package com.scheduling.SJF;

public class Task {
    private final int arriveTime;
    private final int burstTime;
    private final int priority;

    public Task(int burstTime) {
        this(0, burstTime, 0);
    }

    public Task(int arriveTime, int burstTime) {
        this(arriveTime, burstTime, 0);
    }

    public Task(int arriveTime, int burstTime, int priority) {
        this.arriveTime = arriveTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }
}
