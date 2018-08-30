package com.sfc.doc.center.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;

@Service
public class FileService {

    @Value("${fileLocalPath}")
    private String fileLocalPath;

    private static final String CHARSET = "UTF-8";

    public String readFileAsString(String fileFullPath) throws IOException {
        File file = new File(fileFullPath);
        return FileUtils.readFileToString(file, CHARSET);
    }

    public void writeToFile(String fileFullPath, String content) throws IOException {
        File file = new File(fileFullPath);
        FileUtils.writeStringToFile(file, content, CHARSET);
    }

    public void copyFolder(String sourcePath, String targetPath) throws IOException {
        File sourceDir = new File(sourcePath);
        File targetDir = new File(targetPath);
        FileUtils.copyDirectory(sourceDir, targetDir);
    }

    public void createFolder(String folderPath) {
        File filePath = new File(folderPath);
        filePath.mkdirs();
    }


}
