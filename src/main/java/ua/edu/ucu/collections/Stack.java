package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.collections.immutable.ImmutableList;

public class Stack {
    private ImmutableLinkedList stack_list = new ImmutableLinkedList();

    public void push(Object el) {
        stack_list = (ImmutableLinkedList) stack_list.addFirst(el);
    }

    public Object pop() {
        Object el = stack_list.getFirst();
        stack_list = (ImmutableLinkedList) stack_list.removeFirst();
        return el;
    }

    public Object peek() {
        Object el = stack_list.getFirst();
        return el;
    }
}
