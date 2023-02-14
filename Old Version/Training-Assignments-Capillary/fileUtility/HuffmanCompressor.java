package fileUtility;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanCompressor implements ICompressor {
    HeaderInformation headerInfo;

    @Override
    public void compress(String source) throws IOException {
        if (new File(source).length() == 0) return;

        Path sourcePath = Paths.get(source);
        String destination = sourcePath.getParent() + "//" + sourcePath.getFileName().toString().replaceFirst("[.][^.]+$", "") + "_compressed.txt";

        headerInfo = new HeaderInformation();

        HashMap<Character, Integer> frequencyTable = createFrequencyTable(source);
        HuffmanTree htree = createHuffmanTree(frequencyTable);

        HashMap<Character, String> characterBitCodes = htree.getCharacterBitEncodings();

        ByteArrayOutputStream bot = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bot);
        out.writeObject(characterBitCodes);
        byte[] serializedCharacterBitCodes = bot.toByteArray();
        bot.close();
        out.close();

        headerInfo.setContent(serializedCharacterBitCodes);
        headerInfo.setSize(bot.size());

        HuffmanEncoder(source, destination, characterBitCodes);
        appendHeader(destination);

        System.out.println("Compressed");
    }

    private HashMap<Character, Integer> createFrequencyTable(String source) throws IOException {

        HashMap<Character, Integer> frequencyTable = new HashMap<>();
        FileInputStream file = new FileInputStream(source);
        BufferedInputStream br = new BufferedInputStream(file);

        int num = 0;
        char ch;
        int totalCharacters = 0;

        while ((num = br.read()) != -1) {
            totalCharacters++;
            ch = (char) num;
            int prevCount = frequencyTable.getOrDefault(ch, 0);
            frequencyTable.put(ch, prevCount + 1);
        }

        headerInfo.setTotalCharactersInUncompressedFile(totalCharacters);

        if (br != null)
            br.close();

        return frequencyTable;
    }

    private HuffmanTree createHuffmanTree(HashMap<Character, Integer> frequencyTable) {
        PriorityQueue<HuffmanTree> mpq = new PriorityQueue<>(new HuffmanTree());

        for (Entry<Character, Integer> row : frequencyTable.entrySet()) {
            HuffmanTree tree = new HuffmanTree();
            tree.root = new TerminalNode(row.getValue(), row.getKey());
            mpq.add(tree);
        }

        while (mpq.size() > 1) {
            HuffmanTree tree1 = mpq.poll();
            HuffmanTree tree2 = mpq.poll();

            HuffmanTree treeNew = new HuffmanTree();

            int combinedWeight = tree1.root.getWeight() + tree2.root.getWeight();

            treeNew.root = new NonTerminalNode(combinedWeight);
            treeNew.root.setLeft(tree1.root);
            treeNew.root.setRight(tree2.root);
            mpq.add(treeNew);
        }

        return mpq.poll();
    }

    private void HuffmanEncoder(String source, String destination, HashMap<Character, String> characterBitCodes) throws IOException {

        FileInputStream file = new FileInputStream(source);
        BufferedInputStream br = new BufferedInputStream(file);
        BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(destination));


        int num = 0;                // for reading from file
        char ch;                    // for storing character conversion of int
        int totalCharacters = 0;    // total characters in output file
        int bitCount = 0;            // tracking number of bits for writing 1 byte to file
        int buffer = 0;             // bits to be written in output file
        boolean padRequired = true;

        int headerBytes = 12 + headerInfo.getSize();

        while (headerBytes-- != 0) {
            byte f = 0;
            bw.write(f);
        }

        while ((num = br.read()) != -1) {
            ch = (char) num;

            String code = characterBitCodes.get(ch);

            for (char bit : code.toCharArray()) {
                buffer = buffer << 1;
                if (bit == '1')
                    buffer = buffer ^ 1;
                bitCount++;
                padRequired = true;
                if (bitCount == 8) {
                    totalCharacters++;
                    byte b = (byte) buffer;

                    bw.write(b);
                    buffer = 0;
                    bitCount = 0;
                    padRequired = false;
                }
            }
        }

        if (padRequired) {
            totalCharacters++;
            buffer = buffer << (8 - bitCount);
            byte b = (byte) buffer;
            bw.write(b);
        }

        headerInfo.setTotalCharactersInCompressedFile(totalCharacters);

        if (br != null)
            br.close();
        if (bw != null)
            bw.close();
    }

    private void appendHeader(String filePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(0);
        file.writeInt(headerInfo.getTotalCharactersInUncompressedFile());
        file.writeInt(headerInfo.getTotalCharactersInCompressedFile());
        file.writeInt(headerInfo.getSize());
        file.write(headerInfo.getContent());
        file.close();
    }
}
