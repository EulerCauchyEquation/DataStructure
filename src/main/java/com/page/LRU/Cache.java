package com.page.LRU;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * LRU Algorithm
 *
 * @author 송훤출
 * @since 20.05.04
 */
public class Cache {
    private int frameSize;
    private int miss;

    private final Map<Integer, CacheItem> pageMap = new HashMap<>();
    private CacheItem head;
    private CacheItem tail;

    public Cache(int frameSize) {
        this.frameSize = frameSize;
    }

    public void execute(int[] pages) {
        for (int page : pages) {
            if (pageMap.containsKey(page)) {
                remove(page);
            } else {
                if (isFull()) {
                    removeFirst();
                }
                miss++;
            }
            add(page);
        }
    }

    private void add(int page) {
        CacheItem newItem = new CacheItem(page);
        pageMap.put(page, newItem);

        if (head == null) {
            head = tail = newItem;
            return;
        }

        tail.next = newItem;
        newItem.prev = tail;
        tail = newItem;
    }

    private void remove(int page) {
        CacheItem deleteItem = pageMap.get(page);
        if (deleteItem == null) {
            throw new NoSuchElementException();
        }

        if (deleteItem.prev != null) {
            deleteItem.prev.next = deleteItem.next;
        } else {
            head = head.next;
            head.prev = null;
        }
        if (deleteItem.next != null) {
            deleteItem.next.prev = deleteItem.prev;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        pageMap.remove(page);
    }

    private void removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        pageMap.remove(head.item);

        head = head.next;
        head.prev = null;
    }

    private boolean isFull() {
        return pageMap.size() == frameSize;
    }

    public int getPageDefault() {
        return miss;
    }

    static class CacheItem {
        private int item;
        private CacheItem prev;
        private CacheItem next;

        public CacheItem(int item) {
            this(null, item, null);
        }

        public CacheItem(CacheItem prev, int item, CacheItem next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
