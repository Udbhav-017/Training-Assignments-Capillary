package com.huffmanzipper.commons;

import com.filezipper.utilities.IMap;

public interface IHuffmanTree {
    public void buildTree(IMap<String, Integer> frequencyTable);
    public IMap<String, String>  getBitEncodings();
    public void setRoot(ITreeNode root);
    public ITreeNode getRoot();
}
