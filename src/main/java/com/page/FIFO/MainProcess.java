package com.page.FIFO;

import java.util.LinkedList;
import java.util.Queue;

/**
 * FIFO Page Replacement Algorithm
 *
 * @author 송훤출
 * @since 20.05.04
 */
public class MainProcess {
    private final Queue<Integer> pageTable = new LinkedList<>();
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
        if (pageTable.contains(page)) {
            return;
        }

        if (pageTable.size() == frameSize) {
            pageTable.poll();
        }
        pageTable.offer(page);
        miss++;
    }

    public int getPageDefault() {
        return miss;
    }
}
