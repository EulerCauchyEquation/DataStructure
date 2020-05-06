package com.disk.LOOK;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LOOK disk scheduling
 *
 * @author 송훤출
 * @since 20.05.06
 */
public class Scheduling {
    private final TreeSet<Integer> demandQueue;
    private int head;
    private int distance;

    public Scheduling(int head, int[] queue) {
        this.demandQueue = Arrays.stream(queue)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));
        this.head = head;
    }

    public void execute() {
        final List<Integer> leftList = new ArrayList<>(demandQueue.headSet(head));
        final List<Integer> rightList = new ArrayList<>(demandQueue.tailSet(head));
        Collections.reverse(leftList);

        perform(leftList);
        perform(rightList);
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
