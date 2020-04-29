package com.linear.mStack;

import java.util.Arrays;

/**
 * stack - LIFO
 * push, pop : O(1)
 *
 * @author hwun chul
 * @since 20.04.29
 */
public class MyStack<E> {
    private static final int DEFAULT_CAPACITY = 1 << 2;

    private Object[] item;
    private int top;

    public MyStack() {
        this.item = new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return top;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        return isEmpty()
                ? null
                : (E) item[top - 1];
    }

    public void push(E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        ensureCapacity(top);

        item[top++] = data;
    }

    private void ensureCapacity(int top) {
        if (top - item.length < 0) {
            return;
        }
        int newCapacity = top + (top >> 1);
        item = Arrays.copyOf(item, newCapacity);
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) {
            throw new NullPointerException();
        }

        E result = (E) item[--top];
        item[top] = null;
        return result;
    }

    public Object[] toArray() {
        return Arrays.copyOf(item, top);
    }
}
