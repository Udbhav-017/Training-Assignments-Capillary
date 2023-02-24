package com.huffmanzipper.wordbasedimplementation.compression;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.utilities.HashMapImpl;
import com.filezipper.utilities.IMap;

import com.huffmanzipper.commons.IHeaderInfo;

import com.huffmanzipper.defaultimplementation.compression.DefaultCompressorImpl;

import java.io.IOException;

public class WordBasedCompressionImpl extends DefaultCompressorImpl {
    public WordBasedCompressionImpl(IHeaderInfo headerInfoImpl) {
        super(headerInfoImpl);
    }

    public class encodingState{
        public int bitCount = 0;            // tracking number of bits for writing 1 byte to file
        public int buffer = 0;             // bits to be written in output file
        public boolean padRequired = true;
    }

    @Override
    protected IMap<String, Integer> createFrequencyTable(IInputStream source) throws IOException{
        IMap<String, Integer> frequencyTable = new HashMapImpl<>();

        int num;
        StringBuilder word = new StringBuilder();

        while ((num = source.read()) != -1) {

            char ch = (char)num;
            if(Character.isLetterOrDigit(ch)){
                word.append(ch);
            }
            else{
                frequencyTable.increment(word.toString(), 1);
                frequencyTable.increment(String.valueOf(ch), 1);
                word.setLength(0);
            }

        }
        if(word.length()!=0)
            frequencyTable.increment(word.toString(), 1);

        return frequencyTable;
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
                code = huffBitCodes.get(word.toString());
                writeHuffCode(code, state, destination);
                word.setLength(0);
                word.append(c);

                code = huffBitCodes.get(word.toString());
                writeHuffCode(code, state, destination);
                word.setLength(0);
            }
        }
        if(word.length()!=0){
            code = huffBitCodes.get(word.toString());
            writeHuffCode(code, state, destination);
        }

        if (state.padRequired){
            state.buffer = state.buffer << (8 - state.bitCount);
            byte b = (byte) state.buffer;
            destination.write(b);
        }
    }

    protected void writeHuffCode(String code, encodingState state, IOutputStream destination) throws IOException {
        for (char bit : code.toCharArray()) {
            state.buffer = state.buffer << 1;

            if (bit == '1') state.buffer = state.buffer ^ 1;
            state.bitCount++;
            state.padRequired = true;

            if (state.bitCount == 8) {
                byte b = (byte) state.buffer;

                destination.write(b);
                state.buffer = 0;
                state.bitCount = 0;
                state.padRequired = false;
            }
        }
    }
}
