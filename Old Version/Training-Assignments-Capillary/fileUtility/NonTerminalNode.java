package fileUtility;

public class NonTerminalNode implements ITreeNode {
    int weight;
    ITreeNode left;
    ITreeNode right;

    NonTerminalNode(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void setLeft(ITreeNode node) {
        this.left = node;

    }

    @Override
    public ITreeNode getLeft() {
        return this.left;
    }

    @Override
    public void setRight(ITreeNode node) {
        this.right = node;

    }

    @Override
    public ITreeNode getRight() {
        return this.right;
    }
}
