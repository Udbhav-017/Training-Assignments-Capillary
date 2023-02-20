package com.capillary;

import com.filezipper.Zipper;
import com.huffmanzipper.defaultimplementation.DefaultHuffman;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Throwable {
        String original = "/home/udbhavgupta/IdeaProjects/input2.txt";
        String compressed = "/home/udbhavgupta/IdeaProjects/comp.txt";
        String decompressed = "/home/udbhavgupta/IdeaProjects/final.txt";

        Zipper z = new Zipper(new DefaultHuffman());
        z.zip(original,compressed);
        Logger.getLogger(Main.class.getName()).log(Level.INFO,"Compressed!!");

        z.unzip(compressed, decompressed);
        Logger.getLogger(Main.class.getName()).log(Level.INFO,"Decompressed!!");
    }
}
