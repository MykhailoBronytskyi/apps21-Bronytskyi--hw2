package ua.edu.ucu.collections.immutable;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

public class ImmutableArrayListTest {

    ImmutableArrayList arrayList = new ImmutableArrayList();
    ImmutableArrayList arrayEmptyList = new ImmutableArrayList();

    @Before
    public void setUp() {
        arrayList = new ImmutableArrayList(new Object[]{1, 2, 3, 4, 5});
        arrayEmptyList = new ImmutableArrayList();
    }

    @Test
    public void testAdd() {
        arrayList = (ImmutableArrayList) arrayList.add(10);
        System.out.println(arrayList);

        Assert.assertEquals("[10, 1, 2, 3, 4, 5]", arrayList.toString());

        arrayList = (ImmutableArrayList) arrayList.add(100, 10);
        Assert.assertEquals("[10, 1, 2, 3, 4, 5, 10]", arrayList.toString());


        arrayList = (ImmutableArrayList) arrayList.add(-100, 10);
        Assert.assertEquals("[10, 10, 1, 2, 3, 4, 5, 10]", arrayList.toString());


        arrayEmptyList = (ImmutableArrayList) arrayEmptyList.add(10);
        Assert.assertEquals("[10]", arrayEmptyList.toString());
    }

    @Test
    public void testAddAll() {
        arrayList = (ImmutableArrayList) arrayList.addAll(100, new Object[]{5, 5, 5, 5, 5});
        Assert.assertEquals("[1, 2, 3, 4, 5, 5, 5, 5, 5, 5]", arrayList.toString());

        arrayEmptyList = (ImmutableArrayList) arrayEmptyList.addAll(100, new Object[]{5, 5, 5, 5, 5});
        Assert.assertEquals("[5, 5, 5, 5, 5]", arrayEmptyList.toString());

        arrayEmptyList = (ImmutableArrayList) arrayEmptyList.addAll(-1, new Object[]{3});
        Assert.assertEquals("[5, 5, 5, 5, 5, 3]", arrayEmptyList.toString());


        arrayEmptyList = (ImmutableArrayList) arrayEmptyList.addAll(-100, new Object[]{3});
        Assert.assertEquals("[3, 5, 5, 5, 5, 5, 3]", arrayEmptyList.toString());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGet() {
        assertEquals(1, arrayList.get(0));
        assertEquals(5, arrayList.get(-1));
        //error
        assertEquals(1, arrayList.get(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test2Get() {
        assertEquals(1, arrayList.get(-10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove() {
        arrayList = (ImmutableArrayList) arrayList.remove(0);
        assertEquals("[2, 3, 4, 5]", arrayList.toString());

        arrayList = (ImmutableArrayList) arrayList.remove(-1);
        assertEquals("[2, 3, 4]", arrayList.toString());

        arrayList.remove(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSet() {
        arrayList = (ImmutableArrayList) arrayList.set(0, 10);

        assertEquals("[10, 2, 3, 4, 5]", arrayList.toString());

        arrayList = (ImmutableArrayList) arrayList.set(-1, 15);
        assertEquals("[10, 2, 3, 4, 15]", arrayList.toString());

        arrayEmptyList.set(0, 10);
    }

    @Test
    public void testIndexOf() {
        int idx_of_number_5 = arrayList.indexOf(5);
        assertEquals(4, idx_of_number_5);

        arrayList = (ImmutableArrayList) arrayList.set(0, "Hi");
        int idx_of_string = arrayList.indexOf("Hi");
        assertEquals(0, idx_of_string);

        arrayList = (ImmutableArrayList) arrayList.set(0, new Node());
        int idx_node = arrayList.indexOf(new Node());
        assertEquals(-1, idx_node);

        int idx_of_non_existed = arrayList.indexOf("Non existing value");
        assertEquals(-1, idx_of_non_existed);
    }

    @Test
    public void testSize() {
        assertEquals(5, arrayList.size());
        assertEquals(0, arrayEmptyList.size());
    }

    @Test
    public void testClear() {
        assertEquals(5, arrayList.size());
        arrayList = (ImmutableArrayList) arrayList.clear();
        assertEquals(0, arrayList.size());
    }

    @Test
    public void testIsEmpty() {
        assertEquals(false, arrayList.isEmpty());
        assertEquals(true, arrayEmptyList.isEmpty());
    }

    @Test
    public void testToArray() {
        arrayList = (ImmutableArrayList) arrayList.add(10);
        assertArrayEquals(new Object[]{10, 1, 2, 3, 4, 5}, arrayList.toArray());

        arrayList = (ImmutableArrayList) arrayList.addAll(new Object[]{30, 20});
        assertArrayEquals(new Object[]{30, 20, 10, 1, 2, 3, 4, 5}, arrayList.toArray());

        assertArrayEquals(new Object[]{}, arrayEmptyList.toArray());

        arrayEmptyList = (ImmutableArrayList) arrayEmptyList.add(null);
        assertArrayEquals(new Object[]{null}, arrayEmptyList.toArray());
    }
}