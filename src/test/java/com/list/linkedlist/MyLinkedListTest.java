package com.list.linkedlist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * LinkedList test
 *
 * @author 송훤출
 * @since 20.04.28
 */
public class MyLinkedListTest {
    private MyLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    public void init() {
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

    private void addElements() {
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(1);
    }

    @Test
    public void testInsert() {
        addElements();
        list.insert(2, 9);

        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 9, 5, 1}));
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
    public void testRemoveLast() {
        addElements();
        list.removeLast();

        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 5}));
        Assert.assertThat(list.size(), is(3));
    }

    @Test
    public void testRemoveFirst() {
        addElements();
        list.removeFirst();

        Assert.assertThat(list.toArray(), is(new Object[]{2, 5, 1}));
        Assert.assertThat(list.size(), is(3));
    }

    @Test
    public void testRemove() {
        addElements();
        list.remove(2);

        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 1}));
        Assert.assertThat(list.size(), is(3));
    }

    @Test
    public void testIterator() {
        addElements();

        Iterator<Integer> it = list.iterator();

        Assert.assertThat(it.hasNext(), is(true));
        Assert.assertThat(it.next(), is(3));

        Assert.assertThat(it.hasNext(), is(true));
        Assert.assertThat(it.next(), is(2));

        Assert.assertThat(it.hasNext(), is(true));
        Assert.assertThat(it.next(), is(5));
    }
}
