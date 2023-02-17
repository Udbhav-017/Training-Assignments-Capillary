package com.filezipper;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;

import java.io.IOException;

public interface  IZipperAlgorithm {
     void compress(IInputStream source, IOutputStream destination) throws IOException;
     void decompress(IInputStream source, IOutputStream destination) throws IOException, ClassNotFoundException;
}

