package com.sfc.doc.center.service.test;

import com.sfc.doc.center.service.FileService;
import com.sfc.doc.center.service.MarkdownService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    @Autowired
    FileService fileService;

    @Autowired
    MarkdownService markdownService;

    @Test
    public void readFileTest() throws IOException {
        System.out.println(fileService.readFileAsString("E:\\doc-temp\\exclusive_cloud_apigatewa\\20180829-191008\\articles\\apigateway\\5-\\developer_gateway.md"));
    }


    @Test
    public void writeFileTest() throws IOException {
        String markdown = fileService.readFileAsString("E:\\doc-temp\\exclusive_cloud_apigatewa\\20180829-191008\\articles\\apigateway\\5-\\developer_gateway.md");
        String html = markdownService.markdownToHtml(markdown);
        fileService.writeToFile("E:\\doc-temp\\exclusive_cloud_apigatewa\\20180829-191008\\articles\\apigateway\\5-\\developer_gateway.md.html", html);
    }

    @Test
    public void copyFileTest() throws IOException {
        fileService.copyFolder("E:\\doc-temp\\exclusive_cloud_apigatewa\\20180829-191008\\articles\\apigateway\\5-\\images", "E:\\doc-temp\\exclusive_cloud_apigatewa\\20180829-191009\\articles\\apigateway\\5-\\images");
    }

}
