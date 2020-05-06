package com.disk.CLOOK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * C-LOOK Disk Scheduling
 *
 * @author 송훤출
 * @since 20.05.06
 */
public class Scheduling {
    private final TreeSet<Integer> demandQueue;
    private int head;
    private int distance;

    public Scheduling(int init_head, int[] queue) {
        this.demandQueue = Arrays.stream(queue)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));
        this.head = init_head;
    }

    public void execute() {
        final List<Integer> leftList = new ArrayList<>(demandQueue.headSet(head));
        final List<Integer> rightList = new ArrayList<>(demandQueue.tailSet(head));

        perform(rightList);
        perform(leftList);
    }

    private void perform(List<Integer> demandList) {
        int prev = head;
        for (int track : demandList) {
            distance += Math.abs(prev - track);
            prev = track;
        }
        head = prev;
    }

    public int getHeadDistance() {
        return distance;
    }
}
