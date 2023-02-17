package com.huffmanzipper.commons;

import com.filezipper.iostreams.IInputStream;
import com.filezipper.iostreams.IOutputStream;
import com.filezipper.utilities.Deserialization;
import com.filezipper.utilities.IMap;
import com.filezipper.utilities.IMapEntry;
import com.filezipper.utilities.Serialization;

import java.io.IOException;
import java.nio.ByteBuffer;

public class HeaderInfoImpl implements IHeaderInfo{
    private Integer size;
    private Integer totalCharactersInUncompressedFile;
    private Integer totalCharactersInCompressedFile;
    private Byte[] content;

    @Override
    public void setHeaderInfoObject(IMap countMap, IMap huffBitCodeMap) throws IOException {
        int totalCharactersInUncompressed = 0;
        int totalCharactersInCompressed = 0;

        for(IMapEntry entry: countMap.getEntryArray()){
            totalCharactersInUncompressed += (int)entry.getValue();
            totalCharactersInCompressed += (int)entry.getValue()*String.valueOf(huffBitCodeMap.get(entry.getKey())).length();
        }
        totalCharactersInCompressed = (int)Math.ceil(totalCharactersInCompressed/(8.0));

        setTotalCharactersInUncompressedFile(totalCharactersInUncompressed);
        setTotalCharactersInCompressedFile(totalCharactersInCompressed);

        Byte[] content = Serialization.serialize(huffBitCodeMap);
        setContent(content);

        setSize(this.content.length);
    }

    @Override
    public Object getContent() throws IOException, ClassNotFoundException {
        return Deserialization.deserialize(this.content);
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Integer getTotalCharactersInUncompressedFile() {
        return this.totalCharactersInUncompressedFile;
    }

    @Override
    public Integer getTotalCharactersInCompressedFile() {
        return this.totalCharactersInCompressedFile;
    }

    @Override
    public void writeHeader(IOutputStream destination) throws IOException {
        Byte[] data = new Byte[12];
        ByteBuffer bb = ByteBuffer.allocate(12);
        bb.putInt(this.totalCharactersInUncompressedFile)
                .putInt(this.totalCharactersInCompressedFile)
                .putInt(this.size);

        byte[] b = bb.array();
        int c=0;
        for(Byte by:b)
            data[c++] = by;

        destination.write(data);
        destination.write(this.content);
    }

    @Override
    public void readHeader(IInputStream source) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(12);
        for(int i=0; i<12; i++){
            bb.put((byte)source.read());
        }
        bb.flip();
        setTotalCharactersInUncompressedFile(bb.getInt());
        setTotalCharactersInCompressedFile(bb.getInt());
        setSize(bb.getInt());
        setContent(source.readNBytes(this.getSize()));
    }

    @Override
    public void setTotalCharactersInUncompressedFile(Integer totalCharactersInUncompressedFile) {
        this.totalCharactersInUncompressedFile = totalCharactersInUncompressedFile;
    }

    @Override
    public void setTotalCharactersInCompressedFile(Integer totalCharactersInCompressedFile) {
        this.totalCharactersInCompressedFile = totalCharactersInCompressedFile;
    }

    @Override
    public void setContent(Byte[] content) throws IOException {
        this.content = content;
    }

    @Override
    public void setSize(Integer size) {
        this.size = size;
    }
}
