package ua.edu.ucu.collections.immutable;

public class Node {
    private Node previous = null;
    private Node next = null;
    private Object value = null;

    public Node() {
    }

    @Override
    public String toString() {
        return value.toString();
    }


    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node prev) {
        previous = prev;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object val) {
        value = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node new_next) {
        next = new_next;
    }
}

