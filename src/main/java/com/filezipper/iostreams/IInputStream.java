package com.filezipper.iostreams;

import java.io.IOException;

public interface IInputStream {
    public int read() throws IOException;
    public Byte[] readNBytes(Integer numberOfBytes) throws IOException;
    public Integer  available() throws IOException;
    public void reset() throws IOException;
    public void finalize() throws Throwable;
}
