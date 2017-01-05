import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by up_ding on 02/12/2016.
 */
public class Bag<Item> implements Iterable<Item> {
    private int N; // size of the Bag
    private Node<Item> first; // beginning of bag

    // linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // initialize the bag
    public Bag() {
        N = 0;
        first = null;
    }

    // examine the bag is empty or not
    public boolean isEmpty() {
        return first == null;
    }

    //return size of the Bag
    public int size() {
        return N;
    }

    // add item to bag
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    // returns but not remove the latest recently added item
    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException("Bag empty");
        return first.item;
    }

    // returns String representation of the Bag
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Item item : this)
            s.append(item + " ");
        return  s.toString();
    }

    // return an iterator iterate output the item in LIFO order
    public Iterator<Item> iterator() {
        return  new BagIterator<Item>(first);
    }
    // an implementation of the iterator without remove() since it's optimal
    private class BagIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        public BagIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(!item.equals("-"))
                bag.add(item);
        }
        StdOut.println(bag);

    }
}
