package com.disk.FCFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 디스크 스케줄링 FCFS (First-come First-Served)
 *
 * @author 송훤출
 * @since 20.05.06
 */
public class Scheduling {
    private final Queue<Integer> readyQueue;
    private final int init_head;
    private int distance;

    public Scheduling(int init_head, int[] queue) {
        this.readyQueue = Arrays.stream(queue)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        this.init_head = init_head;
    }

    public void execute() {
        int prev = init_head;

        while (!readyQueue.isEmpty()) {
            int current = readyQueue.poll();
            distance += Math.abs(prev - current);
            prev = current;
        }
    }

    public int getHeadDistance() {
        return distance;
    }
}
