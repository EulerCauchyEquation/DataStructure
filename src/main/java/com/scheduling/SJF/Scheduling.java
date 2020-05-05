package com.scheduling.SJF;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * SJF Scheduling : Shortest Job First Scheduling
 * non-preemptive
 *
 * @author 송훤출
 * @since 20.05.05
 */
public class Scheduling {
    private final PriorityQueue<Task> taskList;
    private final PriorityQueue<Task> waitQueue;
    private final int size;
    private int totalTurnAroundTime;
    private int totalWaitingTime;

    public Scheduling(List<Task> taskList) {
        this.taskList = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getArriveTime() - o2.getArriveTime();
            }
        });
        for (Task task : taskList) {
            this.taskList.offer(task);
        }

        this.waitQueue = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getBurstTime() - o2.getBurstTime();
            }
        });
        this.size = taskList.size();
    }

    public void analyze() {
        if (taskList.isEmpty()) {
            return;
        }

        int elapsed = taskList.peek().getArriveTime();

        while (!taskList.isEmpty() || !waitQueue.isEmpty()) {
            addArrivalProcessToWaitQueue(elapsed);

            Task task = waitQueue.isEmpty() ? taskList.poll()
                    : waitQueue.poll();

            elapsed += task.getBurstTime() +
                    Math.max(task.getArriveTime() - elapsed, 0);

            int turnAroundTime = elapsed - task.getArriveTime();
            int waitTime = turnAroundTime - task.getBurstTime();

            totalTurnAroundTime += turnAroundTime;
            totalWaitingTime += waitTime;
        }
    }

    private void addArrivalProcessToWaitQueue(int elapsed) {
        while (!taskList.isEmpty()
                && elapsed >= taskList.peek().getArriveTime()) {
            waitQueue.offer(taskList.poll());
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
