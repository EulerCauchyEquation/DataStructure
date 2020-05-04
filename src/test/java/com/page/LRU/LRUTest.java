package com.page.LRU;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * LRU Algorithm : Least Recently Used Page Algorithm
 *
 * @author 송훤출
 * @since 20.05.04
 */
public class LRUTest {
    private Cache cache;

    @Before
    public void setUp() {
        cache = new Cache(3);
    }

    @Test
    public void LRU_예제1() {
        int[] pages = {8, 1, 2, 3, 1, 4, 1, 5, 3, 4, 1, 4, 3, 2};

        cache.execute(pages);

        Assert.assertThat(cache.getPageDefault(), is(10));
    }

    @Test
    public void LRU_예제2() {
        int[] pages = {0, 1, 2, 3, 0, 1, 4, 0, 1, 2, 3, 4};

        cache.execute(pages);

        Assert.assertThat(cache.getPageDefault(), is(10));
    }
}
