package Exercise3;

import java.util.ArrayList;

public class HeapPriorityQueue {
    // inner class for the entries of the heap
    static class Entry {
        int key;
        Object value;

        Entry(int k, Object v) {
            key = k;
            value = v;
        }
    }

    private final ArrayList<Entry> heap = new ArrayList<>();

    // method to insert a new entry with k and v
    public void insert(int k, Object v) {
        Entry newEntry = new Entry(k, v);
        heap.add(newEntry); // add new entry to the end of the list
        upheap(heap.size() - 1); // restore heap property
    }

    // recursive upheap method
    private void upheap(int i) {
        if (i > 0) {
            int parentIndex = (i - 1) / 2;
            if (heap.get(i).key < heap.get(parentIndex).key) {
                swap(i, parentIndex);
                upheap(parentIndex); // recur at the position of the parent
            }
        }
    }

    // helpoer mothod that swaps 2 entries
    private void swap(int i, int j) {
        Entry temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // method to remove min element (root)
    public Entry removeMin() {
        if (heap.isEmpty()) {
            return null;
        }
        Entry min = heap.get(0);
        Entry last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            downheap(0);
        }
        return min;
    }

    // recursive downheap method
    private void downheap(int i) {
        int leftIndex = 2 * i + 1;
        int rightIndex = 2 * i + 2;
        int smallest = i;

        if (leftIndex < heap.size() && heap.get(leftIndex).key < heap.get(smallest).key) {
            smallest = leftIndex;
        }

        if (rightIndex < heap.size() && heap.get(rightIndex).key < heap.get(smallest).key) {
            smallest = rightIndex;
        }

        if (smallest != i) {
            swap(i, smallest);
            downheap(smallest);
        }
    }

    public static void main(String[] args) {
        HeapPriorityQueue pq = new HeapPriorityQueue();

        
        // random insertions
        pq.insert(10, "J");
        pq.insert(1, "Y");
        pq.insert(8, "M");
        pq.insert(14, "N");
        pq.insert(13, "L");
        pq.insert(11, "H");
        pq.insert(19, "D");
        pq.insert(16, "E");
        pq.insert(12, "G");
        pq.insert(17, "I");
        pq.insert(18, "O");

        Entry min;
        while ((min = pq.removeMin()) != null) {
            System.out.println(min.key + " - " + min.value);
        }
    }
}
