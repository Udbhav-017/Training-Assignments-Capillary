package com.huffmanzipper.wordcharimplementation.compression;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
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
        int percent = 100;
        return getFilteredFrequencyTable((HashMapImpl<String, Integer>) super.createFrequencyTable(source), percent);
    }

    private HashMapImpl<String,Integer> getFilteredFrequencyTable(HashMapImpl<String,Integer> allWordsFrequencyTable, int percentOfWords){
        Object[] sortedWordArray = allWordsFrequencyTable.sortKeyByValues();

        int totalWordsInMap = allWordsFrequencyTable.size();
        int topXPercent = (int) (((1.0)*percentOfWords/100)*totalWordsInMap);

        HashMapImpl<String, Integer> topWordsTable = new HashMapImpl<>();

        for(int i = 0; i < topXPercent; i++){
            String word = (String)sortedWordArray[i];
            topWordsTable.put(word, allWordsFrequencyTable.get(word));
        }

        for(int i = topXPercent; i < totalWordsInMap; i++){
            String word = (String)sortedWordArray[i];

            for(int j=0; j<word.length(); j++){
                topWordsTable.increment(String.valueOf(word.charAt(j)), allWordsFrequencyTable.get(word));
            }
        }
        return  topWordsTable;
    }

    @Override
    protected void huffmanEncoder(IInputStream source, IOutputStream destination, IMap<String, String> huffBitCodes) throws IOException{
        encodingState state = new encodingState();

        StringBuilder word = new StringBuilder();
        String code="";

        int num = 0;                // for reading from file

        while ((num = source.read()) != -1) {
            char c = (char)num;
            if(Character.isLetterOrDigit(c)){
                word.append(c);
            }
            else{
                if(huffBitCodes.containsKey(word.toString())){
                    code = huffBitCodes.get(word.toString());
                    writeHuffCode(code, state, destination);
                }
                else{
                    for(int i=0; i<word.length(); i++){
                        code = huffBitCodes.get(String.valueOf(word.charAt(i)));
                        writeHuffCode(code, state, destination);
                    }
                }

                code = huffBitCodes.get(String.valueOf(c));
                writeHuffCode(code, state, destination);
                word.setLength(0);
            }
        }

        if(word.length()!=0){
            if(huffBitCodes.containsKey(word.toString())){
                code = huffBitCodes.get(word.toString());
                writeHuffCode(code, state, destination);
            }
            else{
                for(int i=0; i<word.length(); i++){
                    code = huffBitCodes.get(String.valueOf(word.charAt(i)));
                    writeHuffCode(code, state, destination);
                }
            }
        }

        if (state.padRequired){
            state.buffer = state.buffer << (8 - state.bitCount);
            byte b = (byte) state.buffer;
//            destination.write(b);
        }
    }
}
