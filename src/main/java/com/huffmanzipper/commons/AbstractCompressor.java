package com.huffmanzipper.commons;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.utilities.IMap;

import java.io.IOException;

public abstract class AbstractCompressor {
//  removed final
    public void compress(IInputStream source, IOutputStream destination) throws IOException{
        IMap<String, Integer> frequencyTable = createFrequencyTable(source);

        IHuffmanTree hTree = createHuffmanTree(frequencyTable);
        IMap<String, String> huffBitCodes = hTree.getBitEncodings();

        IHeaderInfo headerInfo = getHeaderInfoEmptyObject();

        headerInfo.setHeaderInfoObject(frequencyTable, huffBitCodes);

        headerInfo.writeHeader(destination);

        source.reset();
        huffmanEncoder(source, destination, huffBitCodes);
    }

    protected abstract IHeaderInfo getHeaderInfoEmptyObject();
    protected abstract IMap<String, Integer> createFrequencyTable(IInputStream source) throws IOException;
    protected abstract IHuffmanTree createHuffmanTree(IMap<String, Integer> frequencyTable);
    protected abstract void huffmanEncoder(IInputStream source, IOutputStream destination, IMap<String, String> characterBitCodes) throws IOException;
}
