package com.huffmanzipper.commons;

import com.filezipper.utilities.HashMapImpl;
import com.filezipper.utilities.IMap;
import com.filezipper.utilities.IMapEntry;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanTreeImpl implements IHuffmanTree, Comparator<IHuffmanTree> {
    private ITreeNode root;

    @Override
    public IMap<String, String> getBitEncodings() {
        IMap<String, String> bitCodes = new HashMapImpl<>();

        if (this.root instanceof TerminalNode) {
            bitCodes.put(((TerminalNode) this.root).getCharacter(), "0");
            return bitCodes;
        }

        StringBuilder code = new StringBuilder();

        bitCodeHelper(this.root.getLeft(), code, 0, bitCodes);

        bitCodeHelper(this.root.getRight(), code, 1, bitCodes);


        return bitCodes;
    }

    @Override
    public void buildTree(IMap<String, Integer> frequencyTable) {
        PriorityQueue<IHuffmanTree> minpq = new PriorityQueue<>(new HuffmanTreeImpl());

        for (IMapEntry row : frequencyTable.getEntryArray()) {
            IHuffmanTree tree = new HuffmanTreeImpl();
            tree.setRoot(new TerminalNode((Integer) row.getValue(), (String) row.getKey()));
            minpq.add(tree);
        }

        while (minpq.size() > 1) {
            IHuffmanTree tree1 = minpq.poll();
            IHuffmanTree tree2 = minpq.poll();

            IHuffmanTree treeNew = new HuffmanTreeImpl();

            int combinedWeight = tree1.getRoot().getWeight() + tree2.getRoot().getWeight();

            treeNew.setRoot(new NonTerminalNode(combinedWeight));
            treeNew.getRoot().setLeft(tree1.getRoot());
            treeNew.getRoot().setRight(tree2.getRoot());
            minpq.add(treeNew);
        }

        this.root = minpq.poll().getRoot();
    }

    @Override
    public void setRoot(ITreeNode root) {
        this.root = root;
    }

    @Override
    public ITreeNode getRoot() {
        return this.root;
    }

    @Override
    public int compare(IHuffmanTree h1, IHuffmanTree h2) {
        int result;
        if (h1.getRoot().getWeight() > h2.getRoot().getWeight()) {
            result = 1;
        } else {
            result = -1;
        }
        return result;
    }

    private void bitCodeHelper(ITreeNode node, StringBuilder output, int move, IMap<String, String> bitcodes) {
        if (node == null) return;
        output.append(move);
        if (node instanceof NonTerminalNode) {
            bitCodeHelper(node.getLeft(), output, 0, bitcodes);
            bitCodeHelper(node.getRight(), output, 1, bitcodes);
        }
        if (node instanceof TerminalNode) {
            bitcodes.put(((TerminalNode) node).getCharacter(), output.toString());
        }
        output.deleteCharAt(output.length() - 1);
    }
}
