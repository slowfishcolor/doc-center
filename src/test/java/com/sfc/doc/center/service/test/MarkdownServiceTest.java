package com.sfc.doc.center.service.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfc.doc.center.domain.menu.MenuNode;
import com.sfc.doc.center.domain.menu.MenuNodeGenerator;
import com.sfc.doc.center.service.MarkdownService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarkdownServiceTest {

    @Autowired
    MarkdownService markdownService;

    String md = "# Doc Template Show Case\n" +
            "\n" +
            "* Introduction\n" +
            "    * [Introduction 1](/introduction/introduction_1.md)\n" +
            "    * [Introduction 2](/introduction/introduction_2.md)\n" +
            "* Version\n" +
            "    * [Version Summary](/version/version_summary.md)\n" +
            "    * Version Details\n" +
            "        * [Version Detail 1](/version/version-details/version_detail_1.md)\n" +
            "        * [Version Detail 2](/version/version-details/version_detail_2.md)";

    @Test
    public void markdownToHtmlTest() {
        String html = markdownService.markdownToHtml(md);
        System.out.printf(html);
    }

    @Test
    public void generateMenuTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MenuNode menuNode = markdownService.generateMenuFromSummary(md);
        System.out.println(mapper.writeValueAsString(menuNode));
    }
}
