package com.sfc.doc.center.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class MarkdownService {

    private Parser parser = Parser.builder().build();

    private HtmlRenderer renderer = HtmlRenderer.builder().build();

    public String markdownToHtml(String markdownText) {
        Node document = parser.parse(markdownText);
        return renderer.render(document);
    }

}
