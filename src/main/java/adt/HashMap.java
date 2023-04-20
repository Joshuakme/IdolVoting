package adt;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author Joshua Koh Min En
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class HashMap<K,V> implements Map<K,V>, Cloneable, Serializable {
    // Data Fields
    private Node<K,V>[] array;
    private int capacity;
    private int size;
    
    // Constructor

    public HashMap(int capacity) {
        this.capacity = capacity;
        array = (Node<K,V>[]) new Node[capacity];
    }
    
    // Getter
    private int getIndex(K key) {
        int hash = key.hashCode();
        hash = hash < 0 ? -hash : hash;
        return hash % capacity;
    }

    
     
    // Abstract Methods
    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndex((K)key);
        Node<K,V> head = array[index];
        
        while(head != null) {
            if(head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Node<K,V> node : array) {
            while(node != null) {
                if(node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public Set entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public V get(Object key) {
        int index = getIndex((K)key);
        Node<K,V> head = array[index];
        while(head != null) {
            if(head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(Node<K,V> node : array) {
            while(node != null) {
                set.add(node.key);
                node = node.next;
            }
        }
        return set;
    }

    @Override
    public V put(Object key, Object value) {
        int index = getIndex(key);
        Node<K,V> head = array[index];
        while(head != null) {
            if(head.key.equals(key)) {
                V oldValue = head.value;
                head.value = value;
                return oldValue;
            }
            head = head.next;
        }
        
        Node<K,V> newNode = new Node<>(key, value);
        newNode.next = array[index];
        array[index] = newNode;
        size++;
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        int index = getIndex((K)key);
        Node<K,V> head = array[index];
        Node<K,V> prev = null;
        while(head != null) {
            if(head.key.equals(key)) {
                if(prev == null) {
                    array[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Collection values() {
        List<V> list = new ArrayList<>();
        for(Node<K,V> node : array) {
            while(node != null) {
                list.add(node.value);
                node = node
    }
    
    // Class Within Class
    public class Node<K,V> {
        
    }
    
    public class head {
        
    }
}
