package com.sfc.doc.center.service.test;

import com.sfc.doc.center.domain.task.Task;
import com.sfc.doc.center.service.ProcessService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessServiceTest {

    @Autowired
    ProcessService processService;

    @Test
    public void processTest() throws IOException, GitAPIException {
        Task task = new Task();
        task.setGitRepoUrl("https://github.com/slowfishcolor/doc-template.git");
        System.out.println("start process");
        processService.process(task);
        System.out.println("end process");
    }
}
