package com.codecool.javabst;

import java.util.ArrayList;
import java.util.List;

// Skeleton for the Binary search tree. Feel free to modify this class.
public class BinarySearchTree {

    Node root;

    private BinarySearchTree(List<Integer> elements) {
        this.root = new Node(getMiddleElement(elements));
        fillNode(root, elements);
    }

    private boolean hasElement(List<Integer> elements) {
        return elements.size() > 0;
    }

    private int getHalfIndex(List<Integer> elements) {
        return elements.size() / 2;
    }

    private Integer getMiddleElement(List<Integer> elements) {
        return elements.remove(getHalfIndex(elements));
    }

    private void fillNode(Node node, List<Integer> elements) {
        if (!hasElement(elements)) return;
        List<Integer> leftList = new ArrayList<>(elements.subList(0, getHalfIndex(elements)));
        List<Integer> rightList = new ArrayList<>(elements.subList(getHalfIndex(elements) + 1, elements.size()));
        if (hasElement(leftList)) {
            node.setLeft(new Node(getMiddleElement(leftList)));
            fillNode(node.getLeft(), leftList);
        }
        if (hasElement(rightList)) {
            node.setRight(new Node(getMiddleElement(rightList)));
            fillNode(node.getRight(), rightList);
        }
    }

    public static BinarySearchTree build(List<Integer> elements) {
        return new BinarySearchTree(elements);
    }

    private boolean searchInNode(Node node, Integer toFind) {
        if (node == null) return false;
        if (node.getItem().equals(toFind)) return true;
        return node.getItem() > toFind ?
                searchInNode(node.getLeft(), toFind) :
                searchInNode(node.getRight(), toFind);
    }

    public boolean search(Integer toFind) {
        return searchInNode(root, toFind);
    }

    private void searchPlaceForItem(Node node, Integer toAdd) {
        Integer current = node.getItem();
        if (current.equals(toAdd)) throw new IllegalArgumentException("Number " + toAdd + "already exist in the tree");
        if (current > toAdd) {
            if (node.hasLeftChild()) {
                searchPlaceForItem(node.getLeft(), toAdd);
            } else {
                node.setLeft(new Node(toAdd));
            }
        } else {
            if (node.hasRightChild()) {
                searchPlaceForItem(node.getRight(), toAdd);
            } else {
                node.setRight(new Node(toAdd));
            }
        }
    }

    public void add(Integer toAdd) {
        searchPlaceForItem(root, toAdd);
    }

    private Node searchForNode(Node node, Integer toSearch) {
        if (node == null) return null;
        if (node.getItem().equals(toSearch)) return node;
        return node.getItem() > toSearch ?
                searchForNode(node.getLeft(), toSearch) :
                searchForNode(node.getRight(), toSearch);
    }

    private Node searchForParentNode(Node node, Integer toSearch) {
        if (node == null) return null;
        if (node.hasRightChild() && node.getRight().getItem().equals(toSearch)) return node;
        if (node.hasLeftChild() && node.getLeft().getItem().equals(toSearch)) return node;
        return node.getItem() > toSearch ?
                searchForParentNode(node.getLeft(), toSearch) :
                searchForParentNode(node.getRight(), toSearch);
    }

    private void deleteNode(Node nodeToRemove, Node parentNode) {
        if (!nodeToRemove.hasRightChild() && !nodeToRemove.hasLeftChild()) {
            parentNode.overrideNodeChild(nodeToRemove, null);
            return;
        }
        if (!nodeToRemove.hasRightChild() && nodeToRemove.hasLeftChild()) {
            nodeToRemove.overrideNode(nodeToRemove.getLeft());
            return;
        }
        if (nodeToRemove.hasRightChild() && !nodeToRemove.hasLeftChild()) {
            nodeToRemove.overrideNode(nodeToRemove.getRight());
            return;
        }
        Node maxInLeft = getMaximum(nodeToRemove.getLeft());
        Node maxParent = searchForParentNode(nodeToRemove, maxInLeft.getItem());
        nodeToRemove.setItem(maxInLeft.getItem());
        maxParent.overrideNodeChild(maxInLeft, null);
    }

    private Node getMaximum(Node node) {
        if (node.getRight() == null) return node;
        return getMaximum(node.getRight());
    }

    public void remove(Integer toRemove) {
        if (root.getItem().equals(toRemove)) {
            if (root.hasChild()) {
                deleteNode(root, null);
            } else {
                root = null;
            }
            return;
        }
        Node parentNode = searchForParentNode(root, toRemove);
        Node removeNode = searchForNode(parentNode, toRemove);
        if (removeNode == null) throw new IllegalArgumentException("Number " + toRemove + "not found in the tree");
        deleteNode(removeNode, parentNode);
    }

}
