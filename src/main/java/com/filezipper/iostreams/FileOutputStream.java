package com.filezipper.iostreams;

import java.io.IOException;

public final class FileOutputStream implements IOutputStream{
    private final java.io.FileOutputStream fos;
    FileOutputStream(String destinationFilePath) throws IOException {
        this.fos = new java.io.FileOutputStream(destinationFilePath);
    }
    @Override
    public void write(Byte data) throws IOException{
        fos.write(data);
    }

    protected void finalize() throws Throwable{
        if(fos!=null)
            fos.close();
    }
}
