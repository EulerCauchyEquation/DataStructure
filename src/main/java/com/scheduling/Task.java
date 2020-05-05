package com.scheduling;

public class Task {
    private int arriveTime;
    private int burstTime;
    private int priority;

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
}
