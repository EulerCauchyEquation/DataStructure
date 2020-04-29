package com.linear.mQueue;

import java.util.NoSuchElementException;

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
        if (data == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            head = tail = new Node<>(data);
            size++;
            return;
        }

        final Node<E> newNode = new Node<>(null, data, head);
        head.prev = newNode;
        head = newNode;
        size++;
    }

    public void add(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            head = tail = new Node<>(data);
            size++;
            return;
        }

        final Node<E> newNode = new Node<>(tail, data, null);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void insert(int index, E data) {
        checkRange(index);

        if (data == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            head = tail = new Node<>(data);
            size++;
            return;
        }

        Node<E> current = head;
        while (--index > 0) {
            current = current.next;
        }
        final Node<E> newNode = new Node<>(current, data, current.next);
        current.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        head = head.next;
        head.prev = null;
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        tail = tail.prev;
        tail.next = null;
        size--;
    }

    public void remove(int index) {
        checkRange(index);

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            explicitRemove(index);
        }
    }

    private void explicitRemove(int index) {
        Node<E> current = head;
        while (--index > 0) {
            current = current.next;
        }
        current.next = current.next.next;
        current.next.prev = null;
        current.next.prev = current;
        size--;
    }

    public E getFirst() {
        return isEmpty()
                ? null
                : head.data;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        Node<E> current = head;
        while (current != null) {
            result[i++] = current.data;
            current = current.next;
        }
        return result;
    }

    private static class Node<E> {

        private final E data;
        private Node<E> prev;
        private Node<E> next;

        public Node(E data) {
            this(null, data, null);
        }

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
