package com.huffmanzipper.commons;

interface ITreeNode{
    Integer getWeight();

    void setLeft(ITreeNode node);

    ITreeNode getLeft();

    void setRight(ITreeNode node);

    ITreeNode getRight();
}