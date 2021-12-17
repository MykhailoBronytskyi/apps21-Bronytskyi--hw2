package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableLinkedList implements ImmutableList {
    private final Node head;
    private final Node tail;
    private final int size;

    private int normaliseIndex(int index) {

        if (index > this.size) {
            return this.size;

        } else if (index < 0) {

            int refactored_negative_index = this.size + index + 1;
            if (refactored_negative_index < 0) {
                return 0;
            }
            index = refactored_negative_index;
        }
        return index;
    }

    private int checkAndNormaliseIndex(int index) {

        if (index >= this.size) {
            throw new IllegalArgumentException("Index is too large");

        } else if (index < 0) {

            int refactored_negative_index = this.size + index;
            if (refactored_negative_index < 0) {
                throw new IllegalArgumentException("Negative index is to big by abs");
            }
            index = refactored_negative_index;
        }
        return index;
    }

    private Node getNodeByIndex(int index) {
        Node new_head = this.head;
        for (int idx = 0; idx < index; idx++) {
            new_head = new_head.getNext();
        }
        return new_head;
    }

    private ImmutableLinkedList copy() {
        return new ImmutableLinkedList(this.toArray());
    }

    public ImmutableLinkedList(Object[] elements) {
        if (elements.length == 0) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            return;
        }
        Node new_head = new Node();
        new_head.setValue(elements[0]);
        this.head = new_head;

        for (int idx = 1; idx < elements.length; idx++) {
            Object el = elements[idx];

            Node next_node = new Node();
            next_node.setValue(el);
            next_node.setPrevious(new_head);

            new_head.setNext(next_node);
            new_head = next_node;
        }
        this.tail = new_head;
        this.size = elements.length;
    }

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public ImmutableList add(Object el) {
        return this.add(0, el);
    }

    @Override
    public ImmutableList add(int index, Object el) {
        return this.addAll(index, new Object[]{el});
    }

    @Override
    public ImmutableList addAll(Object[] arr) {
        return this.addAll(0, arr);
    }

    @Override
    public ImmutableList addAll(int index, Object[] arr) {

        index = this.normaliseIndex(index);

        int new_len = this.size + arr.length;

        Object[] new_array = new Object[new_len];
        Node new_head = this.head;

        int idx = 0;
        for (; idx < index; idx++) {
            new_array[idx] = new_head.getValue();
            new_head = new_head.getNext();
        }

        for (Object el : arr) {
            new_array[idx] = el;
            idx++;
        }

        for (; idx < new_len; idx++) {
            new_array[idx] = new_head.getValue();
            new_head = new_head.getNext();
        }
        return new ImmutableLinkedList(new_array);
    }

    @Override
    public Object get(int index) {
        int new_idx = this.checkAndNormaliseIndex(index);
        Node new_head = getNodeByIndex(new_idx);
        return new_head.getValue();
    }

    @Override
    public ImmutableList remove(int index) {

        int new_idx = this.checkAndNormaliseIndex(index);
        Object[] new_arr = new Object[this.size - 1];
        Node new_node = this.head;

        for (int idx = 0; idx < new_idx; idx++) {
            new_arr[idx] = new_node.getValue();
            new_node = new_node.getNext();
        }
        new_node = new_node.getNext();

        for (int idx = new_idx; idx < this.size - 1; idx++) {
            new_arr[idx] = new_node.getValue();
            new_node = new_node.getNext();
        }
        return new ImmutableLinkedList(new_arr);
    }

    @Override
    public ImmutableList set(int index, Object el) {

        int new_idx = this.checkAndNormaliseIndex(index);

        ImmutableLinkedList new_list = this.copy();

        Node id_node = new_list.getNodeByIndex(new_idx);
        id_node.setValue(el);

        return new_list;
    }

    @Override
    public int indexOf(Object el) {
        Node new_head = this.head;
        for (int idx = 0; idx < this.size; idx++) {
            if (el == new_head.getValue()) {
                return idx;
            }
            new_head = new_head.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {

        Object[] new_array = new Object[this.size];
        Node new_head = this.head;

        for (int idx = 0; idx < this.size; idx++) {
            new_array[idx] = new_head.getValue();
            new_head = new_head.getNext();
        }
        return new_array;
    }

    @Override

    public String toString() {
        return Arrays.toString(this.toArray());
    }

    public ImmutableLinkedList addFirst(Object el) {
        return (ImmutableLinkedList) this.add(el);
    }

    public ImmutableLinkedList addLast(Object el) {
        return (ImmutableLinkedList) this.add(this.size, el);
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public Object getFirst() {
        return this.head.getValue();
    }

    public Object getLast() {
        return this.tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) this.remove(this.size - 1);
    }
}
