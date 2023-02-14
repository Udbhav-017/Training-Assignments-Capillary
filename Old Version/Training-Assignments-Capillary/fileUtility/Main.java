package fileUtility;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String source1 = "C:\\Users\\rudbh\\Desktop\\Testing\\fie2.txt";
        String source2 = "C:\\Users\\rudb\\h\\Desktop\\Testing\\file2_compressed.txt";

        IZipper zip = new HuffmanZipper();
        long start = System.nanoTime();
		zip.compress(source1);
//        zip.decompress(source2);
        long end = System.nanoTime();

        System.out.print("Exc Time : "+(end-start)/1000000);
    }
}
