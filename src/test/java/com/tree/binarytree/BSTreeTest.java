package com.tree.binarytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * 이진 트리 테스트
 *
 * @author 송훤출
 * @since 20.04.16
 */
public class BSTreeTest {
    private BSTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new BSTree<>();
    }

    @Test
    public void testInit() {
        Assert.assertThat(tree.isEmpty(), is(true));
        Assert.assertThat(tree.size(), is(0));
    }

    @Test
    public void testInsertElements() {
        tree.insert(5);
        tree.insert(8);
        tree.insert(9);

        Assert.assertThat(tree.search(5), is(5));
        Assert.assertThat(tree.search(8), is(8));
        Assert.assertThat(tree.search(9), is(9));
        Assert.assertThat(tree.toArray(), is(new Object[]{5, 8, 9}));
        Assert.assertThat(tree.size(), is(3));

        tree.insert(9);
        tree.insert(7);
        tree.insert(10);

        Assert.assertThat(tree.search(9), is(9));
        Assert.assertThat(tree.search(7), is(7));
        Assert.assertThat(tree.search(10), is(10));
        Assert.assertThat(tree.toArray(), is(new Object[]{5, 7, 8, 9, 10}));
        Assert.assertThat(tree.size(), is(5));
    }

    @Test(expected = NullPointerException.class)
    public void testInsertElementNull() {
        tree.insert(null);
    }

    @Test
    public void testSearchElementsNull() {
        Assert.assertNull(tree.search(9));
    }

    @Test
    public void testRemoveElements() {
        tree.insert(5);
        tree.insert(8);
        tree.insert(9);
        tree.insert(7);
        tree.insert(10);

        Assert.assertThat(tree.toArray(), is(new Object[]{5, 7, 8, 9, 10}));
        Assert.assertThat(tree.size(), is(5));

        tree.remove(8);

        Assert.assertThat(tree.toArray(), is(new Object[]{5, 7, 9, 10}));
        Assert.assertThat(tree.size(), is(4));

        tree.remove(5);
        tree.remove(9);

        Assert.assertThat(tree.toArray(), is(new Object[]{7, 10}));
        Assert.assertThat(tree.size(), is(2));

        tree.remove(10);
        tree.remove(7);

        Assert.assertThat(tree.toArray(), is(new Object[]{}));
        Assert.assertThat(tree.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveMissingElement() {
        tree.insert(9);
        tree.remove(0);
    }

    @Test
    public void testInOrder() {
        tree.insert(5);
        tree.insert(8);
        tree.insert(9);
        tree.insert(7);
        tree.insert(10);

        Assert.assertThat(tree.toArray(), is(new Object[]{5, 7, 8, 9, 10}));
        Assert.assertThat(tree.size(), is(5));
    }

    @Test
    public void testToArrayReverse() {
        tree.insert(23);
        tree.insert(18);
        tree.insert(12);
        tree.insert(21);
        tree.insert(19);
        tree.insert(20);
        tree.insert(22);
        tree.insert(44);
        tree.insert(35);
        tree.insert(52);

        Assert.assertThat(tree.toArrayByReverse(), is(
                new Object[]{52, 44, 35, 23, 22, 21, 20, 19, 18, 12}));
    }
}
