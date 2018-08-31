package com.sfc.doc.center.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfc.doc.center.domain.menu.MenuNode;
import com.sfc.doc.center.domain.menu.MenuNodeLeafTraversal;
import com.sfc.doc.center.domain.task.Task;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class ProcessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessService.class);

    @Value("${fileLocalPath}")
    private String fileLocalPath;

    private final Map<String, Task> taskMap = new ConcurrentHashMap<>();

    private final BlockingQueue<Task> taskQueue = new LinkedBlockingDeque<>();

    private ExecutorService executor;

    private ObjectMapper mapper;

    @Autowired
    private GitService gitService;

    @Autowired
    private MarkdownService markdownService;

    @Autowired
    private FileService fileService;

    @Autowired
    private OssService ossService;

    public void submitTask(Task task){
        this.taskQueue.offer(task);
    }

    @PostConstruct
    public void init() {
        executor = Executors.newFixedThreadPool(1);
        mapper = new ObjectMapper();
    }

    private class Processor implements Runnable {

        @Override
        public void run() {

        }
    }

    public void process(Task task) throws IOException, GitAPIException {

        // generate path
        String serialNumber = generateSerialNumber();
        String repoName = gitService.getRepoName(task.getGitRepoUrl());
        String localRepoPath = getLocalGitFolder(task.getGitRepoUrl(), serialNumber);
        String outputPath = getLocalOutputPath(localRepoPath);
        task.setBaseUrl("/" + repoName + "/" + serialNumber + "/output");

        // create folder
        fileService.createFolder(localRepoPath);
        fileService.createFolder(outputPath);

        // clone repo
        LOGGER.info("cloning repo: {}", task.getGitRepoUrl());
        gitService.cloneRepo(task.getGitRepoUrl(), localRepoPath);

        // generate menu
        LOGGER.info("processing menu");
        String summaryMarkdown = fileService.readFileAsString(localRepoPath + File.separator + "SUMMARY.md");
        MenuNode menu = markdownService.generateMenuFromSummary(summaryMarkdown);
        menu.setPath(task.getBaseUrl());
        task.setMenu(menu);

        // save menu
        saveMenu(outputPath, menu);

        // copy images folder
        fileService.copyFolder(localRepoPath + File.separator + "images", outputPath + File.separator + "images");

        // traverse all doc leaf
        LOGGER.info("processing markdown to html");
        Iterator<MenuNode> iterator = new MenuNodeLeafTraversal(menu);
        while (iterator.hasNext()) {
            MenuNode node = iterator.next();
            markdownToHtml(localRepoPath + node.getPath(), outputPath + node.getPath() + ".html");
            node.setPath(node.getPath() + ".html");
        }

        // upload all files to oss
        LOGGER.info("uploading files to oss");
        uploadOutputFiles(outputPath, menu.getPath());

        task.setFinish(true);
        LOGGER.info("process finished successfully");
    }

    private void uploadOutputFiles(String outputPath, String baseUrl) {
        Iterator<File> fileIterator = fileService.fileIterator(outputPath);
        while (fileIterator.hasNext()) {
            File file = fileIterator.next();
            LOGGER.debug("file: {}", file.getAbsolutePath());
            LOGGER.debug("relativePath: {}", getFileRelativePath(file.getAbsolutePath(), baseUrl));
            ossService.uploadFile(getFileRelativePath(file.getAbsolutePath(), baseUrl), file);
        }
    }

    private String getFileRelativePath(String absolutePath, String baseUrl) {
        String replacedString = absolutePath.replace('\\', '/');
        return replacedString.substring(replacedString.indexOf(baseUrl));
    }

    private void saveMenu(String filePath, MenuNode menu) throws IOException {
        String menuJsonString = mapper.writeValueAsString(menu);
        fileService.writeToFile(filePath + File.separator + "menu.js", menuJsonString);
    }

    private void markdownToHtml(String sourceFile, String targetFile) throws IOException {
        String markdown = fileService.readFileAsString(sourceFile);
        String html = markdownService.markdownToHtml(markdown);
        html = html.replace("<img src=\"/images/", "<img src=\"${}/images/");
        fileService.writeToFile(targetFile, html);
    }

    private String getLocalGitFolder(String repoUrl, String serialNumber) {
        String repoName = gitService.getRepoName(repoUrl);
        String repoParentPath = fileLocalPath + File.separator + repoName;
        String createdRepoFolderPath = repoParentPath +
                File.separator + serialNumber +
                File.separator + "git";
        return createdRepoFolderPath;
    }

    private String getLocalOutputPath(String localGitFolder) {
        return localGitFolder.substring(0, localGitFolder.length() - 3) + "output";
    }

    private String generateSerialNumber() {
        return new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(System.currentTimeMillis()));
    }

}
