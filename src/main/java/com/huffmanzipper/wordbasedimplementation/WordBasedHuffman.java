package com.huffmanzipper.wordbasedimplementation;

import com.filezipper.IZipperAlgorithm;
import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.huffmanzipper.commons.AbstractCompressor;
import com.huffmanzipper.commons.AbstractDecompressor;
import com.huffmanzipper.commons.HeaderInfoFreqMapImpl;
import com.huffmanzipper.wordbasedimplementation.compression.WordBasedCompressionImpl;
import com.huffmanzipper.wordbasedimplementation.decompression.WordBasedDecompressionImpl;

import java.io.IOException;

public class WordBasedHuffman implements IZipperAlgorithm{
    private final AbstractCompressor huffman_compressor;
    private final AbstractDecompressor huffman_decompressor;

    public WordBasedHuffman(){
        this.huffman_compressor = new WordBasedCompressionImpl(new HeaderInfoFreqMapImpl());
        this.huffman_decompressor = new WordBasedDecompressionImpl(new HeaderInfoFreqMapImpl());
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