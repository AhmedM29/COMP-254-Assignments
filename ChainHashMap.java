package Exercise1;

import java.util.ArrayList;

// ChainHashMap class extends AbstractHashMap and uses separate chaining for collision resolution.
public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
    private UnsortedTableMap<K,V>[] table; // Array of UnsortedTableMap to store chains

    // Constructors
    public ChainHashMap() { super(); }
    public ChainHashMap(int cap) { super(cap); }
    public ChainHashMap(int cap, int p) { super(cap, p); }
    public ChainHashMap(int cap, float loadFactor) { super(cap, loadFactor); }
    public ChainHashMap(int cap, int p, float loadFactor) { super(cap, p, loadFactor); }

    // Initializes the table with empty chains
    @Override
    protected void createTable() {
        table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new UnsortedTableMap<>();
        }
    }

    // Retrieves a value by key from the appropriate chain
    @Override
    protected V bucketGet(int h, K k) {
        return table[h].get(k);
    }

    // Inserts or updates a key-value pair in the appropriate chain
    @Override
    protected V bucketPut(int h, K k, V v) {
        V oldValue = table[h].put(k, v);
        if (oldValue == null) n++; // Increment size if it's a new entry
        return oldValue;
    }

    // Removes a key-value pair from the appropriate chain
    @Override
    protected V bucketRemove(int h, K k) {
        V value = table[h].remove(k);
        if (value != null) n--; // Decrement size if an entry was removed
        return value;
    }
    
    // Returns an iterable collection of all key-value pairs in the map
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            for (Entry<K, V> entry : table[h].entrySet())
                buffer.add(entry);
        return buffer;
    }
}
