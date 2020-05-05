package com.scheduling.FCFS;

import com.scheduling.Task;
import org.junit.Assert;
import org.junit.Before;
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

    @Before
    public void setUp() {
        scheduling = new Scheduling();
    }

    @Test
    public void 예제1() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(0, 21));
        taskList.add(new Task(1, 3));
        taskList.add(new Task(1, 6));

        scheduling.analyze(taskList);

        Assert.assertThat(scheduling.getAverageTurnAroundTime(), is("25.00"));
        Assert.assertThat(scheduling.getAverageWaitingTime(), is("15.00"));
    }

    @Test
    public void 예제2() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(0, 21));
        taskList.add(new Task(1, 3));
        taskList.add(new Task(1, 3));

        scheduling.analyze(taskList);

        Assert.assertThat(scheduling.getAverageTurnAroundTime(), is("24.00"));
        Assert.assertThat(scheduling.getAverageWaitingTime(), is("17.00"));
    }
}
