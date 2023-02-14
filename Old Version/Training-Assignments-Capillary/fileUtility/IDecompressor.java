package fileUtility;

import java.io.IOException;

public interface IDecompressor {
    void decompress(String source) throws IOException, ClassNotFoundException;
}
