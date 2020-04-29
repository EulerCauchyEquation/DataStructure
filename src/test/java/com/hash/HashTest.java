package com.hash;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;

/**
 * hash test (separated chain)
 * put, remove, search : O(1)
 *
 * @author hwun chul
 * @since 20.04.29
 */
public class HashTest {
    private ChainHash<Integer, String> hash;

    @Before
    public void setUp() {
        hash = new ChainHash<>();
    }

    @Test
    public void testInsert() {
        Assert.assertThat(hash.size(), is(0));

        hash.put(3, "C");
        hash.put(2, "B");
        Assert.assertThat(hash.containsKey(3), is(true));
        Assert.assertThat(hash.get(3), is("C"));
        Assert.assertThat(hash.containsKey(2), is(true));
        Assert.assertThat(hash.get(2), is("B"));
        Assert.assertThat(hash.size(), is(2));

        hash.put(5, "E");
        hash.put(1, "A");
        Assert.assertThat(hash.containsKey(5), is(true));
        Assert.assertThat(hash.get(5), is("E"));
        Assert.assertThat(hash.containsKey(1), is(true));
        Assert.assertThat(hash.get(1), is("A"));
        Assert.assertThat(hash.size(), is(4));
    }

    private void addElements() {
        hash.put(3, "C");
        hash.put(2, "B");
        hash.put(5, "E");
        hash.put(1, "A");
    }

    @Test(expected = NullPointerException.class)
    public void testInsertNull() {
        hash.put(null, null);
        hash.put(1, null);
        hash.put(null, "A");
    }

    @Test
    public void testInsertDuplicateElement() {
        addElements();
        Assert.assertThat(hash.containsKey(2), is(true));
        Assert.assertThat(hash.get(2), is("B"));
        Assert.assertThat(hash.size(), is(4));

        hash.put(2, "B");
        Assert.assertThat(hash.containsKey(2), is(true));
        Assert.assertThat(hash.get(2), is("B"));
        Assert.assertThat(hash.size(), is(4));
    }

    @Test
    public void testSearchIllegal() {
        addElements();

        Assert.assertNull(hash.get(9));
    }

    @Test
    public void testRemove() {
        addElements();
        Assert.assertThat(hash.containsKey(5), is(true));
        Assert.assertThat(hash.get(5), is("E"));
        Assert.assertThat(hash.size(), is(4));

        hash.remove(5);

        Assert.assertThat(hash.containsKey(5), is(false));
        Assert.assertNull(hash.get(5));
        Assert.assertThat(hash.size(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveNoneElement() {
        addElements();

        hash.remove(0);
    }
}
