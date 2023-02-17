package com.filezipper;

import com.filezipper.iostreams.FileInputStream;
import com.filezipper.iostreams.FileOutputStream;
import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;

import java.io.IOException;

public class Zipper {
    private final IZipperAlgorithm zipAlgorithm;

    public Zipper(IZipperAlgorithm zipAlgorithm){
        this.zipAlgorithm = zipAlgorithm;
    }

    public void zip(String sourcePath, String destinationPath) throws Throwable {
        IInputStream source = new FileInputStream(sourcePath);
        IOutputStream destination = new FileOutputStream(destinationPath);

        zipAlgorithm.compress(source, destination);
        source.finalize();
        destination.finalize();
    }

    public void unzip(String sourcePath, String destinationPath) throws Throwable {
        IInputStream source = new FileInputStream(sourcePath);
        IOutputStream destination = new FileOutputStream(destinationPath);

        zipAlgorithm.decompress(source, destination);
        source.finalize();
        destination.finalize();
    }
}
