package com.list.linkedlist;

import java.util.NoSuchElementException;

/**
 * LinkedList
 * time - complexity :
 * add, remove : O(1)
 * search, insert : o(n)
 *
 * @author 송훤출
 * @since 20.04.28
 */
public class MyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(E data) {
        if (head == null) {
            head = tail = new Node<>(data);
            size++;
            return;
        }

        final Node<E> newNode = new Node<>(data, null, head);
        head.prev = newNode;
        head = newNode;
        size++;
    }


    public void add(E data) {
        if (head == null) {
            head = tail = new Node<>(data);
            size++;
            return;
        }

        final Node<E> newNode = new Node<>(data, tail, null);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }
    }

    public void insert(int index, E data) {
        checkRange(index);

        if (head == null) {
            head = tail = new Node<>(data);
            size++;
            return;
        }

        Node<E> current = head;
        while (--index > 0) {
            current = current.next;
        }

        final Node<E> newNode = new Node<>(data, current, current.next);
        current.next = newNode;
        current.next.prev = newNode;
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        head = head.next;
        if (head == null) { // GC help
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        tail = tail.prev;
        if (tail == null) { // GC help
            head = null;
        } else {
            tail.next = null;
        }
        size--;
    }

    public void remove(int index) {
        checkRange(index);

        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            explicitRemove(index);
        }
        size--;
    }

    private void explicitRemove(int index) {
        Node<E> current = head;
        while (--index > 0) {
            current = current.next;
        }

        current.next = current.next.next;
        current.next.prev = current;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }

    public Iterator<E> iterator() {
        return new itr();
    }

    static class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private class itr implements Iterator<E> {
        private int cursor;
        private Node<E> last;
        private Node<E> next;

        public itr() {
            this.cursor = 0;
            this.last = null;
            this.next = head;
        }

        @Override
        public boolean hasNext() {
            return cursor - size < 0;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                last = next;
                next = next.next;
                cursor++;
                return last.data;
            }
            throw new NoSuchElementException();
        }
    }
}
