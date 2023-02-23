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

    @Override
    protected IMap<String, Integer> createFrequencyTable(IInputStream source) throws IOException{
        IMap<String, Integer> frequencyTable = new HashMapImpl<>();

        int num;
        StringBuilder word = new StringBuilder();

        while ((num = source.read()) != -1) {

            char ch = (char)num;
            if(Character.isLetter(ch)){
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

        int num = 0;                // for reading from file
        StringBuilder word = new StringBuilder();
        int bitCount = 0;            // tracking number of bits for writing 1 byte to file
        int buffer = 0;             // bits to be written in output file
        boolean padRequired = true;
        String code="";

        while ((num = source.read()) != -1) {
            char c = (char)num;
            if(Character.isLetter(c)){
                word.append(c);
            }
            else{
                code = huffBitCodes.get(word.toString());
                for (char bit : code.toCharArray()) {
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
                word.setLength(0);
                word.append(c);

                code = huffBitCodes.get(word.toString());
                for (char bit : code.toCharArray()) {
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
                word.setLength(0);
            }

        }
        if(word.length()!=0){
            code = huffBitCodes.get(word.toString());

            for (char bit : code.toCharArray()) {
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
