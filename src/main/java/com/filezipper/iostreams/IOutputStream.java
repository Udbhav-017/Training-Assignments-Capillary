package com.filezipper.iostreams;

import java.io.IOException;

public interface IOutputStream {
    public void write(int data) throws IOException;
    public void write(Byte[] data) throws IOException;
    public void finalize() throws Throwable;
}
