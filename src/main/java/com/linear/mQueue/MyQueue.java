package com.linear.mQueue;

import java.util.NoSuchElementException;

/**
 * Queue - FIFO
 * pop, push : O(1)
 *
 * @author hwun chul
 * @since 20.04.29
 */
public class MyQueue<E> {
    private final MyLinkedList<E> queue;
    private int size;

    public MyQueue() {
        this.queue = new MyLinkedList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public E peek() {
        return isEmpty()
                ? null
                : queue.getFirst();
    }

    public void offer(E data) {
        queue.add(data);
    }

    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E result = queue.getFirst();
        queue.removeFirst();
        return result;
    }
}
