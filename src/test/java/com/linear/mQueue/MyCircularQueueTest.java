package com.linear.mQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Circular Queue test
 *
 * @author 송훤출
 * @since 20.05.07
 */
public class MyCircularQueueTest {
    private MyCircularQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new MyCircularQueue<>();
    }

    @Test
    public void init() {
        Assert.assertThat(queue.isEmpty(), is(true));
        Assert.assertThat(queue.size(), is(0));
    }

    @Test
    public void testPush() {
        addElements();
        Assert.assertThat(queue.peek(), is(3));
        Assert.assertThat(queue.size(), is(4));
        Assert.assertThat(queue.toArray(), is(new Object[]{3, 2, 5, 1}));

        queue.offer(9);
        Assert.assertThat(queue.peek(), is(3));
        Assert.assertThat(queue.size(), is(5));
        Assert.assertThat(queue.toArray(), is(new Object[]{3, 2, 5, 1, 9}));
    }

    private void addElements() {
        queue.offer(3);
        queue.offer(2);
        queue.offer(5);
        queue.offer(1);
    }

    @Test
    public void testRemove() {
        addElements();
        Assert.assertThat(queue.peek(), is(3));
        Assert.assertThat(queue.size(), is(4));
        Assert.assertThat(queue.toArray(), is(new Object[]{3, 2, 5, 1}));

        while (!queue.isEmpty()) {
            queue.poll();
        }

        Assert.assertThat(queue.size(), is(0));
        Assert.assertThat(queue.toArray(), is(new Object[]{}));

        addElements();
        Assert.assertThat(queue.peek(), is(3));
        Assert.assertThat(queue.size(), is(4));
        Assert.assertThat(queue.toArray(), is(new Object[]{3, 2, 5, 1}));
    }
}