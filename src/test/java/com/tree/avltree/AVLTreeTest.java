package com.tree.avltree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * AVL Tree Test
 *
 * @author 송훤출
 * @since 20.04.27
 */
public class AVLTreeTest {
    private AVLTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new AVLTree<>();
    }

    @Test
    public void testInit() {
        Assert.assertThat(tree.isEmpty(), is(true));
        Assert.assertThat(tree.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
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
        Assert.assertThat(tree.toArrayPreOrder(), is(new Object[]{7, 2, 1, 5, 3, 9, 8}));
        Assert.assertThat(tree.toArrayPostOrder(), is(new Object[]{1, 3, 5, 2, 8, 9, 7}));
        Assert.assertThat(tree.size(), is(7));

        tree.insert(4);
        Assert.assertThat(tree.toArrayInOrder(), is(new Object[]{1, 2, 3, 4, 5, 7, 8, 9}));
        Assert.assertThat(tree.toArrayPreOrder(), is(new Object[]{7, 2, 1, 4, 3, 5, 9, 8}));
        Assert.assertThat(tree.toArrayPostOrder(), is(new Object[]{1, 3, 5, 4, 2, 8, 9, 7}));
        Assert.assertThat(tree.size(), is(8));
    }

    @Test(expected = IllegalArgumentException.class)
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

        tree.remove(4);
        tree.remove(1);
        Assert.assertThat(tree.toArrayInOrder(), is(new Object[]{2, 3, 5, 7, 8, 9}));
        Assert.assertThat(tree.toArrayPreOrder(), is(new Object[]{7, 3, 2, 5, 9, 8}));
        Assert.assertThat(tree.toArrayPostOrder(), is(new Object[]{2, 5, 3, 8, 9, 7}));
        Assert.assertThat(tree.size(), is(6));
    }
}
