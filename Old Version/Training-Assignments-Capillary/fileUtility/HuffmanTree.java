package fileUtility;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;

class HuffmanTree implements Comparator<HuffmanTree> {
    ITreeNode root;

    @Override
    public int compare(HuffmanTree h1, HuffmanTree h2) {
        int result;
        if (h1.root.getWeight() > h2.root.getWeight()) {
            result = 1;
        } else {
            result = -1;
        }
        return result;
    }

    public HashMap<Character, String> getCharacterBitEncodings() {
        HashMap<Character, String> characterBitCodes = new HashMap<>();

        if (this.root instanceof TerminalNode) {
            characterBitCodes.put(((TerminalNode) this.root).getCharacter(), "0");
            return characterBitCodes;
        }

        StringBuilder code = new StringBuilder();

        createBitCodes(this.root.getLeft(), code, 0, characterBitCodes);

        createBitCodes(this.root.getRight(), code, 1, characterBitCodes);


        return characterBitCodes;
    }

    private void createBitCodes(ITreeNode node, StringBuilder output, int move, HashMap<Character, String> characterBitCodes) {
        if (node == null) return;
        output.append(move);
        if (node instanceof NonTerminalNode) {
            createBitCodes(node.getLeft(), output, 0, characterBitCodes);
            createBitCodes(node.getRight(), output, 1, characterBitCodes);
        }
        if (node instanceof TerminalNode) {
            characterBitCodes.put(((TerminalNode) node).getCharacter(), output.toString());
        }
        output.deleteCharAt(output.length() - 1);
    }

    public HashMap<String, Character> getBitDecodings() {
        HashMap<String, Character> bitDecodings = new HashMap<>();

        if (this.root instanceof TerminalNode) {
            bitDecodings.put("0", ((TerminalNode) this.root).getCharacter());
            return bitDecodings;
        }

        StringBuilder code = new StringBuilder();

        bitDecodeHelper(this.root.getLeft(), code, 0, bitDecodings);

        bitDecodeHelper(this.root.getRight(), code, 1, bitDecodings);

        return bitDecodings;
    }

    private void bitDecodeHelper(ITreeNode node, StringBuilder output, int move, HashMap<String, Character> bitDecodings) {
        if (node == null) return;
        output.append(move);
        if (node instanceof NonTerminalNode) {
            bitDecodeHelper(node.getLeft(), output, 0, bitDecodings);
            bitDecodeHelper(node.getRight(), output, 1, bitDecodings);
        }
        if (node instanceof TerminalNode) {
            bitDecodings.put(output.toString(), ((TerminalNode) node).getCharacter());
        }
        output.deleteCharAt(output.length() - 1);
    }
}
