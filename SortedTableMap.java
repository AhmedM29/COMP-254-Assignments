//commented better so that i dont forget how this works 

package Exercise2;


import java.util.ArrayList;
import java.util.Comparator;

// An abstract class that provides a skeletal implementation of a map that has its keys sorted
abstract class AbstractSortedMap<K,V> implements Comparator<K> {
    private Comparator<K> comp; // Comparator to determine the order of keys

    // Constructor with a given comparator
    protected AbstractSortedMap(Comparator<K> c) { comp = c; }

    // Default constructor which uses the natural ordering of the keys
    protected AbstractSortedMap() { this(new DefaultComparator<>()); }

    // Inner class that defines the default comparator using the natural ordering
    private static class DefaultComparator<K> implements Comparator<K> {
        public int compare(K a, K b) throws ClassCastException {
            return ((Comparable<K>) a).compareTo(b);
        }
    }

    // Uses the specified comparator to compare two keys
    public int compare(K a, K b) {
        return comp.compare(a, b);
    }

    // Uses the specified comparator to compare a key with an entry's key
    protected int compare(K a, Entry<K,V> b) {
        return comp.compare(a, b.getKey());
    }

    // An interface representing a key-value pair (entry) in the map
    public interface Entry<K, V> {
        K getKey(); // Method to get the key
        V getValue(); // Method to get the value
    }

    // Validates a key by trying to compare it to itself
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0); // Checks if the key can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key"); // Throws if the key is not compatible with the comparator
        }
    }
}

// Concrete class for map entries, which implements the Entry interface
class MapEntry<K, V> implements AbstractSortedMap.Entry<K, V> {
    private K k; // The key
    private V v; // The value

    public MapEntry(K key, V value) {
        k = key;
        v = value;
    }

    // Methods to get and set the key and value
    public K getKey() { return k; }
    public V getValue() { return v; }
    public V setValue(V value) {
        V old = v;
        v = value;
        return old; // Returns the old value
    }
}

// A concrete implementation of a sorted map using a list (table) to store the entries
public class SortedTableMap<K, V> extends AbstractSortedMap<K, V> {
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>(); // List to hold the map entries

    // Constructors
    public SortedTableMap() { super(); }
    public SortedTableMap(Comparator<K> comp) { super(comp); }

    // ... rest of the SortedTableMap methods ...

    // Implementation of containsKey method to determine if a key is in the map
    public boolean containsKey(K key) {
        int j = findIndex(key); // Find the index of the key
        // Check if index is valid and if the key at index j matches the provided key
        if (j >= 0 && j < size() && compare(key, table.get(j)) == 0) {
            return true;
        }
        return false;
    }

    // Utility method to find the index of a key using binary search
    private int findIndex(K key) {
        int low = 0;
        int high = table.size() - 1;

        // Binary search loop
        while (low <= high) {
            int mid = (low + high) / 2; // Calculate the midpoint
            int compResult = compare(key, table.get(mid)); // Compare the key with the key at midpoint

            if (compResult == 0) {
                return mid; // Key found at the midpoint
            } else if (compResult < 0) {
                high = mid - 1; // Search in the lower half
            } else {
                low = mid + 1;  // Search in the upper half
            }
        }
        // Key not found; return a negative value indicating the insertion point
        return -(low + 1);
    }

    // Method to get the size of the map
    public int size() {
        return table.size();
    }

    // Main method to test the SortedTableMap
    public static void main(String[] args) {
        // Instantiate a SortedTableMap for testing
        SortedTableMap<Integer, String> map = new SortedTableMap<>();

        // add entries to the map
        map.table.add(new MapEntry<>(1, "One"));
        map.table.add(new MapEntry<>(2, "Two"));
        map.table.add(new MapEntry<>(3, "Three"));

        // testing w different keys 
        boolean containsKey1 = map.containsKey(1); 
        boolean containsKey2 = map.containsKey(2); 
        boolean containsKey3 = map.containsKey(3); 
        boolean containsKey4 = map.containsKey(4); 
        
        // print
        System.out.println("Contains key 1? " + containsKey1);
        System.out.println("Contains key 2? " + containsKey2);
        System.out.println("Contains key 3? " + containsKey3);
        System.out.println("Contains key 4? " + containsKey4);

        // gosh adding this was a problem
        if (containsKey1 && containsKey2 && containsKey3 && !containsKey4) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed."); // tests didnt pass :(
        }
    }
}
