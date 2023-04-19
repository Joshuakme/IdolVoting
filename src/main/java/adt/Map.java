/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Collection;
import java.util.Set;

/**
 * @author Joshua Koh Min En
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface Map<K,V> {
    
    void clear();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    Set<Map.Entry<K,V>> entrySet();
    V get(Object key);
    boolean isEmpty();
    Set<K> keySet();
    V put(K key, V value);
    void putAll(Map<? extends K, ? extends V> m);
    V remove(Object key);
    int size();
    Collection<V> values();
    
    
    public class Entry<K,V> {
        //boolean equals(Object o);
        //K getKey();
        //V getValue();
        //V setValue(V value);
    }
}
