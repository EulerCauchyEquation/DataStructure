package com.list.arraylist;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ArrayList : 배열리스트
 * insert, remove, contains : O(N)
 * search : O(1)
 *
 * @author 송훤출
 * @since 20.04.28
 */
public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 1 << 1;

    private Object[] elementData;
    private Comparator<? super E> comparator;
    private int size;

    public MyArrayList() {
        this(null);
    }

    public MyArrayList(Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void add(E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        ensureCapacity(size + 1);
        elementData[size++] = data;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            int newCapacity = minCapacity + (minCapacity >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public void set(int index, E data) {
        validateRange(index);

        if (data == null) {
            throw new NullPointerException();
        }

        ensureCapacity(size + 1);
        elementData[index] = data;
    }

    private void validateRange(int index) {
        if (index < 0 || index >= elementData.length) {
            throw new IllegalArgumentException();
        }
    }

    public Object get(int index) {
        validateRange(index);

        return elementData[index];
    }

    public void remove(int index) {
        validateRange(index);

        for (int i = index; i < size() - 1; i++) {
            elementData[index] = elementData[index + 1];
        }
        elementData[--size] = null;
    }

    public boolean contains(Object o) {
        for (Object e : elementData) {
            if (e.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor - size() < 0;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                int lastIndex = cursor++;
                return elementData[lastIndex];
            }
            throw new ArrayIndexOutOfBoundsException();
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(cursor);
        }
    }
}
