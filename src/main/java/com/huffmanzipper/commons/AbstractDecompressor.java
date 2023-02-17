package com.huffmanzipper.commons;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.utilities.HashMapImpl;
import com.filezipper.utilities.IMap;

import java.io.IOException;

public abstract class AbstractDecompressor {
    public final void decompress(IInputStream source, IOutputStream destination) throws IOException, ClassNotFoundException {
        IHeaderInfo headerInfo = new HeaderInfoImpl();
        headerInfo.readHeader(source);
        IMap<String, String> huffBitCodes = (IMap<String, String>) headerInfo.getContent();
        IMap<String, String> huffBitDecodes = huffBitCodes.reverse();

        huffmanDecoder(source , destination, huffBitDecodes, headerInfo);
    }

    protected abstract void huffmanDecoder(IInputStream source, IOutputStream destination, IMap<String, String> huffBitDecodes, IHeaderInfo headerInfo) throws IOException;
}
