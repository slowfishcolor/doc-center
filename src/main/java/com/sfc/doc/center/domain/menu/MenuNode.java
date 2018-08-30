package com.sfc.doc.center.domain.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.commonmark.node.Node;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuNode {

    /**
     * menu identity id
     */
    private int id;

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
    private String path;

    /**
     * children layer nodes
     */
    private List<MenuNode> children;

    /**
     * related node
     */
    @JsonIgnore
    private Node node;

    public MenuNode() {
    }

    public MenuNode(boolean document) {
        this.document = document;
    }

    public MenuNode(boolean document, Node node) {
        this.document = document;
        this.node = node;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
