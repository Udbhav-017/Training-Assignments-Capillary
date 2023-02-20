package com.filezipper.iostreams;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public final class FileOutputStream implements IOutputStream{
    private OutputStream fos;
    public FileOutputStream(String destinationFilePath) throws IOException {
        this.fos = new BufferedOutputStream(new java.io.FileOutputStream(new File(destinationFilePath)));
    }
    @Override
    public void write(int data) throws IOException{
        fos.write(data);
    }

    public void write(Byte[] data) throws IOException{
        for(Byte b: data)
            fos.write(b);
    }

    public void finalize() throws Throwable{
        if(fos!=null) {
            fos.flush();
            fos.close();
        }
    }
}
