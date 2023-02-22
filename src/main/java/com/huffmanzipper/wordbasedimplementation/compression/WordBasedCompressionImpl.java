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

        char num;
        StringBuilder word = new StringBuilder();

        while ((num = (char)source.read()) != -1) {

            if(Character.isLetter(num)){
                word.append(num);
            }
            else{
                frequencyTable.increment(word.toString(), 1);
                frequencyTable.increment(String.valueOf(num), 1);
                word = new 
            }

            if(word!="")
                add(word)
//            frequencyTable.increment(ch, 1);
        }

        return frequencyTable;
    }
}
