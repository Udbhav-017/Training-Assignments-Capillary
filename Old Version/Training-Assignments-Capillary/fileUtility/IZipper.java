package fileUtility;

import java.io.IOException;

public interface IZipper {
    void compress(String source) throws IOException;

    void decompress(String source) throws IOException, ClassNotFoundException;
}
