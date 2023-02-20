package com.filezipper;

import com.filezipper.iostreams.*;

public class Zipper {
    private final IZipperAlgorithm zipAlgorithm;

    public Zipper(IZipperAlgorithm zipAlgorithm){
        this.zipAlgorithm = zipAlgorithm;
    }

    public void zip(String sourcePath, String destinationPath) throws Throwable {
        IInputStream source = new FileInputReader(sourcePath);
        IOutputStream destination = new FileOutputWriter(destinationPath);

        zipAlgorithm.compress(source, destination);
        source.finalize();
        destination.finalize();
    }

    public void unzip(String sourcePath, String destinationPath) throws Throwable {
        IInputStream source = new FileInputReader(sourcePath);
        IOutputStream destination = new FileOutputWriter(destinationPath);

        zipAlgorithm.decompress(source, destination);
        source.finalize();
        destination.finalize();
    }
}
