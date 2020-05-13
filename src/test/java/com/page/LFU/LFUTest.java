package com.page.LFU;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * LFU Page Replacement Test
 *
 * @author 송훤출
 * @since 20.05.04
 */
public class LFUTest {
    private MainProcess process;

    @Before
    public void setUp() {
        process = new MainProcess();
    }

    @Test
    public void 예제1() {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};

        process.execute(pages);

        Assert.assertThat(process.getPageDefault(), is(13));
    }

    @Test
    public void 예제2() {
        int[] pages = {2, 3, 1, 3, 1, 2, 4, 5};

        process.execute(pages);

        Assert.assertThat(process.getPageDefault(), is(5));
    }
}
