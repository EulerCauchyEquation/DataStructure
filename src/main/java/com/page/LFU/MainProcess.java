package com.page.LFU;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * LFU : Least Frequently Used
 *
 * @author 송훤출
 * @since 20.05.04
 */
public class MainProcess {
    private final Map<Integer, Integer> pageTable = new HashMap<>();
    private final Queue<Integer> history = new LinkedList<>();
    private final int frameSize;
    private int miss;

    public MainProcess() {
        this(3);
    }

    public MainProcess(int frameSize) {
        this.frameSize = frameSize;
    }

    public void execute(int[] pages) {
        for (int page : pages) {
            usePage(page);
        }
    }

    private void usePage(int page) {
        if (pageTable.containsKey(page)) {
            history.remove(page);
        } else {
            if (pageTable.size() == frameSize) {
                int deletePage = getLeastReferencedPage();

                pageTable.remove(deletePage);
                history.remove(deletePage);
            }
            pageTable.put(page, 1);
            miss++;
        }
        pageTable.compute(
                page,
                (key, value) -> value = (value == null) ? 1 : value + 1);
        history.offer(page);
    }

    private int getLeastReferencedPage() {
        int minPage = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;

        for (int page : history) {
            if (result - pageTable.get(page) > 0) {
                result = pageTable.get(page);
                minPage = page;
            }
        }
        return minPage;
    }

    public int getPageDefault() {
        return miss;
    }
}
