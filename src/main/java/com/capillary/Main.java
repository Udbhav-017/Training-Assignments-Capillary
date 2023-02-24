package com.capillary;

import com.filezipper.Zipper;
import com.huffmanzipper.defaultimplementation.DefaultHuffman;
import com.huffmanzipper.wordbasedimplementation.WordBasedHuffman;
import com.huffmanzipper.wordcharimplementation.WordCharBasedHuffman;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Throwable {
        String original = "/home/udbhavgupta/IdeaProjects/input.txt";
        String compressed = "/home/udbhavgupta/IdeaProjects/comp.txt";
        String decompressed = "/home/udbhavgupta/IdeaProjects/final.txt";

        Zipper z = new Zipper(new WordCharBasedHuffman());

        long cstart = System.currentTimeMillis();
        z.zip(original,compressed);
        long cend  = System.currentTimeMillis();
        Logger.getLogger(Main.class.getName()).log(Level.INFO,"Compressed, Time Taken -> "+(cend-cstart));

        z.unzip(compressed, decompressed);
        long dend  = System.currentTimeMillis();
        Logger.getLogger(Main.class.getName()).log(Level.INFO,"Decompressed, Time Taken -> "+(dend-cend));
    }
}
