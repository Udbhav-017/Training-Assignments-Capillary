package com.filezipper;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;

public interface  IZipperAlgorithm {
     void compress(IInputStream source, IOutputStream destination);
     void decompress(IInputStream source, IOutputStream destination);
}

