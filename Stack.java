import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by up_ding on 02/12/2016.
 */
public class Stack<Item> implements Iterable<Item> {//generics
    private int N; // the size of the Stack, namely the number of Node
    private Node<Item> first; // The top of the Stack, all the operations of the Stack in on the top

    private class Node<Item> { // the data-structure of List, which is combine of Nodes have item and pointer to the next
        private Item item;  // item is the content of the Node
        private Node<Item> next; // next is like a pointer to the next Node
    }

    public Stack() { // The constructor of Stack, which no need to has a parameter int N, cause can not predict the
        // operations on the stack
        N = 0;
        first = null;
    }

    // to examine wether the Stack is empty or not
    public boolean isEmpty() {
        return first == null;
    }

    // return the size of the Stack
    public int size() {
        return N;
    }

    // push an item to the top of the Stack
    public void push(Item item) {
        Node<Item> oldfirst = first; // make the first Node to be the Node under the top Node
        first = new Node<Item>(); // make space on the memory to store the new Node, first has existed,
        // if the statement is Node<Item> first = new Node<Item>, which is wrong
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    // pop the top Node of the Stack
    public Item pop() {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow"); // examine whether the Stack is empty
        Item item = first.item; // because the Stack is not empty, so there exists at least a Node, namely N>=1
        first = first.next; // No need to consider the loitering problem, because this statement make the GC system
        // clean memory automatically
        N--;
        return item;
    }

    // return (but not remove) the Item most recently added to the Stack
    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    // return a String representation of the Stack
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Item item : this)
            s.append(item + " ");
        return  s.toString();
    }

    // returns a iterator to this Stack that iterates through the items in LIFO order
    public Iterator<Item> iterator(){
        return new StackIterator<Item>(first);
    }

    // a iterator without remove method since it is optimal
    private class StackIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        public StackIterator(Node<Item> first) {// the constructor has a parameter since iterator should has a entrance
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
        Stack<String> stack = new Stack<String>();
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(!item.equals("-")) stack.push(item);
            else if(!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println();
        StdOut.println("(" + stack.size() + " left in the stack)");
        StdOut.println(stack);
    }

}
