package com.filezipper.iostreams;

import java.io.*;
import java.nio.charset.StandardCharsets;

public final class FileInputReader implements  IInputStream{
    private Reader fis;
    private String sourcePath;
    public FileInputReader(String sourceFilePath) throws IOException {
        this.fis = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(sourceFilePath), StandardCharsets.UTF_8));

        this.sourcePath = sourceFilePath;
    }

    @Override
    public Byte read() throws IOException{
        return (byte) fis.read();
    }
    @Override
    public Byte[] readNBytes(Integer noOfBytes) throws IOException{
        Byte[] data = new Byte[noOfBytes];
        int ind = 0;
        while (ind<noOfBytes)
            data[ind++] = (byte) fis.read();

        return data;
    }

    @Override
    public Integer available() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reset() throws IOException {
        fis.close();
        fis = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(sourcePath), StandardCharsets.UTF_8));
    }

    @Override
    public void finalize() throws Throwable{
        if(fis!=null)
            fis.close();
    }
}
