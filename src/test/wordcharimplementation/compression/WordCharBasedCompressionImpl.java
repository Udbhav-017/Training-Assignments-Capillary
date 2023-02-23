package com.huffmanzipper.wordcharimplementation.compression;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.utilities.HashMapImpl;
import com.filezipper.utilities.IMap;
import com.huffmanzipper.commons.IHeaderInfo;
import com.huffmanzipper.wordbasedimplementation.compression.WordBasedCompressionImpl;

import java.io.IOException;

public class WordCharBasedCompressionImpl extends WordBasedCompressionImpl {
    public WordCharBasedCompressionImpl(IHeaderInfo headerInfoImpl) {
        super(headerInfoImpl);
    }

    @Override
    protected IMap<String, Integer> createFrequencyTable(IInputStream source) throws IOException {
        IMap<String, Integer> allWordsFrequencyTable = new HashMapImpl<>();
        IMap<String, Integer> filteredWordsFrequencyTable = new HashMapImpl<>();
        IMap<String, Integer> finalFrequencyTable = new HashMapImpl<>();

        int num;
        StringBuilder word = new StringBuilder();

        while ((num = source.read()) != -1) {

            char ch = (char)num;
            if(Character.isLetter(ch)){
                word.append(ch);
            }
            else{
                allWordsFrequencyTable.increment(word.toString(), 1);
                allWordsFrequencyTable.increment(String.valueOf(ch), 1);
                word.setLength(0);
            }

        }
        if(word.length()!=0)
            allWordsFrequencyTable.increment(word.toString(), 1);

        return frequencyTable;
    }
}
