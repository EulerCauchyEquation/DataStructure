package com.linear.mQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class LinkedListTest {
    private MyLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    public void testInit() {
        Assert.assertThat(list.isEmpty(), is(true));
        Assert.assertThat(list.size(), is(0));
    }

    @Test
    public void testAddFirst() {
        addElements();
        list.addFirst(9);

        Assert.assertThat(list.toArray(), is(new Object[]{9, 3, 2, 5, 1}));
        Assert.assertThat(list.size(), is(5));
    }

    @Test
    public void testAddLast() {
        addElements();
        list.add(9);

        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 5, 1, 9}));
        Assert.assertThat(list.size(), is(5));
    }

    @Test
    public void testInsert() {
        addElements();

        list.insert(2, 9);
        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 9, 5, 1}));
        Assert.assertThat(list.size(), is(5));
    }

    private void addElements() {
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(1);
    }

    @Test
    public void testRemoveFirst() {
        addElements();
        list.removeFirst();

        Assert.assertThat(list.toArray(), is(new Object[]{2, 5, 1}));
        Assert.assertThat(list.size(), is(3));
    }

    @Test
    public void testRemoveLast() {
        addElements();
        list.removeLast();

        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 5}));
        Assert.assertThat(list.size(), is(3));
    }

    @Test
    public void testRemove() {
        addElements();
        list.remove(2);

        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 1}));
        Assert.assertThat(list.size(), is(3));
    }
}
