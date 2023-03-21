package com.filezipper;

import com.filezipper.iostreams.*;
import com.filezipper.statistics.Stats;

public class Zipper {
    private final IZipperAlgorithm zipAlgorithm;

    public Zipper(IZipperAlgorithm zipAlgorithm){
        this.zipAlgorithm = zipAlgorithm;
    }

    public void zip(String sourcePath, String destinationPath) throws Throwable {
        IInputStream source = new FileInputStream(sourcePath);
        IOutputStream destination = new FileOutputStream(destinationPath);

        long cstart = System.currentTimeMillis();
        zipAlgorithm.compress(source, destination);
        long cend  = System.currentTimeMillis();
        Stats.totalCompressionTimeInMilliSecs =  (cend-cstart);


    }

    public void unzip(String sourcePath, String destinationPath) throws Throwable {
        IInputStream source = new FileInputStream(sourcePath);
        IOutputStream destination = new FileOutputStream(destinationPath);

        long dstart  = System.currentTimeMillis();
        zipAlgorithm.decompress(source, destination);
        long dend  = System.currentTimeMillis();
        Stats.totalDecompressionTimeInMilliSecs =  (dend-dstart);


    }
}
