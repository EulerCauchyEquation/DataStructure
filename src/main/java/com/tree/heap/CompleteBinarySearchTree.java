package com.tree.heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Complete Binary Tree ( Priority Queue )
 * time - complexity : O(log N)
 *
 * @author 송훤출
 * @since 20.04.25
 */
public class CompleteBinarySearchTree<E> {
    private static final int DEFAULT_CAPACITY = 1 << 2;

    private Object[] elementData;
    private int treeSize;
    private Comparator<? super E> comparator;

    public CompleteBinarySearchTree() {
        this(null);
    }

    public CompleteBinarySearchTree(Comparator<? super E> comparator) {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.treeSize = 0;
        this.comparator = comparator;
    }

    /**
     * heap sort
     * time - complexity : O(n log n)
     */
    public static void sort(Object[] arr) {
        CompleteBinarySearchTree<Object> tree
                = new CompleteBinarySearchTree<>();

        for (Object o : arr) {
            tree.offer(o);
        }

        int i = 0;
        while (!tree.isEmpty()) {
            arr[i++] = tree.poll();
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return treeSize;
    }

    public void offer(E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        ensureCapacity(treeSize);
        explicitOffer(data);
    }

    private void ensureCapacity(int oldCapacity) {
        if (oldCapacity - elementData.length < 0) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @SuppressWarnings("unchecked")
    private void explicitOffer(E data) {
        int current = treeSize;
        int parent = current >> 1;

        while (current > 0) {
            int comp = compare(data, (E) elementData[parent]);
            if (comp >= 0) {
                break;
            }
            elementData[current] = elementData[parent];
            current = parent;
            parent = current >> 1;
        }
        elementData[current] = data;
        treeSize++;
    }

    @SuppressWarnings("unchecked")
    private int compare(E data1, E data2) {
        return comparator == null
                ? ((Comparable<? super E>) data1).compareTo(data2)
                : comparator.compare(data1, data2);
    }

    public Object peek() {
        return isEmpty() ? null : elementData[0];
    }

    @SuppressWarnings("unchecked")
    public Object poll() {
        if (isEmpty()) {
            return null;
        }

        int last = --treeSize;
        Object result = elementData[0];
        elementData[0] = elementData[last];
        elementData[last] = null;

        explicitPoll((E) elementData[0]);
        return result;
    }

    @SuppressWarnings("unchecked")
    private void explicitPoll(E data) {
        int current = 0;
        int half = treeSize >> 1;

        while (current < half) {
            int child = (current << 1) + 1;
            int right = child + 1;
            if (right < treeSize
                    && compare((E) elementData[child], (E) elementData[right]) > 0) {
                child = right;
            }
            E c = (E) elementData[child];

            if (compare(data, c) <= 0) {
                break;
            }
            elementData[current] = c;
            current = child;
        }
        elementData[current] = data;
    }
}
