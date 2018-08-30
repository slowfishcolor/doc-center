package com.sfc.doc.center.domain.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuNodeLeafTraversal implements Iterator<MenuNode> {

    private MenuNode menuNode;

    private List<MenuNode> menuNodeList;

    private Iterator<MenuNode> iterator;

    public MenuNodeLeafTraversal(MenuNode root) {
        menuNodeList = new ArrayList<>();
        traversalToList(root);
        iterator = menuNodeList.iterator();
    }

    private void traversalToList(MenuNode node) {
        if (node.getChildren() == null) {
            if (node.isDocument()) {
                menuNodeList.add(node);
            }
            return;
        }

        for (MenuNode child : node.getChildren()) {
            traversalToList(child);
        }
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public MenuNode next() {
        return iterator.next();
    }
}
