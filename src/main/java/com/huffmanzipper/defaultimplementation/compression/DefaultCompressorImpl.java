package com.huffmanzipper.defaultimplementation.compression;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.utilities.HashMapImpl;
import com.filezipper.utilities.IMap;
import com.huffmanzipper.commons.*;

import java.io.IOException;

public class DefaultCompressorImpl extends AbstractCompressor {
    private final IHeaderInfo headerInfo;
    public DefaultCompressorImpl(IHeaderInfo headerInfoImpl){
        this.headerInfo = headerInfoImpl;
    }
    @Override
    protected IHeaderInfo getHeaderInfoEmptyObject() {
        return this.headerInfo;
    }

    protected IMap<String, Integer> createFrequencyTable(IInputStream source) throws IOException {
        IMap<String, Integer> frequencyTable = new HashMapImpl<>();

        int num;
        while ((num = source.read()) != -1) {
            String ch = String.valueOf((char) num);
            frequencyTable.increment(ch, 1);
        }
        
        return frequencyTable;
    }
    protected IHuffmanTree createHuffmanTree(IMap<String, Integer> frequencyTable){
        IHuffmanTree hTree = new HuffmanTreeImpl();
        hTree.buildTree(frequencyTable);
        return hTree;
    }
    protected void huffmanEncoder(IInputStream source, IOutputStream destination, IMap<String, String> huffBitCodes) throws IOException{

        int num = 0;                // for reading from file
        String ch;                    // for storing character conversion of int
        int bitCount = 0;            // tracking number of bits for writing 1 byte to file
        int buffer = 0;             // bits to be written in output file
        boolean padRequired = true;

        while ((num = source.read()) != -1) {

            ch = String.valueOf((char) num);
            String code = huffBitCodes.get(ch);
            int loop = 0;
            for (char bit : code.toCharArray()) {
                loop++;
                buffer = buffer << 1;

                if (bit == '1') buffer = buffer ^ 1;
                bitCount++;
                padRequired = true;

                if (bitCount == 8) {
                    byte b = (byte) buffer;
                    destination.write(b);
                    buffer = 0;
                    bitCount = 0;
                    padRequired = false;
                }
            }
        }

        if (padRequired){
            buffer = buffer << (8 - bitCount);
            byte b = (byte) buffer;
            destination.write(b);
        }
    }
}
