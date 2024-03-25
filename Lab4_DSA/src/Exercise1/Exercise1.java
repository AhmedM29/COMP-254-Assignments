package Exercise1;

import java.util.Iterator;

interface Position<E> {
    E getElement() throws IllegalStateException;
}

interface PositionalList<E> {
    int size();
    boolean isEmpty();
    Position<E> first();
    Position<E> last();
    Position<E> before(Position<E> p) throws IllegalArgumentException;
    Position<E> after(Position<E> p) throws IllegalArgumentException;
    Position<E> addFirst(E e);
    Position<E> addLast(E e);
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
    E set(Position<E> p, E e) throws IllegalArgumentException;
    E remove(Position<E> p) throws IllegalArgumentException;
}

class LinkedPositionalList<E> implements PositionalList<E> {
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }
        
        public E getElement() throws IllegalStateException {
            if (next == null) // convention for defunct node
                throw new IllegalStateException("Position no longer valid");
            return element;
        }
        
        public Node<E> getPrev() {
            return prev;
        }
        
        public Node<E> getNext() {
            return next;
        }
        
        public void setElement(E e) {
            element = e;
        }
        
        public void setPrev(Node<E> p) {
            prev = p;
        }
        
        public void setNext(Node<E> n) {
            next = n;
        }
    }
    
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    
    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Invalid position");
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null)
            throw new IllegalArgumentException("Position no longer in the list");
        return node;
    }
    
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer)
            return null;
        return node;
    }
    
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public Position<E> first() { return position(header.getNext()); }
    public Position<E> last() { return position(trailer.getPrev()); }
    
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }
    
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }
    
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }
    
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }
    
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }
    
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }
    
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }
    
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E temp = node.getElement();
        node.setElement(null); // help with garbage collection
        node.setNext(null); // and convention for defunct node
        node.setPrev(null);
        return temp;
    }
}

class Exercise1<E> extends LinkedPositionalList<E> {
    public int indexOf(Position<E> p) {
        if (p == null)
            return -1;
        
        int index = 0;
        Position<E> current = first();
        while (current != null) {
            if (current == p)
                return index;
            index++;
            current = after(current);
        }
        return -1; // p not found in the list
    }
    public class  Main1 {
        public static void main(String[] args) {
            Exercise1<Integer> list = new Exercise1<>();
            
            // Adding some elements to the list
            Position<Integer> p1 = list.addLast(10);
            Position<Integer> p2 = list.addLast(20);
            Position<Integer> p3 = list.addLast(30);
            
            // Testing indexOf method
            System.out.println("Index of p1: " + list.indexOf(p1)); // Should print 0
            System.out.println("Index of p2: " + list.indexOf(p2)); // Should print 1
            System.out.println("Index of p3: " + list.indexOf(p3)); // Should print 2
        }
    }
}



