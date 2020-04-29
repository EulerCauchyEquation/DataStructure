package com.linear.mStack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * stack : LIFO (Last-In First-Out)
 *
 * @author hwun chul
 * @since 20.04.29
 */
public class MyStackTest {
    private MyStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new MyStack<>();
    }

    @Test
    public void init() {
        Assert.assertThat(stack.isEmpty(), is(true));
        Assert.assertThat(stack.size(), is(0));
    }

    @Test
    public void testPush() {
        addElements();
        Assert.assertThat(stack.peek(), is(1));
        Assert.assertThat(stack.size(), is(4));
        Assert.assertThat(stack.toArray(), is(new Object[]{3, 2, 5, 1}));

        stack.push(9);

        Assert.assertThat(stack.peek(), is(9));
        Assert.assertThat(stack.size(), is(5));
        Assert.assertThat(stack.toArray(), is(new Object[]{3, 2, 5, 1, 9}));
    }

    private void addElements() {
        stack.push(3);
        stack.push(2);
        stack.push(5);
        stack.push(1);
    }

    @Test(expected = NullPointerException.class)
    public void testPushNull() {
        stack.push(null);
    }

    @Test
    public void testPop() {
        addElements();

        int data = stack.pop();
        Assert.assertThat(data, is(1));
        Assert.assertThat(stack.peek(), is(5));
        Assert.assertThat(stack.size(), is(3));
        Assert.assertThat(stack.toArray(), is(new Object[]{3, 2, 5}));

        data = stack.pop();
        Assert.assertThat(data, is(5));
        Assert.assertThat(stack.peek(), is(2));
        Assert.assertThat(stack.size(), is(2));
        Assert.assertThat(stack.toArray(), is(new Object[]{3, 2}));
    }

    @Test(expected = NullPointerException.class)
    public void testPopEmpty() {
        stack.pop();
    }
}
