package com.scheduling.FCFS;

import java.util.List;
import java.util.PriorityQueue;

/**
 * FCFS Scheduling : First-Come First-Served
 *
 * @author 송훤출
 * @since 20.05.05
 */
public class Scheduling {
    private final PriorityQueue<Task> taskList;
    private final int size;
    private int totalTurnAroundTime;
    private int totalWaitingTime;

    public Scheduling(List<Task> taskList) {
        this.taskList = new PriorityQueue<>(taskList);
        this.size = taskList.size();
    }

    public void analyze() {
        if (taskList.isEmpty()) {
            return;
        }

        int elapsed = taskList.peek().getArriveTime();

        while (!taskList.isEmpty()) {
            Task task = taskList.poll();
            elapsed += task.getBurstTime() +
                    ((task.getArriveTime() - elapsed <= 0)
                            ? 0
                            : task.getArriveTime());

            int turnAroundTime = elapsed - task.getArriveTime();
            int waitTime = turnAroundTime - task.getBurstTime();

            totalTurnAroundTime += turnAroundTime;
            totalWaitingTime += waitTime;
        }
    }

    public String getAverageTurnAroundTime() {
        double avgTurnAroundTime = (double) totalTurnAroundTime / size;
        return String.format("%.2f", avgTurnAroundTime);
    }

    public String getAverageWaitingTime() {
        double avgWaitingTime = (double) totalWaitingTime / size;
        return String.format("%.2f", avgWaitingTime);
    }
}
