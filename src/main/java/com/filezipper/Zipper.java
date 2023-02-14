package com.filezipper;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;

public class Zipper {
    private final IZipperAlgorithm zipAlgorithm;

    Zipper(IZipperAlgorithm zipAlgorithm){
        this.zipAlgorithm = zipAlgorithm;
    }

    public void compress(IInputStream source, IOutputStream destination){
        zipAlgorithm.compress(source, destination);
    }

    public void decompress(IInputStream source, IOutputStream destination){
         zipAlgorithm.decompress(source, destination);
    }
}
