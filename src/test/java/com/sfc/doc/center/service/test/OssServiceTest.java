package com.sfc.doc.center.service.test;

import com.sfc.doc.center.service.OssService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OssServiceTest {

    @Autowired
    OssService ossService;

    @Test
    public void uploadTest() {
        String filePath = "/doc-template/20180830-195303/output/introduction/introduction_1.md.html";
        String absolutePath = "E:\\doc-temp\\doc-template\\20180830-195303\\output\\introduction\\introduction_1.md.html";
        File file = new File(absolutePath);
        ossService.uploadFile(filePath, file);
    }
}
