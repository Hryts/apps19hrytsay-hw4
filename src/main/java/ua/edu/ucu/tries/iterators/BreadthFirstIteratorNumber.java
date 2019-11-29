package ua.edu.ucu.tries.iterators;

import ua.edu.ucu.tries.Node;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BreadthFirstIteratorNumber implements Iterator<String> {
    private Queue<Node> queue = new LinkedList();
    RWayTrie trie;
    int k;
    String next;

    public BreadthFirstIteratorNumber(RWayTrie trie, String pref, int k) {
        this.trie = trie;
        this.k = k;
        queue.add(trie.get(pref));
    }

    @Override
    public boolean hasNext() {
        if (queue.isEmpty()) {
            return false;
        }
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            System.out.println(current);
            for (Node child : current.getNext()) {
                queue.add(child);
            }
            if (current.isWord()) {
                next = current.getValue().getTerm();
                return true;
            }
        }
        return false;
    }

    @Override
    public String next() {
        if (next == null) {
            throw new NoSuchElementException();
        }
        return next;
    }

    public static Iterable<String> wordsWithPrefix(RWayTrie trie, String pref) {
        return () -> new BreadthFirstIteratorNumber(trie, pref, 0);
    }
}
