package com.huffmanzipper.commons;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.utilities.IMap;

import java.io.IOException;

public interface IHeaderInfo {
    public void setHeaderInfoObject(IMap countMap, IMap huffBitCodeMap) throws IOException;
    public Object getContent() throws IOException, ClassNotFoundException;
    public Integer getSize();
    public Integer getTotalCharactersInUncompressedFile();
    public Integer getTotalCharactersInCompressedFile();
    public void writeHeader(IOutputStream destination) throws IOException;
    public void readHeader(IInputStream source) throws IOException;

    void setTotalCharactersInUncompressedFile(Integer totalCharactersInUncompressedFile);
    void setTotalCharactersInCompressedFile(Integer totalCharactersInCompressedFile);
    void setContent(Byte[] content) throws IOException;
    void setSize(Integer size);
}
