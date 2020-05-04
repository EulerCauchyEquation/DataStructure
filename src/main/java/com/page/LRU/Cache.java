package com.page.LRU;

import java.util.Stack;

/**
 * LRU Algorithm
 *
 * @author 송훤출
 * @since 20.05.04
 */
public class Cache {
    private int frameSize;
    private int miss;

    public Cache(int frameSize) {
        this.frameSize = frameSize;
    }

    public void execute(int[] pages) {
        final Stack<Integer> pageList = new Stack<>();

        for (int page : pages) {
            if (pageList.contains(page)) {
                pageList.removeElement(page);
            } else {
                if (pageList.size() == frameSize) {
                    pageList.removeElementAt(0);
                }
                miss++;
            }
            pageList.push(page);
        }
    }

    public int getPageDefault() {
        return miss;
    }
}
