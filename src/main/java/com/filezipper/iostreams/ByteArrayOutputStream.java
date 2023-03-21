package com.filezipper.iostreams;


import java.io.FileOutputStream;
import java.io.IOException;

public final class ByteArrayOutputStream implements IOutputStream{
    private java.io.ByteArrayOutputStream bos;
    String destinationPath;
    public ByteArrayOutputStream(String destinationFilePath) throws IOException {
        this.bos = new java.io.ByteArrayOutputStream();
        this.destinationPath=destinationFilePath;
    }
    @Override
    public void write(int data) throws IOException{
        bos.write(data);
    }

    public void write(Byte[] data) throws IOException{
        for(Byte b: data)
            bos.write(b);
    }

    public void finalize() throws Throwable{
        if(bos!=null) {
            FileOutputStream f = new FileOutputStream(destinationPath);
            f.write(bos.toByteArray());
            f.close();
            bos.flush();
            bos.close();
        }
    }
}
