// Could not find probehashmap for exercise 1, i sent you an email but did not get a response so i am submitting with only these two 

package Exercise1;

import java.util.ArrayList;
import java.util.Random;

// abstract base class for creating hash maps
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
    protected int n = 0; // num of entries
    protected int capacity; // total cap also size of hash table
    private int prime; // just the average prime number
    private long scale, shift; // coefficients
    private float maxLoadFactor; // max load factor before re sizing

    // constructor to init a map with given load factor prime etc etc 
    public AbstractHashMap(int cap, int p, float loadFactor) {
        prime = p;
        capacity = cap;
        maxLoadFactor = loadFactor;
        if (loadFactor <= 0 || loadFactor > 1)
            throw new IllegalArgumentException("Load factor must be > 0 and <= 1");
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1; // prime - prime-1 (smthng like that)
        shift = rand.nextInt(prime); // random numbers
        createTable(); // Create the initial table
    }

    // additional constructors for different scenarios
    public AbstractHashMap(int cap, float loadFactor) { this(cap, 109345121, loadFactor); } // 
    public AbstractHashMap(float loadFactor) { this(17, 109345121, loadFactor); } // default cap and prime
    public AbstractHashMap(int cap) { this(cap, 0.5f); } // defualt load factor
    public AbstractHashMap() { this(17, 0.5f); } // default capacity and load factor

    // returns the number of entries in the map
    @Override
    public int size() { return n; }

    // retrieves the value associated with a given key
    @Override
    public V get(K key) { return bucketGet(hashValue(key), key); }

    // inserts or replaces an entry in the map
    @Override
    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (n > capacity * maxLoadFactor) // If load factor exceeded, resize the map
            resize(2 * capacity - 1); // New capacity is roughly double the current capacity
        return answer;
    }

    // removes an entry from the map
    @Override
    public V remove(K key) { return bucketRemove(hashValue(key), key); }

    // romputes the hash value for a key
    private int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    // resizes the hash table to a new capacity
    private void resize(int newCap) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for (Entry<K,V> e : entrySet()) buffer.add(e);
        capacity = newCap;
        createTable(); // Create a new table with updated capacity
        n = 0; // Reset entry count
        for (Entry<K,V> e : buffer) put(e.getKey(), e.getValue()); // Rehash entries
    }

    // Abstract methods that subclasses must implement
    protected abstract void createTable();
    protected abstract V bucketGet(int h, K k);
    protected abstract V bucketPut(int h, K k, V v);
    protected abstract V bucketRemove(int h, K k);
}
