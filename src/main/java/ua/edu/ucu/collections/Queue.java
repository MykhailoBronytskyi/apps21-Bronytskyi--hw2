package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {

    private ImmutableLinkedList stack_list = new ImmutableLinkedList();

    public Object peek() {
        Object el = stack_list.getLast();
        return el;
    }

    public Object dequeue() {
        Object el = stack_list.getLast();
        stack_list = (ImmutableLinkedList) stack_list.removeLast();
        return el;
    }

    public void enqueue(Object el) {
        stack_list = (ImmutableLinkedList) stack_list.addFirst(el);
    }
}
