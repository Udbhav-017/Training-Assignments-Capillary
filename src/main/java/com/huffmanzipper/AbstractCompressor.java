package com.huffmanzipper;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;

public abstract class AbstractCompressor {
    public final void compress(IInputStream source, IOutputStream destination) {
        //abstract
    }

//    abstract IMap<Character, Integer> createFrequencyTable(IInputStream source) throws IOException;
//    protected abstract IHuffmanTree createHuffmanTree(IMap<Character, Integer> frequencyTable);
//    protected abstract void HuffmanEncoder(IInputStream source, IOutputStream destination, IMap<Character, String> characterBitCodes) throws IOException;
//    protected abstract void appendHeader(IOutputStream destination) throws IOException;
}
