package com.huffmanzipper.wordbasedimplementation.compression;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.utilities.HashMapImpl;
import com.filezipper.utilities.IMap;
import com.huffmanzipper.defaultimplementation.compression.DefaultCompressorImpl;

import java.io.IOException;

public class WordBasedCompressionImplDefault extends DefaultCompressorImpl {
    @Override
    protected IMap<String, Integer> createFrequencyTable(IInputStream source) throws IOException{
        IMap<String, Integer> frequencyTable = new HashMapImpl<>();

        int num;
        StringBuilder word = new StringBuilder();

        while ((num = source.read()) != -1) {

            word.append((char) num);
//            frequencyTable.increment(ch, 1);
        }

        return frequencyTable;
    }
}
