package com.filezipper.iostreams;

import java.io.*;

public final class FileInputStream implements  IInputStream{
    private  InputStream fis;
    private String sourcePath;
    public FileInputStream(String sourceFilePath) throws IOException {
        this.fis = new BufferedInputStream(new java.io.FileInputStream(sourceFilePath));

        this.sourcePath = sourceFilePath;
    }

    @Override
    public int read() throws IOException{
        return fis.read();
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
    public Integer available() throws IOException {
        return fis.available();
    }

    @Override
    public void reset() throws IOException {
        fis.close();
        fis = new BufferedInputStream(new java.io.FileInputStream(sourcePath));
    }
    @Override
    public void finalize() throws Throwable{
        if(fis!=null)
            fis.close();
    }
}
