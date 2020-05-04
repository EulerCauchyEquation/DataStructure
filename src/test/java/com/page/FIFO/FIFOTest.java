package com.page.FIFO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * FIFO Page Replacement
 *
 * @author 송훤출
 * @since 20.05.04
 */
public class FIFOTest {
    private MainProcess process;

    @Before
    public void setUp() {
        process = new MainProcess();
    }

    @Test
    public void 예제1() {
        int[] pages = {8, 1, 2, 3, 1, 4, 1, 5, 3, 4, 1, 4, 3, 2};

        process.execute(pages);

        Assert.assertThat(process.getPageDefault(), is(11));
    }

    @Test
    public void 예제2() {
        int[] pages = {2, 3, 2, 1, 5, 2, 3, 5};

        process.execute(pages);

        Assert.assertThat(process.getPageDefault(), is(6));
    }
}
