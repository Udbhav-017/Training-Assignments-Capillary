package com.huffmanzipper.defaultimplementation.decompression;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.huffmanzipper.commons.AbstractDecompressor;
import com.huffmanzipper.commons.IHeaderInfo;
import com.filezipper.utilities.IMap;

import java.io.IOException;

public class DefaultDecompressorImpl extends AbstractDecompressor {
    private final IHeaderInfo headerInfo;
    public DefaultDecompressorImpl(IHeaderInfo headerInfoImpl){
        this.headerInfo = headerInfoImpl;
    }
    @Override
    protected IHeaderInfo getHeaderInfoEmptyObject() {
        return this.headerInfo;
    }

    @Override
    public IMap<String, String> getHuffBitDecodesFromHeader() throws IOException, ClassNotFoundException {
        IMap<String, String> huffBitCodes = (IMap<String, String>) this.headerInfo.getContent();
        return huffBitCodes.reverse();
    }

    @Override
    protected void huffmanDecoder(IInputStream source, IOutputStream destination, IMap<String, String> huffBitDecodes, IHeaderInfo headerInfo) throws IOException {

        StringBuilder code = new StringBuilder();

        while (true) {
            int num = source.read();

            if(num==-1) return;

            int bitsLeft = 8;
            while (bitsLeft-- > 0) {
                int bit = ((num) >> 7) & 1;
                code = code.append(bit);

                num <<= 1;
                if (huffBitDecodes.containsKey(code.toString())) {
                      for(char data: huffBitDecodes.get(code.toString()).toCharArray()){
                          destination.write(data);
                      }

                      code = new StringBuilder();

                    }
                }
            }
        }
    }
