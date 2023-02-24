package com.huffmanzipper.wordcharimplementation;

import com.filezipper.IZipperAlgorithm;
import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.huffmanzipper.commons.AbstractCompressor;
import com.huffmanzipper.commons.AbstractDecompressor;
import com.huffmanzipper.commons.HeaderInfoFreqMapImpl;
import com.huffmanzipper.wordbasedimplementation.compression.WordBasedCompressionImpl;
import com.huffmanzipper.wordbasedimplementation.decompression.WordBasedDecompressionImpl;
import com.huffmanzipper.wordcharimplementation.compression.WordCharBasedCompressionImpl;
import com.huffmanzipper.wordcharimplementation.decompression.WordCharBasedDecompressionImpl;

import java.io.IOException;

public class WordCharBasedHuffman implements IZipperAlgorithm {
    private final AbstractCompressor huffman_compressor;
    private final AbstractDecompressor huffman_decompressor;

    public WordCharBasedHuffman(){
        this.huffman_compressor = new WordCharBasedCompressionImpl(new HeaderInfoFreqMapImpl());
        this.huffman_decompressor = new WordCharBasedDecompressionImpl(new HeaderInfoFreqMapImpl());
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
