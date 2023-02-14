package fileUtility;

public class TerminalNode implements ITreeNode {
    int weight;
    char character;

    TerminalNode(int weight, char character) {
        this.weight = weight;
        this.character = character;
    }

    public char getCharacter() {
        return this.character;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setLeft(ITreeNode node) {
    }

    @Override
    public ITreeNode getLeft() {
        return null;
    }

    @Override
    public void setRight(ITreeNode node) {
    }

    @Override
    public ITreeNode getRight() {
        return null;
    }
}
