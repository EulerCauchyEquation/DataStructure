package com.page.SCR;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Second Chance Replacement Algorithm
 *
 * @author 송훤출
 * @since 20.05.07
 */
public class MainProcess {
    private final HashMap<Integer, PageItem> pageTable;
    private final int frameSize;
    private PageItem front;
    private PageItem rear;
    private int miss;

    public MainProcess() {
        this(3);
    }

    public MainProcess(int frameSize) {
        this.pageTable = new HashMap<>();
        this.frameSize = frameSize;
    }

    public void execute(int[] pages) {
        for (int page : pages) {
            usePage(page);
        }
    }

    private void usePage(int page) {
        if (pageTable.containsKey(page)) {
            update(page);
            return;
        }

        if (isFull()) {
            int deletePage = findPageToRemove();
            remove(deletePage);
        }
        miss++;
        add(page);
    }

    private void update(int page) {
        final PageItem pageItem = pageTable.get(page);
        pageItem.referenceBit = 1;
    }

    private int findPageToRemove() {
        PageItem current = front;

        while (current != null) {
            if (current.referenceBit == 0) {
                return current.item;
            }
            current.referenceBit = 0;
            current = current.next;
        }

        throw new NoSuchElementException();
    }

    private void remove(int page) {
        final PageItem pageItem = pageTable.get(page);

        if (pageItem == front) {
            front = front.next;
        } else if ((pageItem == rear)) {
            rear = rear.prev;
        }

        pageItem.prev.next = pageItem.next;
        pageItem.next.prev = pageItem.prev;
        pageTable.remove(page);
    }

    private void add(int page) {
        final PageItem newItem = new PageItem(page);
        pageTable.put(page, newItem);

        if (front == null) {
            front = rear = newItem;
            front.next = front.prev = front;
            return;
        }

        rear.next = newItem;
        newItem.prev = rear;
        newItem.next = front;
        front.prev = newItem;
        rear = newItem;
    }

    private boolean isFull() {
        return frameSize == pageTable.size();
    }

    public int getPageDefault() {
        return miss;
    }

    private static class PageItem {
        private final int item;
        private int referenceBit;
        private PageItem prev;
        private PageItem next;

        public PageItem(int item) {
            this(null, item, null);
        }

        public PageItem(PageItem prev, int item, PageItem next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
