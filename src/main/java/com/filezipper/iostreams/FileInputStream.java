package com.filezipper.iostreams;

import java.io.IOException;

public final class FileInputStream implements  IInputStream{
    private final java.io.FileInputStream fis;

    FileInputStream(String sourceFilePath) throws IOException {
        this.fis = new java.io.FileInputStream(sourceFilePath);
    }

    @Override
    public Byte read() throws IOException{
        return (byte) fis.read();
    }

    @Override
    public Integer available() throws IOException {
        return fis.available();
    }

    protected void finalize() throws Throwable{
        if(fis!=null)
            fis.close();
    }
}
