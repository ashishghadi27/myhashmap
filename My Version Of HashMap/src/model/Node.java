package model;

import java.util.Map;
import java.util.Objects;

public class Node<K, V> implements Map.Entry<K, V> {

    private final int hash;
    private final K key;
    private V value;
    private Node<K,V> next;

    public Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V preVal = this.value;
        this.value = value;
        return preVal;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o instanceof Map.Entry){
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>)o;
            return Objects.equals(this.key, entry.getKey()) && Objects.equals(this.value, entry.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

}
