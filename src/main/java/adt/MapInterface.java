package adt;

/**
 * @author Joshua Koh Min En
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface MapInterface<K,V> {
    
    void clear();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    V get(Object key);
    boolean isEmpty();
    V put(K key, V value);
    V remove(Object key);
    int size();
    
    
    public interface Entry<K,V> {
        K getKey();
        V getValue();
        V setValue(V value);
        boolean equals(Object o);
        int hashCode();
    }
}
