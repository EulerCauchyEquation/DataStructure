package com.list.arraylist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * ArrayList test
 *
 * @author 송훤출
 * @since 20.04.28
 */
public class MyArrayListTest {
    private MyArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    public void init() {
        Assert.assertThat(list.isEmpty(), is(true));
        Assert.assertThat(list.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        list.add(null);
    }

    public void elementsAdd() {
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(1);
    }

    @Test
    public void testAdd() {
        elementsAdd();

        Assert.assertThat(list.size(), is(4));
        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 5, 1}));
    }

    @Test
    public void testInsert() {
        elementsAdd();
        list.set(2, 9);

        Assert.assertThat(list.size(), is(4));
        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 9, 1}));
    }

    @Test
    public void testSearch() {
        elementsAdd();

        Assert.assertThat(list.get(2), is(5));
        Assert.assertThat(list.contains(5), is(true));

        Assert.assertThat(list.contains(9), is(false));
    }

    @Test
    public void testRemove() {
        elementsAdd();
        list.remove(2);

        Assert.assertThat(list.size(), is(3));
        Assert.assertThat(list.toArray(), is(new Object[]{3, 2, 1}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveRangeOver() {
        elementsAdd();
        list.remove(5);
    }

    @Test
    public void testIterator() {
        elementsAdd();

        Iterator<Integer> it = list.iterator();

        Assert.assertThat(it.hasNext(), is(true));
        Assert.assertThat(it.next(), is(3));

        Assert.assertThat(it.hasNext(), is(true));
        Assert.assertThat(it.next(), is(2));

        Assert.assertThat(it.hasNext(), is(true));
        Assert.assertThat(it.next(), is(5));

        it.remove();
        Assert.assertThat(it.hasNext(), is(false));
    }
}
