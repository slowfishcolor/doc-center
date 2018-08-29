package com.sfc.doc.center.service;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GitService {

    @Value("${fileLocalPath}")
    private String fileLocalPath;

    public void cloneRepo(String gitRepoUrl, String localRepoPath) throws GitAPIException {
        CloneCommand cc = Git.cloneRepository().setURI(gitRepoUrl);
        cc.setDirectory(new File(localRepoPath)).call();
    }

    /**
     * Build local repo path, and clone into it.
     *
     * @param gitRepoUrl
     * @return path to local repo
     * @throws GitAPIException
     */
    public String cloneRepo(String gitRepoUrl) throws GitAPIException {
        String localRepoPath = createLocalRepoFolder(gitRepoUrl);
        cloneRepo(gitRepoUrl, localRepoPath);
        return localRepoPath;
    }

    private String getRepoName(String gitRepoUrl) {
        int start = gitRepoUrl.lastIndexOf('/');
        return gitRepoUrl.substring(start, gitRepoUrl.length() - 5);
    }

    /**
     * Create a local repo folder at fileLocalPath/repoName/yyyyMMdd-hhmmss
     *
     * @param gitRepoUrl
     * @return created folder path
     */
    private String createLocalRepoFolder(String gitRepoUrl) {
        String repoName = getRepoName(gitRepoUrl);
        String repoParentPath = fileLocalPath + File.separator + repoName;
        String createdFolderPath = repoParentPath +
                File.separator +
                new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(System.currentTimeMillis()));
        File filePath = new File(createdFolderPath);
        filePath.mkdirs();
        return createdFolderPath;
    }
}
