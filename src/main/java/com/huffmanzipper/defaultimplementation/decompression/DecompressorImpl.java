package com.huffmanzipper.defaultimplementation.decompression;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.huffmanzipper.commons.AbstractDecompressor;
import com.huffmanzipper.commons.IHeaderInfo;
import com.filezipper.utilities.IMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DecompressorImpl extends AbstractDecompressor {
    @Override
    protected void huffmanDecoder(IInputStream source, IOutputStream destination, IMap<String, String> huffBitDecodes, IHeaderInfo headerInfo) throws IOException {

        int noOfCharactersToScan = headerInfo.getTotalCharactersInUncompressedFile();

        StringBuilder code = new StringBuilder();

        while (true) {
            byte num = source.read();

            int bitsLeft = 8;
            while (bitsLeft-- > 0) {
                int bit = ((num) >> 7) & 1;
                code = code.append(bit);

                num <<= 1;
                if (huffBitDecodes.containsKey(code.toString())) {
                    byte[] data= huffBitDecodes.get(code.toString()).getBytes(StandardCharsets.UTF_8);

                    for(Byte b: data) destination.write(b);
                    code = new StringBuilder();

                    if (--noOfCharactersToScan == 0)
                        return;
                    }
                }
            }
        }
    }
