package com.tree.redblack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;

/**
 * red-black-tree test
 *
 * @author 송훤출
 * @since 20.05.25
 */
public class RedBlackTreeTest {
    private RedBlackTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new RedBlackTree<>();
    }

    @Test
    public void testInit() {
        Assert.assertThat(tree.isEmpty(), is(true));
        Assert.assertThat(tree.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertNull() {
        tree.insert(null);
    }

    @Test
    public void testInsert() {
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(9);
        tree.insert(7);
        tree.insert(8);
        tree.insert(3);

        Assert.assertThat(tree.toArrayInOrder(), is(new Object[]{1, 2, 3, 5, 7, 8, 9}));
        Assert.assertThat(tree.toArrayPreOrder(), is(new Object[]{2, 1, 7, 5, 3, 9, 8}));
        Assert.assertThat(tree.toArrayPostOrder(), is(new Object[]{1, 3, 5, 8, 9, 7, 2}));
        Assert.assertThat(tree.size(), is(7));

        tree.insert(4);
        tree.insert(6);
        Assert.assertThat(tree.toArrayInOrder(), is(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        Assert.assertThat(tree.toArrayPreOrder(), is(new Object[]{4, 2, 1, 3, 7, 5, 6, 9, 8}));
        Assert.assertThat(tree.toArrayPostOrder(), is(new Object[]{1, 3, 2, 6, 5, 8, 9, 7, 4}));
        Assert.assertThat(tree.size(), is(9));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveIllegalArgumentElement() {
        tree.insert(5);
        tree.remove(0);
    }

    @Test
    public void testRemove() {
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(9);
        tree.insert(7);
        tree.insert(8);
        tree.insert(3);
        tree.insert(4);
        tree.insert(6);

        tree.remove(4);
        tree.remove(1);
        Assert.assertThat(tree.toArrayInOrder(), is(new Object[]{2, 3, 5, 6, 7, 8, 9}));
        Assert.assertThat(tree.toArrayPreOrder(), is(new Object[]{5, 2, 3, 7, 6, 9, 8}));
        Assert.assertThat(tree.toArrayPostOrder(), is(new Object[]{3, 2, 6, 8, 9, 7, 5}));
        Assert.assertThat(tree.size(), is(7));

        tree.remove(5);
        tree.remove(7);
        Assert.assertThat(tree.toArrayInOrder(), is(new Object[]{2, 3, 6, 8, 9}));
        Assert.assertThat(tree.toArrayPreOrder(), is(new Object[]{6, 2, 3, 8, 9}));
        Assert.assertThat(tree.toArrayPostOrder(), is(new Object[]{3, 2, 9, 8, 6}));
        Assert.assertThat(tree.size(), is(5));
    }
}