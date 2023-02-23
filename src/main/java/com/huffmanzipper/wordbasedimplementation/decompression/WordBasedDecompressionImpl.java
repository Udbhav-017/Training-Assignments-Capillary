package com.huffmanzipper.wordbasedimplementation.decompression;

import com.filezipper.utilities.IMap;
import com.huffmanzipper.commons.HuffmanTreeImpl;
import com.huffmanzipper.commons.IHeaderInfo;
import com.huffmanzipper.commons.IHuffmanTree;
import com.huffmanzipper.defaultimplementation.decompression.DefaultDecompressorImpl;

import java.io.IOException;

public class WordBasedDecompressionImpl extends DefaultDecompressorImpl {
    private final IHeaderInfo headerInfo;

    public WordBasedDecompressionImpl(IHeaderInfo headerInfoImpl) {
        super(headerInfoImpl);
        this.headerInfo = headerInfoImpl;
    }
    @Override
    public IMap<String, String> getHuffBitDecodesFromHeader() throws IOException, ClassNotFoundException {
        IMap<String, Integer> frequencyTable = (IMap<String, Integer>) headerInfo.getContent();
        IHuffmanTree hTree = new HuffmanTreeImpl();
        hTree.buildTree(frequencyTable);

        IMap<String, String> huffBitCodes = hTree.getBitEncodings();
        return huffBitCodes.reverse();
    }
}
