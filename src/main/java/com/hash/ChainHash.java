package com.hash;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * hash (분리 체인법)
 *
 * @param <K> key
 * @param <V> value
 * @author hwun chul
 * @since 20.04.29
 */
public class ChainHash<K, V> {
    private static final int CAPACITY = 1 << 4;

    private Node<K, V>[] table;
    private int size;
    private int tableSize;

    @SuppressWarnings("unchecked")
    public ChainHash() {
        this.table = new Node[CAPACITY];
        this.tableSize = CAPACITY;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        int hash = getHashCode(key);
        Node<K, V> current = table[hash];

        while (current != null) {
            if (key.equals(current.key)) {
                // already exist value.
                return;
            }
            current = current.next;
        }
        Node<K, V> newNode = new Node<>(key, value, table[hash]);
        table[hash] = newNode;
        size++;
    }

    private int getHashCode(K key) {
        return Objects.hashCode(key) % tableSize;
    }

    public boolean containsKey(K key) {
        int hash = getHashCode(key);
        Node<K, V> current = table[hash];

        while (current != null) {
            if (key.equals(current.key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public V get(K key) {
        int hash = getHashCode(key);
        Node<K, V> current = table[hash];

        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        int hash = getHashCode(key);
        Node<K, V> current = table[hash];
        Node<K, V> prev = null;

        while (current != null) {
            if (key.equals(current.key)) {
                if (prev == null) {
                    table[hash] = null;
                } else {
                    prev.next = prev.next.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }

        throw new NoSuchElementException();
    }

    static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
