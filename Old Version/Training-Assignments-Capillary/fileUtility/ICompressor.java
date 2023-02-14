package fileUtility;

import java.io.IOException;
import java.io.Serializable;

public interface ICompressor {
    void compress(String source) throws IOException;
}
