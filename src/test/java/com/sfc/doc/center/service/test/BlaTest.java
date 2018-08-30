package com.sfc.doc.center.service.test;

import org.junit.Test;

public class BlaTest {

    @Test
    public void replaceTest() {
        String html = "<h1>Introduction 1</h1>\n" +
                "<p>This is introduction 1.</p>\n" +
                "<p><img src=\"/images/img.jpg\" alt=\"img 1\" /></p>\n" +
                "<h1>Introduction 1</h1>\n" +
                "<p>This is introduction 1.</p>\n" +
                "<p><img src=\"/images/img.jpg\" alt=\"img 1\" /></p>\n" +
                "\n" +
                "<h1>Introduction 1</h1>\n" +
                "<p>This is introduction 1.</p>\n" +
                "<p><img src=\"/images/img.jpg\" alt=\"img 1\" /></p>";
        System.out.println(html);
        html = html.replace("<img src=\"/images/", "<img src=\"${}/images/");
        System.out.println(html);
    }
}
