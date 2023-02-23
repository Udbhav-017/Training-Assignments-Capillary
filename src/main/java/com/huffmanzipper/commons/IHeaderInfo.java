package com.huffmanzipper.commons;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.utilities.IMap;

import java.io.IOException;

public interface IHeaderInfo {
     void setHeaderInfoObject(IMap<String, Integer> countMap, IMap<String, String> huffBitCodeMap) throws IOException;
     Object getContent() throws IOException, ClassNotFoundException;
     Integer getSize();
     Integer getTotalCharactersInUncompressedFile();
     Integer getTotalCharactersInCompressedFile();
     void writeHeader(IOutputStream destination) throws IOException;
     void readHeader(IInputStream source) throws IOException;

    void setTotalCharactersInUncompressedFile(Integer totalCharactersInUncompressedFile);
    void setTotalCharactersInCompressedFile(Integer totalCharactersInCompressedFile);
    void setContent(Byte[] content) throws IOException;
    void setSize(Integer size);
}
