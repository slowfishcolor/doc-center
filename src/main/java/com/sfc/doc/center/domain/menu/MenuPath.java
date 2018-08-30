package com.sfc.doc.center.domain.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.commonmark.node.Node;

/**
 * represent a folder menu item
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuPath extends MenuNode{

    public MenuPath() {
        super(false);
    }

    public MenuPath(Node node) {
        super(false, node);
    }
}
