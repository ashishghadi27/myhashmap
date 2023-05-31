package datastructure;

import model.Node;
import utils.MyEntry;

import java.util.*;

public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K,V> {

    @SuppressWarnings({"unchecked"})
    private Node<K, V>[] table = (Node<K, V>[])new Node[9];
    private Set<Map.Entry<K,V>> entrySet;
    private int size = 0;

    public MyHashMap() {
        super();
        Arrays.fill(table, null);
        entrySet = new HashSet<>();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Node<K,V> node : table){
            if(node.getValue().equals(value)) return true;
            if(node.getNext() != null){
                Node<K, V> temp = node;
                while(temp.getNext() != null){
                    temp = temp.getNext();
                    if(temp.getValue().equals(value)) return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        int hKey = hash(key);
        Node<K,V> node = table[hKey];
        if(node == null) return false;
        //System.out.println("CONTAINS Key: " + key + "  Hash: " + hKey + " NODE KEY: " + node.getKey());
        if(node.getKey().equals(key)) return true;
        else {
            Node<K,V> temp = node;
            while(temp.getNext() != null){
                temp = temp.getNext();
                if(temp.getKey().equals(key)) {
                    //System.out.println("KEY INSIDE: " + temp.getKey());
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public V get(Object key) {

        Node<K,V> node = table[hash(key)];

        if(node == null) return null;
        if(node.getKey().equals(key)) return node.getValue();
        else {
            Node<K,V> temp = node;
            while(temp.getNext() != null){
                temp = temp.getNext();
                if(temp.getKey().equals(key)) return temp.getValue();
            }
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        V val = get(key);
        if(val == null) return null;
        if(remove(key, val))
            return val;
        return null;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        entrySet.clear();
    }

    @Override
    public Set<K> keySet() {
        return super.keySet();
    }

    @Override
    public Collection<V> values() {
        return super.values();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean remove(Object key, Object value) {
        int hash = hash(key);
        if(table[hash].getKey() == key && table[hash].getNext() == null){
            table[hash] = null;
            size--;
            return true;
        }
        else if(table[hash].getKey() == key && table[hash].getNext() != null){
            Node<K, V> temp = table[hash];
            table[hash] = temp.getNext();
            temp.setNext(null);
            temp = null;
            size--;
            return true;
        }
        else {
            Node<K, V> temp = table[hash];
            Node<K, V> prev = null;
            while(temp.getNext() != null){
                if(temp.getKey().equals(key)){
                    return remove(prev, temp);
                }
                prev = temp;
                temp = temp.getNext();
            }
            return remove(prev, temp);
        }
    }

    private boolean remove(Node<K,V> prev, Node<K,V> node){
        if(node == null) return false;
        if(node.getNext() == null) {
            if(prev == null) return false;
            prev.setNext(null);
            size--;
            return true;
        }
        else {
            if(prev == null) return false;
            prev.setNext(node.getNext());
            size--;
            return true;
        }
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        int index = hash(key);
        Node<K, V> temp = table[index];
        if(temp.getKey().equals(key) && temp.getValue().equals(oldValue)){
            temp.setValue(newValue);
            return true;
        }
        else {
            temp = temp.getNext();
            while(temp.getNext() != null){
                if(temp.getKey().equals(key) && temp.getValue().equals(oldValue)){
                    temp.setValue(newValue);
                    return true;
                }
                temp = temp.getNext();
            }
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        int index = hash(key);
        Node<K, V> temp = table[index];
        if(temp.getKey().equals(key)){
            V old = temp.getValue();
            temp.setValue(value);
            return old;
        }
        else{
            while(temp.getNext() != null){
                temp = temp.getNext();
                if(temp.getKey().equals(key)){
                    V old = temp.getValue();
                    temp.setValue(value);
                    return old;
                }
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key);
        Node<K, V> node = new Node<>(index, key, value, null);

        if(containsKey(key))
            remove(key);

        if(table[index] == null) {
            table[index] = node;
            size++;
            return node.getValue();
        }
        else {
            Node<K, V> temp = table[index];
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(node);
            size++;
            return node.getValue();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()){
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        entrySet.clear();
        for(Node<K,V> node : table){
            if(node == null) continue;
            Map.Entry<K,V> entry = new MyEntry<>(node.getKey(), node.getValue());
            entrySet.add(entry);
            if(node.getNext() != null){
                Node<K, V> temp = node;
                while(temp.getNext() != null){
                    temp = temp.getNext();
                    entry = new MyEntry<>(temp.getKey(), temp.getValue());
                    entrySet.add(entry);
                }
            }
        }
        return entrySet;
    }

    private int hash(Object key){
        return key == null ? 0 :  key.hashCode() % 9;
    }
}
