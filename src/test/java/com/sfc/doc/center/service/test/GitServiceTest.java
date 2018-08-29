package com.sfc.doc.center.service.test;

import com.sfc.doc.center.service.GitService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitServiceTest {

    @Autowired
    private GitService gitService;

    @Test
    public void cloneTest() throws GitAPIException {
        System.out.println("clone start");
        gitService.cloneRepo("https://github.com/iuap3/exclusive_cloud_apigateway.git");
        System.out.println("clone end");
    }
}
