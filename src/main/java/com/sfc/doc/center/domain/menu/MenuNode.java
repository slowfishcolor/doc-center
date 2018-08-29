package com.sfc.doc.center.domain.menu;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuNode {

    /**
     * menu identity id
     */
    private int id;

    /**
     * current level menu display order
     */
    private int order;

    /**
     * Weather this node represent a document or just a path.
     * A document node is a leaf node.
     * A path node should have children.
     */
    private boolean document;

    /**
     * menu node name
     */
    private String name;

    /**
     * menu node path
     */
    private String route;

    /**
     * children layer nodes
     */
    private List<MenuNode> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isDocument() {
        return document;
    }

    public void setDocument(boolean document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public List<MenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }
}
