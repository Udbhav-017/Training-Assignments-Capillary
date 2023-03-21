package com.huffmanzipper.commons;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.statistics.Stats;
import com.filezipper.utilities.IMap;

import java.io.IOException;


public abstract class AbstractCompressor {
    public final void compress(IInputStream source, IOutputStream destination) throws IOException{
        long start = System.currentTimeMillis();
        IMap<String, Integer> frequencyTable = createFrequencyTable(source);
        long end = System.currentTimeMillis();
        Stats.createFrequencyTableTime=end-start;

        start = System.currentTimeMillis();
        IHuffmanTree hTree = createHuffmanTree(frequencyTable);
        end = System.currentTimeMillis();
        Stats.createHuffmanTreeTime=end-start;

        start = System.currentTimeMillis();
        IMap<String, String> huffBitCodes = hTree.getBitEncodings();
        end = System.currentTimeMillis();
        Stats.generateHuffmanCodesTime=end-start;

        IHeaderInfo headerInfo = getHeaderInfoEmptyObject();

        headerInfo.setHeaderInfoObject(frequencyTable, huffBitCodes);
        Stats.headerSizeInBytes = headerInfo.getSize();
        Stats.compressedDataSizeInBytes = headerInfo.getTotalCharactersInCompressedFile();

        headerInfo.writeHeader(destination);

        source.reset();
        start = System.currentTimeMillis();
        huffmanEncoder(source, destination, huffBitCodes);
        end = System.currentTimeMillis();
        Stats.huffmanEncoderTime=end-start;
    }

    protected abstract IHeaderInfo getHeaderInfoEmptyObject();
    protected abstract IMap<String, Integer> createFrequencyTable(IInputStream source) throws IOException;
    protected abstract IHuffmanTree createHuffmanTree(IMap<String, Integer> frequencyTable);
    protected abstract void huffmanEncoder(IInputStream source, IOutputStream destination, IMap<String, String> characterBitCodes) throws IOException;
}
