package ua.edu.ucu.tries;

public class Node {
    private Tuple value;
    private Node[] next = new Node[26];

    public void setNext(Node next, char ch) {
        this.next[ch - 97] = next;
    }

    public void setValue(Tuple value) {
        this.value = value;
    }

    public Tuple getValue() { return value; }

    public Node[] getNext() { return next; }

    public Node getNext(char ch) { return next[ch - 97]; }

    public boolean hasNextExcept(Node next) {
        boolean res = false;
        for (Node node : this.next) {
            if (node != null && node != next) {
                res = true;
            }
        }
        return res;
    }

    public boolean isWord() {
        return value.getTerm() != null;
    }
}
