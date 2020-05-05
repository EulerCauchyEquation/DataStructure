package com.scheduling.FCFS;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * FCFS Test
 *
 * @author 송훤출
 * @since 20.05.05
 */
public class FCFSTest {
    private Scheduling scheduling;

    @Test
    public void 예제1() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(0, 21));
        taskList.add(new Task(1, 3));
        taskList.add(new Task(2, 6));

        scheduling = new Scheduling(taskList);
        scheduling.analyze();

        Assert.assertThat(scheduling.getAverageTurnAroundTime(), is("24.00"));
        Assert.assertThat(scheduling.getAverageWaitingTime(), is("14.00"));
    }

    @Test
    public void 예제2() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(2, 3));
        taskList.add(new Task(0, 21));
        taskList.add(new Task(1, 3));

        scheduling = new Scheduling(taskList);
        scheduling.analyze();

        Assert.assertThat(scheduling.getAverageTurnAroundTime(), is("23.00"));
        Assert.assertThat(scheduling.getAverageWaitingTime(), is("14.00"));
    }
}
