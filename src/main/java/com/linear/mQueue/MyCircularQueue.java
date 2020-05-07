package com.linear.mQueue;

import java.util.NoSuchElementException;

/**
 * Circular Queue
 *
 * @author 송훤출
 * @since 20.05.07
 */
public class MyCircularQueue<E> {
    private Node<E> front;
    private Node<E> rear;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void offer(E data) {
        if (rear == null) {
            front = rear = new Node<>(data);
            front.next = front.prev = front;
            size++;
            return;
        }

        final Node<E> newNode = new Node<>(rear, data, front);
        rear.next = newNode;
        front.prev = newNode;
        rear = newNode;
        size++;
    }

    public E poll() {
        if (front == null) {
            throw new NoSuchElementException();
        }

        final E deleteItem = front.item;
        front = front.next;
        front.prev = rear;
        size--;

        if (isEmpty()) {
            front = rear = null;
        }

        return deleteItem;
    }

    public E peek() {
        if (front == null) {
            throw new NoSuchElementException();
        }

        return front.item;
    }

    public boolean contains(Object o) {
        Node<E> current = front;

        while (current != null) {
            if (current.item.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Object[] toArray() {
        final Object[] array = new Object[size];
        Node<E> current = front;

        int i = 0;
        while (i < size) {
            array[i++] = current.item;
            current = current.next;
        }
        return array;
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private static class Node<E> {
        private E item;
        private Node<E> prev;
        private Node<E> next;

        public Node(E item) {
            this(null, item, null);
        }

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class Itr implements Iterator<E> {
        private Node<E> cursor;
        private int index;

        public Itr() {
            this.cursor = front;
            this.index = size;
        }

        @Override
        public boolean hasNext() {
            return index != 0;
        }

        @Override
        public E next() {
            if (index == 0) {
                throw new NoSuchElementException();
            }

            final E item = cursor.item;
            cursor = cursor.next;
            return item;
        }

        @Override
        public void remove() {
            if (index == 0) {
                throw new NoSuchElementException();
            }

            cursor.prev.next = cursor.next;
            cursor.next.prev = cursor.prev;
            cursor = cursor.next;
            index--;

            if (index == 0) {
                front = rear = cursor = null;
            }
        }
    }
}
