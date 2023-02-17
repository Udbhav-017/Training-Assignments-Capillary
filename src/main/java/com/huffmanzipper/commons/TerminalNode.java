package com.huffmanzipper.commons;

public class TerminalNode implements ITreeNode {
    Integer weight;
    String character;

    TerminalNode(Integer weight, String character) {
        this.weight = weight;
        this.character = character;
    }

    public String getCharacter() {
        return this.character;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public void setLeft(ITreeNode node) {
    }

    @Override
    public ITreeNode getLeft() {
        return null;
    }

    @Override
    public void setRight(ITreeNode node) {
    }

    @Override
    public ITreeNode getRight() {
        return null;
    }
}
