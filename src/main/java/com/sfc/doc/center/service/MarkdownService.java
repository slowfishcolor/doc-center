package com.sfc.doc.center.service;

import com.sfc.doc.center.domain.menu.MenuNode;
import com.sfc.doc.center.domain.menu.MenuNodeGenerator;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class MarkdownService {

    private Parser parser;

    private HtmlRenderer renderer = HtmlRenderer.builder().build();

    @PostConstruct
    public void init() {
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        parser = Parser.builder().extensions(extensions).build();
        renderer = HtmlRenderer.builder().extensions(extensions).build();
    }

    public String markdownToHtml(String markdownText) {
        Node document = parser.parse(markdownText);
        return renderer.render(document);
    }

    public MenuNode generateMenuFromSummary(String summaryMarkdownText) {
        Node summaryNode = parser.parse(summaryMarkdownText);
        return MenuNodeGenerator.generateMenuFromSummaryNode(summaryNode);
    }

}
