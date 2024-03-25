package Exercise3;

public class LinkedQueue<E> {
    private Node<E> head; // points to the first node of the queue
    private Node<E> tail; // points to the last node of the queue
    private int size; // keeps track of the number of elements in the queue

    // default constructor to initialize an empty queue
    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // checks if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // adds an element to the end of the queue
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    // removes and returns the element from the front of the queue
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E result = head.element;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return result;
    }

    // appends all elements of Q2 to the end of this queue
    public void concatenate(LinkedQueue<E> Q2) {
        // if Q2 is empty, there's nothing to do
        if (Q2.isEmpty()) {
            return;
        }
        // if this queue is empty, just point head and tail to Q2's nodes
        if (isEmpty()) {
            head = Q2.head;
            tail = Q2.tail;
        } else {
            // otherwise, link the two together
            tail.next = Q2.head;
            tail = Q2.tail;
        }
        // adjust the size
        size += Q2.size;
        // make sure Q2 is now empty
        Q2.head = Q2.tail = null;
        Q2.size = 0;
    }

    // supporting node class for the linked list
    private static class Node<E> {
        E element;
        Node<E> next;

        // constructor for creating a new node
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
    }
    
    // test the concatenate method
    public static void main(String[] args) {
        LinkedQueue<Integer> Q1 = new LinkedQueue<>();
        LinkedQueue<Integer> Q2 = new LinkedQueue<>();

        // let's enqueue some numbers onto Q1
        Q1.enqueue(1);
        Q1.enqueue(2);
        Q1.enqueue(3);

        // and some numbers onto Q2
        Q2.enqueue(4);
        Q2.enqueue(5);

        // now concatenate Q2 onto the end of Q1
        Q1.concatenate(Q2);

        // let's dequeue from Q1 to see the order of elements
        while (!Q1.isEmpty()) {
            System.out.print(Q1.dequeue() + " ");
        }
        // should output 1 2 3 4 5

        // Q2 should now be empty
        System.out.println("\nQ2 is empty: " + Q2.isEmpty()); // should output true
    }
}
