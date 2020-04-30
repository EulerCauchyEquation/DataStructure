package com.sort;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * sort test
 *
 * @author hwun chul
 * @since 20.04.30
 */
public class SortTest {

    @Test
    public void testBubbleSort() {
        int[] intArray = {26, 5, 37, 1, 61, 11, 59, 15, 48, 19};

        SortManager.bubbleSort(intArray);

        Assert.assertThat(intArray, is(new int[]{1, 5, 11, 15, 19, 26, 37, 48, 59, 61}));
    }

    @Test
    public void testSelectionSort() {
        int[] intArray = {26, 5, 37, 1, 61, 11, 59, 15, 48, 19};

        SortManager.selectionSort(intArray);

        Assert.assertThat(intArray, is(new int[]{1, 5, 11, 15, 19, 26, 37, 48, 59, 61}));
    }
}
