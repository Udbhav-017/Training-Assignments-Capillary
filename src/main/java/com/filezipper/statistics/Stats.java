package com.filezipper.statistics;

import com.capillary.Main;
import com.huffmanzipper.commons.AbstractCompressor;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Stats {
    public static String fileName;
    public static long headerSizeInBytes;
    public static long compressedDataSizeInBytes;
    public static long compressedFileSizeInBytes;
    public static double avgSymbolLength;
    public static long totalCompressionTimeInMilliSecs;
    public static long totalDecompressionTimeInMilliSecs;
    public static long createFrequencyTableTime;
    public static long createHuffmanTreeTime;
    public static long generateHuffmanCodesTime;
    public static long huffmanEncoderTime;
    public static long readHeaderTime;
    public static long getHuffCodeMapFromHeaderTime;
    public static long decoderTime;

    public static void logStats(){
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("/home/udbhavgupta/IdeaProjects/stats.log",  true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("========================================================================");
            logger.info("File Nmae -> "+fileName);
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            logger.info("createFrequencyTableTime-> "+createFrequencyTableTime);
            logger.info("createHuffmanTreeTime-> "+createHuffmanTreeTime);
            logger.info("generateHuffmanCodesTime-> "+generateHuffmanCodesTime);
            logger.info("huffmanEncoderTime-> "+huffmanEncoderTime);
            logger.info("Compressed, Time Taken-> "+totalCompressionTimeInMilliSecs);
            logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            logger.info("readHeaderTime-> "+readHeaderTime);
            logger.info("getHuffCodeMapFromHeaderTime-> "+getHuffCodeMapFromHeaderTime);
            logger.info("decoderTime-> "+decoderTime);
            logger.info("Decompressed, Time Taken-> "+totalDecompressionTimeInMilliSecs);
            logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            logger.info("Header Size-> "+headerSizeInBytes);
            logger.info("Compressed Data Size(Without Header)-> "+compressedDataSizeInBytes);
            logger.info("Compressed File Size-> "+compressedFileSizeInBytes);
            logger.info("Bits/Character-> "+avgSymbolLength);
            logger.info("========================================================================");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
