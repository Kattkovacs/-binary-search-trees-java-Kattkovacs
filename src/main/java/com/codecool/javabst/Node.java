package com.codecool.javabst;

public class Node {
    private Integer item;
    private Node left;
    private Node right;

    public Node(Integer item) {
        this.item = item;
    }

    public void overrideNode(Node newNode) {
        this.setItem(newNode.getItem());
        this.setLeft(newNode.getLeft());
        this.setRight(newNode.getRight());
    }

    public boolean hasChild() {
        return hasLeftChild() || hasRightChild();
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    public void overrideNodeChild(Node childNode, Node newNode) {
        if (!hasChild()) throw new IllegalArgumentException("Child node not found");
        if (hasLeftChild() && left.equals(childNode)) {
            setLeft(newNode);
        } else if (hasRightChild() && right.equals(childNode)) {
            setRight(newNode);
        }
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
