package com.sfc.doc.center.domain.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.commonmark.node.Node;

/**
 * represent a document menu item, which `path` target to actual file
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDoc extends MenuNode {

    public MenuDoc() {
        super(true);
    }

    public MenuDoc(Node node) {
        super(true, node);
    }
}
