package com.sfc.doc.center.domain.menu;

import org.commonmark.node.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Use to generate {@link MenuNode} hierarchy from {@link Node}
 */
public class MenuNodeGenerator {

    private static ThreadLocal<Integer> idThreadLocal = new ThreadLocal<>();

    public static MenuNode generateMenuFromSummaryNode(Node summaryNode) {
        if (!isSummaryNode(summaryNode)) {
            throw new IllegalArgumentException("Not a valid summary node!");
        }

        return constructSummaryMenuNode(summaryNode);
    }

    private static MenuNode constructSummaryMenuNode(Node summaryNode) {
        idThreadLocal.set(0);

        MenuNode rootNode = new MenuNode(false, summaryNode);
        Text text = (Text) summaryNode.getFirstChild().getFirstChild();
        rootNode.setName(text.getLiteral());
        rootNode.setId(idThreadLocal.get());
        idThreadLocal.set(idThreadLocal.get() + 1);

        Node current = summaryNode.getLastChild().getFirstChild();
        List<Node> children = new ArrayList<>();
        while (current != null) {
            children.add(current);
            current = current.getNext();
        }

        rootNode.setChildren(constructNextLayerMenuNode(children));
        return rootNode;
    }

    private static List<MenuNode> constructNextLayerMenuNode(List<Node> nextLayer) {
        List<MenuNode> children = new ArrayList<>();
        for (Node childNode : nextLayer) {
            if (isMenuPathLeaf(childNode)) {
                children.add(constructMenuPathFromNode(childNode));
            } else if (isMenuDocLeaf(childNode)) {
                children.add(constructMenuDocFromNode(childNode));
            }
        }
        return children;
    }

    private static MenuPath constructMenuPathFromNode(Node node) {
        Text text = (Text) node.getFirstChild().getFirstChild();
        MenuPath menuPath = new MenuPath(node);
        menuPath.setName(text.getLiteral());
        menuPath.setId(idThreadLocal.get());
        idThreadLocal.set(idThreadLocal.get() + 1);

        // construct next layer Node
        List<Node> nextLayer = new ArrayList<>();
        Node nextLayerNode = node.getFirstChild().getNext().getFirstChild();
        while (nextLayerNode != null) {
            nextLayer.add(nextLayerNode);
            nextLayerNode = nextLayerNode.getNext();
        }

        // construct children Menu Node
        List<MenuNode> children = constructNextLayerMenuNode(nextLayer);
        menuPath.setChildren(children);

        return menuPath;
    }

    private static MenuDoc constructMenuDocFromNode(Node node) {
        Link link = (Link) node.getFirstChild().getFirstChild();
        Text text = (Text) link.getFirstChild();
        MenuDoc menuDoc = new MenuDoc(node);
        menuDoc.setName(text.getLiteral());
        menuDoc.setPath(link.getDestination());
        menuDoc.setId(idThreadLocal.get());
        idThreadLocal.set(idThreadLocal.get() + 1);
        return menuDoc;
    }

    /**
     * Weather this {@link Node} is a Summary node
     * <p>
     * Document - Heading
     * - BulletList
     *
     * @param node
     * @return
     */
    public static boolean isSummaryNode(Node node) {
        if (!(node instanceof Document)) {
            return false;
        }

        if (node.getParent() != null) {
            return false;
        }

        return (node.getFirstChild() instanceof Heading) && (node.getLastChild() instanceof BulletList);
    }

    /**
     * Weather this {@link Node} can be convert to a {@link MenuPath}
     * <p>
     * LI - PR - TX
     * - BL _ ...
     *
     * @param node
     * @return
     */
    public static boolean isMenuPathLeaf(Node node) {
        if (!(node instanceof ListItem)) {
            return false;
        }

        final boolean hasPrLeaf = (node.getFirstChild() instanceof Paragraph) &&
                (node.getFirstChild().getFirstChild() instanceof Text);

        final boolean hasBLChild = node.getLastChild() instanceof BulletList;

        return hasPrLeaf && hasBLChild;
    }

    /**
     * Weather this {@link Node} can be convert to a {@link MenuDoc}
     * <p>
     * LI - PR - LK - TX
     *
     * @param node
     * @return
     */
    public static boolean isMenuDocLeaf(Node node) {
        if (!(node instanceof ListItem)) {
            return false;
        }

        final boolean onlyOneChild = node.getFirstChild() == node.getLastChild();

        final boolean hasLinkLeaf = (node.getFirstChild() instanceof Paragraph) &&
                (node.getFirstChild().getFirstChild() instanceof Link);

        return onlyOneChild && hasLinkLeaf;
    }
}
