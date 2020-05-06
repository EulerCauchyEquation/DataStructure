package com.disk.SSTF;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * SSTF (Shortest Seek Time First) disk scheduling
 *
 * @author 송훤출
 * @since 20.05.06
 */
public class Scheduling {
    private final Queue<Integer> demandQueue;
    private final int init_head;
    private int distance;

    public Scheduling(int init_head, int[] queue) {
        this.demandQueue = Arrays.stream(queue)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        this.init_head = init_head;
    }

    public void execute() {
        int head = init_head;

        // time - complexity : O(N^2).. 수정 요망
        while (!demandQueue.isEmpty()) {
            int current = findShortestTrackTo(head);
            demandQueue.remove(current);
            distance += Math.abs(head - current);
            head = current;
        }
    }

    private int findShortestTrackTo(int head) {
        return demandQueue.stream()
                .filter(track -> head != track)
                .min(Comparator.comparingInt(track -> Math.abs(track - head)))
                .get();
    }

    public int getHeadDistance() {
        return distance;
    }
}
