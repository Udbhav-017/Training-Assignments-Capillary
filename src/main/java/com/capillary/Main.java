package com.capillary;

import com.filezipper.Zipper;
import com.filezipper.statistics.Stats;
import com.huffmanzipper.defaultimplementation.DefaultHuffman;
import com.huffmanzipper.wordbasedimplementation.WordBasedHuffman;
import com.huffmanzipper.wordcharimplementation.WordCharBasedHuffman;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Throwable {
        String original = "/home/udbhavgupta/IdeaProjects/input.txt";
        String compressed = "/home/udbhavgupta/IdeaProjects/comp.txt";
        String decompressed = "/home/udbhavgupta/IdeaProjects/final.txt";

        Stats.fileName= original;
        Zipper z = new Zipper(new DefaultHuffman());

        z.zip(original,compressed);

        z.unzip(compressed, decompressed);

        File cmp= new File(compressed);
        Stats.compressedFileSizeInBytes = cmp.length();

        Stats.logStats();
    }
}
