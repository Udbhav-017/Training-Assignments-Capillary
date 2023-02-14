package fileUtility;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class HuffmanDecompressor implements IDecompressor {
    HeaderInformation headerInfo;

    @Override
    public void decompress(String source) throws IOException, ClassNotFoundException {
        headerInfo = new HeaderInformation();

        readHeader(source);

        ByteArrayInputStream bot = new ByteArrayInputStream(headerInfo.getContent());
        ObjectInputStream out = new ObjectInputStream(bot);
        HashMap<Character, String> characterBitCodes = (HashMap<Character, String>) out.readObject();
        bot.close();
        out.close();

        HashMap<String, Character> decodings = getDecodings(characterBitCodes);

        Path sourcePath = Paths.get(source);
        String destination = sourcePath.getParent() + "//" + sourcePath.getFileName().toString().replaceFirst("[.][^.]+$", "") + "_uncompressed.txt";

        HuffmanDecoder(source, destination, decodings);

        System.out.println("Uncompressed");
    }

    private HashMap<String, Character> getDecodings(HashMap<Character, String> characterBitCodes){
        HashMap<String, Character> decodings = new HashMap<>();

        for (Map.Entry<Character,String> entry : characterBitCodes.entrySet()){
            decodings.put(entry.getValue() , entry.getKey());
        }
        return decodings;
    }
    private void readHeader(String filePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(0);
        headerInfo.setTotalCharactersInUncompressedFile(file.readInt());
        headerInfo.setTotalCharactersInCompressedFile(file.readInt());
        headerInfo.setSize(file.readInt());

        int currentByteIndex = 0;
        byte[] serializedFrequencyMap= new byte[headerInfo.getSize()];

        while(currentByteIndex != headerInfo.getSize())
            serializedFrequencyMap[currentByteIndex++] = file.readByte();

        file.close();

        headerInfo.setContent(serializedFrequencyMap);
    }

    private void HuffmanDecoder(String source, String destination, HashMap<String, Character> characterBitCodes) throws IOException {
        FileInputStream file = new FileInputStream(source);
        BufferedInputStream br = new BufferedInputStream(file);

        BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(destination));

        int skipbytes = 12 + headerInfo.getSize();

        while (skipbytes-- > 0)
            br.read();

        int noOfCharactersToScan = headerInfo.getTotalCharactersInUncompressedFile();

        String code = "";
//		String tempBytes = "";
        while (true) {
            byte num = (byte) br.read();

//			tempBytes +=num;
            int bitsLeft = 8;
            while (bitsLeft-- > 0) {
                int bit = ((num) >> 7) & 1;
                code = code + bit;
//				System.out.println("num ="+num + " code ="+code);
                num <<= 1;
                if (characterBitCodes.containsKey(code)) {
                    char decodedCharacter = characterBitCodes.get(code);
                    bw.write(decodedCharacter);
                    code = "";
                    if (--noOfCharactersToScan == 0) {
                        br.close();
                        bw.close();
                        return;
                    }
                }
            }
        }
//		System.out.println(tempBytes);
    }
}
