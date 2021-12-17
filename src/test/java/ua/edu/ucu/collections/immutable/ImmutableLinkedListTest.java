package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

public class ImmutableLinkedListTest {

    ImmutableLinkedList linkedList = new ImmutableLinkedList();
    ImmutableLinkedList linkedEmptyList = new ImmutableLinkedList();

    @Before
    public void setUp() {
        linkedList = new ImmutableLinkedList(new Object[]{1, 2, 3, 4, 5});
        linkedEmptyList = new ImmutableLinkedList();
    }

    @Test
    public void testMyThoughts() {
        Object[] obj = new Object[]{1,2,3};
        System.out.println(Arrays.toString(obj));
        System.out.println(linkedList.toString());
        System.out.println(linkedList.getHead().getPrevious());
        Object[] arr = new  Object[]{};
        ImmutableLinkedList ne = new ImmutableLinkedList(arr);

    }
    @Test
    public void testAdd() {
        Object[] object = new Object[]{30, 20};

        linkedList = (ImmutableLinkedList) linkedList.add(10);
        assertEquals("[10, 1, 2, 3, 4, 5]", linkedList.toString());

        linkedList = (ImmutableLinkedList) linkedList.addAll(object);
        assertEquals("[30, 20, 10, 1, 2, 3, 4, 5]", linkedList.toString());

        linkedList = (ImmutableLinkedList) linkedList.addAll(-1,object);
        assertEquals("[30, 20, 10, 1, 2, 3, 4, 5, 30, 20]", linkedList.toString());

        linkedList = (ImmutableLinkedList) linkedList.addAll(linkedList.size() + 1, object);
        assertEquals("[30, 20, 10, 1, 2, 3, 4, 5, 30, 20, 30, 20]", linkedList.toString());

        linkedList = (ImmutableLinkedList) linkedList.add(null);
        assertEquals("[null, 30, 20, 10, 1, 2, 3, 4, 5, 30, 20, 30, 20]", linkedList.toString());
    }

    @Test
    public void testEmptyAdd() {
        linkedEmptyList = (ImmutableLinkedList) linkedEmptyList.add(10);
        assertEquals("[10]", linkedEmptyList.toString());

        linkedEmptyList = (ImmutableLinkedList) linkedEmptyList.addAll(new Object[]{30, 20});
        assertEquals("[30, 20, 10]", linkedEmptyList.toString());

        linkedEmptyList = (ImmutableLinkedList) linkedEmptyList.add(null);
        assertEquals("[null, 30, 20, 10]", linkedEmptyList.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGet() {
        int num_1 = (int) linkedList.get(0);
        assertEquals(1, num_1);

        int num_2 = (int) linkedList.get(3);
        assertEquals(4, num_2);

//        throw new IllegalArgumentException();
        int num = (int) linkedList.get(7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinus() {
        int num_1 = (int) linkedList.get(-5);
        assertEquals(1, num_1);

        int num_2 = (int) linkedList.get(-2);
        assertEquals(4, num_2);

        int num = (int) linkedList.get(-7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove() {
        linkedList = (ImmutableLinkedList) linkedList.remove(0);
        assertEquals("[2, 3, 4, 5]", linkedList.toString());

        linkedList = (ImmutableLinkedList) linkedList.remove(-1);
        assertEquals("[2, 3, 4]", linkedList.toString());

        linkedList.remove(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSet() {
        linkedList = (ImmutableLinkedList) linkedList.set(0, 10);

        assertEquals("[10, 2, 3, 4, 5]", linkedList.toString());

        linkedList = (ImmutableLinkedList) linkedList.set(-1, 15);
        assertEquals("[10, 2, 3, 4, 15]", linkedList.toString());

        linkedEmptyList.set(0, 10);
    }
    @Test
    public void testIndexOf() {
        int idx_of_number_5 = linkedList.indexOf(5);
        assertEquals(4, idx_of_number_5);

        linkedList = (ImmutableLinkedList) linkedList.set(0, "Hi");
        int idx_of_string = linkedList.indexOf("Hi");
        assertEquals(0, idx_of_string);

        linkedList = (ImmutableLinkedList) linkedList.set(0, new Node());
        int idx_node = linkedList.indexOf(new Node());
        assertEquals(-1, idx_node);

        int idx_of_non_existed = linkedList.indexOf("Non existing value");
        assertEquals(-1, idx_of_non_existed);
    }
    @Test
    public void testSize() {
        assertEquals(5, linkedList.size());
        assertEquals(0, linkedEmptyList.size());
    }
    @Test
    public void testClear() {
        assertEquals(5, linkedList.size());
        linkedList = (ImmutableLinkedList) linkedList.clear();
        assertEquals(0, linkedList.size());
    }
    @Test
    public void testIsEmpty() {
        assertEquals(false, linkedList.isEmpty());
        assertEquals(true, linkedEmptyList.isEmpty());
    }
    @Test
    public void testToArray() {
        linkedList = (ImmutableLinkedList) linkedList.add(10);
        assertArrayEquals(new Object[]{ 10, 1, 2, 3, 4, 5}, linkedList.toArray());

        linkedList = (ImmutableLinkedList) linkedList.addAll(new Object[]{30, 20});
        assertArrayEquals(new Object[]{30, 20, 10, 1, 2, 3, 4, 5}, linkedList.toArray());

        assertArrayEquals(new Object[]{}, linkedEmptyList.toArray());

        linkedEmptyList = (ImmutableLinkedList) linkedEmptyList.add(null);
        assertArrayEquals(new Object[]{null}, linkedEmptyList.toArray());
    }
    @Test
    public void testAddFirst() {
        linkedList = (ImmutableLinkedList) linkedList.addFirst(10);
        assertEquals("[10, 1, 2, 3, 4, 5]", linkedList.toString());
    }
    @Test
    public void testAddLast() {
        linkedList = (ImmutableLinkedList) linkedList.addLast(10);
        assertEquals("[1, 2, 3, 4, 5, 10]", linkedList.toString());
    }
    @Test
    public void testGetHead() {
        Node head = linkedList.getHead();
        assertEquals("1", head.toString());
    }
    @Test
    public void testGetTail() {
        Node tail = linkedList.getTail();
        assertEquals("5", tail.toString());
    }
    @Test
    public void testGetFirst() {
        int value = (int) linkedList.getFirst();
        assertEquals(1, value);
    }
    @Test
    public void testGetLast() {
        int value = (int) linkedList.getLast();
        assertEquals(5, value);
    }
    @Test
    public void testRemoveFirst() {
        linkedList = (ImmutableLinkedList) linkedList.removeFirst();
        assertEquals("[2, 3, 4, 5]", linkedList.toString());
    }
    @Test
    public void testRemoveLast() {
        linkedList = (ImmutableLinkedList) linkedList.removeLast();
        assertEquals("[1, 2, 3, 4]", linkedList.toString());
    }
}