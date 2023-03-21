package com.filezipper.iostreams;

import java.io.FileInputStream;
import java.io.IOException;

public class ByteArrayInputStream implements IInputStream{

    private java.io.ByteArrayInputStream bis;
    private String sourcePath;
    public ByteArrayInputStream(String sourceFilePath) throws IOException {
        this.bis = new java.io.ByteArrayInputStream(new FileInputStream(sourceFilePath).readAllBytes());

        this.sourcePath = sourceFilePath;
    }

    @Override
    public int read() throws IOException {
        return bis.read();
    }

    @Override
    public Byte[] readNBytes(Integer numberOfBytes) throws IOException {
        Byte[] data = new Byte[numberOfBytes];

        for(int i=0; i<numberOfBytes; i++){
            data[i] = (byte) bis.read();
        }
        return data;
    }

    @Override
    public Integer available() throws IOException {
        return bis.available();
    }

    @Override
    public void reset() throws IOException {
        bis.reset();
    }

    @Override
    public void finalize() throws Throwable {
        bis=null;
    }
}
