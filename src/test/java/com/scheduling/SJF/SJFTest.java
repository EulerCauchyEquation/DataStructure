package com.scheduling.SJF;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * SJF (Shortest Job First) Scheduling test
 *
 * @author 송훤출
 * @since 20.05.05
 */
public class SJFTest {
    private Scheduling scheduling;

    @Test
    public void 예제1() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, 3));
        taskList.add(new Task(5, 4));
        taskList.add(new Task(7, 6));
        taskList.add(new Task(7, 7));
        ;

        scheduling = new Scheduling(taskList);
        scheduling.analyze();

        Assert.assertThat(scheduling.getAverageTurnAroundTime(), is("7.50"));
        Assert.assertThat(scheduling.getAverageWaitingTime(), is("2.50"));
    }

    @Test
    public void 예제2() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(7));
        taskList.add(new Task(8));
        taskList.add(new Task(3));
        taskList.add(new Task(6));

        scheduling = new Scheduling(taskList);
        scheduling.analyze();

        Assert.assertThat(scheduling.getAverageTurnAroundTime(), is("13.00"));
        Assert.assertThat(scheduling.getAverageWaitingTime(), is("7.00"));
    }
}
