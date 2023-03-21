package com.huffmanzipper.commons;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.statistics.Stats;
import com.filezipper.utilities.IMap;

import java.io.IOException;

public abstract class AbstractDecompressor {
    public final void decompress(IInputStream source, IOutputStream destination) throws IOException, ClassNotFoundException {
        IHeaderInfo headerInfo = getHeaderInfoEmptyObject();
        long start = System.currentTimeMillis();
        headerInfo.readHeader(source);
        long end  = System.currentTimeMillis();
        Stats.readHeaderTime =  (end-start);

        start = System.currentTimeMillis();
        IMap<String, String> huffBitDecodes = getHuffBitDecodesFromHeader();
        end  = System.currentTimeMillis();
        Stats.getHuffCodeMapFromHeaderTime =  (end-start);

        start = System.currentTimeMillis();
        huffmanDecoder(source , destination, huffBitDecodes, headerInfo);
        end  = System.currentTimeMillis();
        Stats.decoderTime =  (end-start);
    }

    protected abstract IHeaderInfo getHeaderInfoEmptyObject();

    public abstract IMap<String, String> getHuffBitDecodesFromHeader() throws IOException, ClassNotFoundException;

    protected abstract void huffmanDecoder(IInputStream source, IOutputStream destination, IMap<String, String> huffBitDecodes, IHeaderInfo headerInfo) throws IOException;

}
