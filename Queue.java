import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by up_ding on 02/12/2016.
 */
public class Queue<Item> implements Iterable<Item> {
    private int N; // the size of the queue
    private Node<Item> first; // the beginning of the queue
    private Node<Item> last; // the end of the queue

    // linked list class
    private  class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // initialize an empty queue
    public Queue() {
        N = 0;
        first = null;
        last = null;
    }

    // to examine the queue whether is empty
    public boolean isEmpty() {
        return  first == null;
    }

    // return the size of the queue
    public int size() {
        return  N;
    }

    // add an item to the end of the queue
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last; // examine before N++ since, if N++ the queue is no empty no more and the operation
        // before have made the last not equal null any longer
        else oldlast.next = last;
        N++;
    }

    // removes and returns the item on this queue that was least recently added
    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if(isEmpty()) last = null; // to avoid loitering, since first has statement first = first.next, the GC system
        // would automatically collect the memory not use any more. However, system not know last became null, so make
        // last equals null to avoid loitering
        return  item;
    }

    // returns (but not remove) the item least recently added to the queue
    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    // return a String representation of the queue
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Item item : this)
            s.append(item + " ");
        return  s.toString();
    }

    // return an iterator that iterates items in the queue in LILO order
    public Iterator<Item> iterator() {
        return new QueueIterator<Item>(first);
    }

    // an iterator implementation without remove() since it's optimal
    private class QueueIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        public QueueIterator(Node<Item> first) {
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
        Queue<String> queue = new Queue<String>();
        while(!StdIn.isEmpty()) {
           String item = StdIn.readString();
            if(!item.equals("-")) queue.enqueue(item);
            else if(!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();
        StdOut.println("(" + queue.size() + " left in the queue)");
        StdOut.println(queue);
        /*Queue.Node<String> node = new Queue.Node<String>();
        node.item = "ding   test";
        node.next = null;
        StdOut.println(node.item);*/
        /*Queue<Double> test = new Queue<Double>();
        Queue<Double>.QueueIterator<Double> it = test.new QueueIterator(test.first);*/

        // change the class Node<Item> to non-static then
        /*Queue<String> test = new Queue<String>();
        Queue<String>.Node<String> node = test.new Node<String>();
        node.item = "ding test";
        StdOut.println(node.item);*/

    }

}
