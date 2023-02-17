package com.huffmanzipper.commons;

public class NonTerminalNode implements ITreeNode {
    Integer weight;
    ITreeNode left;
    ITreeNode right;

    NonTerminalNode(Integer weight) {
        this.weight = weight;
    }

    @Override
    public Integer getWeight() {
        return this.weight;
    }

    @Override
    public void setLeft(ITreeNode node) {
        this.left = node;

    }

    @Override
    public ITreeNode getLeft() {
        return this.left;
    }

    @Override
    public void setRight(ITreeNode node) {
        this.right = node;

    }

    @Override
    public ITreeNode getRight() {
        return this.right;
    }
}
