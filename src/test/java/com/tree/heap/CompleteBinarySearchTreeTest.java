package com.tree.heap;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * heap 구현 테스트
 *
 * @author 송훤출
 * @since 20.04.25
 */
public class CompleteBinarySearchTreeTest {
    private CompleteBinarySearchTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new CompleteBinarySearchTree<>();
    }

    @Test
    public void testInit() {
        Assert.assertThat(tree.isEmpty(), is(true));
        Assert.assertThat(tree.size(), is(0));
        Assert.assertNull(tree.peek());
    }

    @Test
    public void testInsertElements() {
        tree.offer(9);
        Assert.assertThat(tree.peek(), is(9));
        Assert.assertThat(tree.size(), is(1));

        tree.offer(5);
        Assert.assertThat(tree.peek(), is(5));
        Assert.assertThat(tree.size(), is(2));

        tree.offer(7);
        Assert.assertThat(tree.peek(), is(5));
        Assert.assertThat(tree.size(), is(3));
    }

    @Test(expected = NullPointerException.class)
    public void testInsertElementNull() {
        tree.offer(null);
    }


    @Test
    public void testRemoveElements() {
        tree.offer(5);
        tree.offer(8);
        tree.offer(9);
        tree.offer(7);
        tree.offer(10);

        Assert.assertThat(tree.poll(), is(5));
        Assert.assertThat(tree.size(), is(4));

        Assert.assertThat(tree.poll(), is(7));
        Assert.assertThat(tree.size(), is(3));

        Assert.assertThat(tree.poll(), is(8));
        Assert.assertThat(tree.size(), is(2));

        Assert.assertThat(tree.poll(), is(9));
        Assert.assertThat(tree.size(), is(1));

        Assert.assertThat(tree.poll(), is(10));
        Assert.assertThat(tree.size(), is(0));
        Assert.assertThat(tree.isEmpty(), is(true));

        tree.offer(20);
        Assert.assertThat(tree.poll(), is(20));
        Assert.assertThat(tree.size(), is(0));
        Assert.assertThat(tree.isEmpty(), is(true));
    }

    @Test
    public void testHeapSort() {
        Object[] arr = {3, 2, 1, 5, 4};
        CompleteBinarySearchTree.sort(arr);

        Assert.assertThat(arr, is(new Object[]{1, 2, 3, 4, 5}));
    }
}
