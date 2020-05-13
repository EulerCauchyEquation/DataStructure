package com.page.LFU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LFU : Least Frequently Used
 *
 * @author 송훤출
 * @since 20.05.04
 */
public class MainProcess {
    private final Map<Integer, Integer> pageTable;
    private final int frameSize;
    private int miss;

    public MainProcess() {
        this(3);
    }

    public MainProcess(int frameSize) {
        this.pageTable = new LinkedHashMap<>();
        this.frameSize = frameSize;
    }

    public void execute(int[] pages) {
        for (int page : pages) {
            usePage(page);
        }
    }

    private void usePage(int page) {
        if (!pageTable.containsKey(page)) {
            if (pageTable.size() == frameSize) {
                int deletePage = getLeastReferencedPage();
                pageTable.remove(deletePage);
            }
            miss++;
        }
        // add the key-value pair to the map, if key is not present
        // replace the value with the newly computed value, if key is present
        pageTable.merge(page, 1,
                (key, value) -> pageTable.get(page) + 1);
    }

    private int getLeastReferencedPage() {
        int minPage = 0;
        int result = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : pageTable.entrySet()) {
            if (result - entry.getValue() > 0) {
                result = entry.getValue();
                minPage = entry.getKey();
            }
        }
        return minPage;
    }

    public int getPageDefault() {
        return miss;
    }
}
