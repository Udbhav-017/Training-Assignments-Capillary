package com.huffmanzipper;

import com.filezipper.IZipperAlgorithm;
import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;

public class Huffman implements IZipperAlgorithm {
    private final AbstractCompressor huffman_compressor;
    private final AbstractDecompressor  huffman_decompressor;
    Huffman(AbstractCompressor huffman_compressor, AbstractDecompressor huffman_decompressor){
        this.huffman_compressor = huffman_compressor;
        this.huffman_decompressor = huffman_decompressor;
    }

    @Override
    public void compress(IInputStream source, IOutputStream destination) {
         huffman_compressor.compress(source, destination);
    }

    @Override
    public void decompress(IInputStream source, IOutputStream destination) {
         huffman_decompressor.decompress(source, destination);
    }
}
