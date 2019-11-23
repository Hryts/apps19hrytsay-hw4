package ua.edu.ucu.tries;

public class RWayTrie implements Trie {

    private static int r = 26;
    private Node root;

    @Override
    public void add(Tuple tuple) {
        root = put(root, tuple, 0);
    }

    private Node put(Node node, Tuple tuple, int d) {
        if (node == null) { node = new Node(); }
        if (tuple.getTerm().length() == d) {node.setValue(tuple); return node;}
        node.setNext(put(node, tuple, d+1), tuple.getTerm().charAt(d));
        return node;
    }

    @Override
    public boolean contains(String word) {
        return get(word).getValue() != null;
    }

    public Node get(String key) {
        Node node = get(root, key, 0);
        if (node == null) return null;
        return node;
    }

    private Node get(Node node, String  key, int d) {
        if (node == null) { return null; }
        if (d == key.length()) return node;
        char c = key.charAt(d);
        return get(node.getNext(c), key, d+1);
    }

    @Override
    public boolean delete(String word) {
        int wLength = word.length();
        Node[] wordInTree = new Node[wLength];
        wordInTree[0] = get(root, word, 0);
        if (wordInTree[0] == null) {
            return false;
        }

        for (int i = 1; i < wLength; ++i) {
            wordInTree[i] = wordInTree[i-1].getNext(word.charAt(i));
            if (wordInTree[i] == null) {
                return false;
            }
        }

        Node currentNext = null;
        boolean notToBeRemoved = wordInTree[wLength-1].hasNextExcept(currentNext);
        if (notToBeRemoved) {
            wordInTree[wLength-1].setValue(new Tuple("", 0));
            return true;
        }
        currentNext = wordInTree[wLength-1];
        for(int i = wLength-2; i >= 0; --i) {
            if (!notToBeRemoved) {
                wordInTree[i].setNext(null, word.charAt(i));
            }
            notToBeRemoved = wordInTree[i].hasNextExcept(currentNext);
        }
        return true;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node startNode = root;
        if (s != "") {
            startNode = get(s);
        }
        
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
