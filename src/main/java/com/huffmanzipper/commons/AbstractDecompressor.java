package com.huffmanzipper.commons;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.utilities.IMap;

import java.io.IOException;

public abstract class AbstractDecompressor {
    public final void decompress(IInputStream source, IOutputStream destination) throws IOException, ClassNotFoundException {
        IHeaderInfo headerInfo = getHeaderInfoEmptyObject();
        headerInfo.readHeader(source);

        IMap<String, String> huffBitDecodes = getHuffBitDecodesFromHeader();

        huffmanDecoder(source , destination, huffBitDecodes, headerInfo);
    }

    protected abstract IHeaderInfo getHeaderInfoEmptyObject();

    public abstract IMap<String, String> getHuffBitDecodesFromHeader() throws IOException, ClassNotFoundException;

    protected abstract void huffmanDecoder(IInputStream source, IOutputStream destination, IMap<String, String> huffBitDecodes, IHeaderInfo headerInfo) throws IOException;

}
