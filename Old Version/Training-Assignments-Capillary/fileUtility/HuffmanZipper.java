package fileUtility;

import java.io.IOException;

class HuffmanZipper implements IZipper {
    @Override
    public void compress(String source) throws IOException {
        ICompressor comp = new HuffmanCompressor();
        comp.compress(source);
    }

    @Override
    public void decompress(String source) throws IOException, ClassNotFoundException {
        IDecompressor dcomp = new HuffmanDecompressor();
        dcomp.decompress(source);
    }
}
