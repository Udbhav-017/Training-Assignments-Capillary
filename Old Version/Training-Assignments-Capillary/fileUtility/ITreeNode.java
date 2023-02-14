package fileUtility;

interface ITreeNode {
    int getWeight();

    void setLeft(ITreeNode node);

    ITreeNode getLeft();

    void setRight(ITreeNode node);

    ITreeNode getRight();
}