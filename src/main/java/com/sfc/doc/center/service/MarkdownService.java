package com.sfc.doc.center.service;

import com.sfc.doc.center.domain.menu.MenuNode;
import com.sfc.doc.center.domain.menu.MenuNodeGenerator;
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

    public MenuNode generateMenuFromSummary(String summaryMarkdownText) {
        Node summaryNode = parser.parse(summaryMarkdownText);
        return MenuNodeGenerator.generateMenuFromSummaryNode(summaryNode);
    }

}
