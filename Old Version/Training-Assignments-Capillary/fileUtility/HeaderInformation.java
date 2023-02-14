package fileUtility;

class HeaderInformation {
    private int size;
    private int totalCharactersInUncompressedFile;
    private int totalCharactersInCompressedFile;
    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size >= 0)
            this.size = size;

    }

    public int getTotalCharactersInUncompressedFile() {
        return totalCharactersInUncompressedFile;
    }

    public void setTotalCharactersInUncompressedFile(int totalCharactersInUncompressedFile) {
        if (size >= 0)
            this.totalCharactersInUncompressedFile = totalCharactersInUncompressedFile;
    }

    public int getTotalCharactersInCompressedFile() {
        return totalCharactersInCompressedFile;
    }

    public void setTotalCharactersInCompressedFile(int totalCharactersInCompressedFile) {
        if (size >= 0)
            this.totalCharactersInCompressedFile = totalCharactersInCompressedFile;
    }
}
