package com.linear.mQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Queue : FIFO
 *
 * @author hwun chul
 * @since 20.04.29
 */
public class MyQueueTest {
    private MyQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new MyQueue<>();
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

        queue.offer(9);

        Assert.assertThat(queue.peek(), is(3));
        Assert.assertThat(queue.size(), is(5));
    }

    private void addElements() {
        queue.offer(3);
        queue.offer(2);
        queue.offer(5);
        queue.offer(1);
    }

    @Test(expected = NullPointerException.class)
    public void testPushNull() {
        queue.offer(null);
    }

    @Test
    public void testRemove() {
        addElements();

        int data = queue.poll();
        Assert.assertThat(data, is(3));
        Assert.assertThat(queue.peek(), is(2));
        Assert.assertThat(queue.size(), is(3));

        data = queue.poll();
        Assert.assertThat(data, is(2));
        Assert.assertThat(queue.peek(), is(5));
        Assert.assertThat(queue.size(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        queue.poll();
    }
}
