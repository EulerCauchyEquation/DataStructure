package com.disk.FCFS;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * FCFS scheduling test ( disk scheduling)
 *
 * @author 송훤출
 * @since 20.05.06
 */
public class FCFSTest {
    private Scheduling scheduling;

    @Test
    public void 예제1() {
        int[] queue = {105, 180, 40, 120, 10, 125, 65, 70};
        int init_head = 50;

        scheduling = new Scheduling(init_head, queue);
        scheduling.execute();

        Assert.assertThat(scheduling.getHeadDistance(), is(640));
    }

    @Test
    public void 예제2() {
        int[] queue = {150, 0, 70, 200, 30, 20, 60};
        int init_head = 50;

        scheduling = new Scheduling(init_head, queue);
        scheduling.execute();

        Assert.assertThat(scheduling.getHeadDistance(), is(670));
    }
}
