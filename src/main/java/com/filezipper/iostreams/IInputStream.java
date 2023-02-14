package com.filezipper.iostreams;

import java.io.IOException;

public interface IInputStream {
    public Byte read() throws IOException;
    public Integer  available() throws IOException;
}
