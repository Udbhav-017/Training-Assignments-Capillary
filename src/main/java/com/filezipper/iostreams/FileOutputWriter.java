package com.filezipper.iostreams;

import java.io.*;
import java.nio.charset.StandardCharsets;

public final class FileOutputWriter implements IOutputStream{
    private Writer fos;
    public FileOutputWriter(String destinationFilePath) throws IOException {
        this.fos = new BufferedWriter(new OutputStreamWriter(new java.io.FileOutputStream(destinationFilePath), StandardCharsets.UTF_8));
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
