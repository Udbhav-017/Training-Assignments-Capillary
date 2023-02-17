package com.huffmanzipper.defaultimplementation;

import com.filezipper.IZipperAlgorithm;
import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.huffmanzipper.commons.AbstractCompressor;
import com.huffmanzipper.commons.AbstractDecompressor;
import com.huffmanzipper.defaultimplementation.compression.CompressorImpl;
import com.huffmanzipper.defaultimplementation.decompression.DecompressorImpl;

import java.io.IOException;

public class DefaultHuffman implements IZipperAlgorithm {
    private final AbstractCompressor huffman_compressor;
    private final AbstractDecompressor huffman_decompressor;
    public DefaultHuffman(){
        this.huffman_compressor = new CompressorImpl();
        this.huffman_decompressor = new DecompressorImpl();
    }

    DefaultHuffman(AbstractCompressor huffman_compressor, AbstractDecompressor huffman_decompressor){
        this.huffman_compressor = huffman_compressor;
        this.huffman_decompressor = huffman_decompressor;
    }

    @Override
    public void compress(IInputStream source, IOutputStream destination) throws IOException {
         huffman_compressor.compress(source, destination);
    }

    @Override
    public void decompress(IInputStream source, IOutputStream destination) throws IOException, ClassNotFoundException {
         huffman_decompressor.decompress(source, destination);
    }
}