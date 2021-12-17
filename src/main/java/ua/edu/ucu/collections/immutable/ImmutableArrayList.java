package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.Objects;

public final class ImmutableArrayList implements ImmutableList {
    private final Object[] arr;

    private int normaliseIndex(int index) {
        int size = this.size();
        if (index > size) {
            return size;

        } else if (index < 0) {

            int refactored_negative_index = size + index + 1;
            if (refactored_negative_index < 0) {
                return 0;
            }
            index = refactored_negative_index;
        }
        return index;
    }

    private int checkAndNormaliseIndex(int index) {

        if (index >= this.size()) {
            throw new IllegalArgumentException("Index is too large");

        } else if (index < 0) {

            int refactored_negative_index = this.size() + index;
            if (refactored_negative_index < 0) {
                throw new IllegalArgumentException("Negative index is to big by abs");
            }
            index = refactored_negative_index;
        }
        return index;
    }

    public ImmutableArrayList(Object[] elements) {
        this.arr = new Object[elements.length];
        for (int idx = 0; idx < elements.length; idx++) {
            this.arr[idx] = elements[idx];
        }
    }


    public ImmutableArrayList() {
        this.arr = new Object[0];
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
    public ImmutableList addAll(Object[] array) {
        return this.addAll(0, array);
    }

    @Override
    public ImmutableList addAll(int index, Object[] array) {

        index = this.normaliseIndex(index);
        int new_len = this.size() + array.length;

        Object[] new_array = new Object[new_len];

        int idx = 0;
        int this_idx = 0;
        for (; idx < index; idx++) {
            new_array[idx] = this.arr[idx];
        }
        this_idx = idx;

        for (Object el : array) {
            new_array[idx] = el;
            idx++;
        }

        for (; idx < new_len; idx++) {
            new_array[idx] = this.arr[this_idx];
            this_idx++;
        }
        return new ImmutableArrayList(new_array);

    }

    @Override
    public Object get(int index) {
        index = this.checkAndNormaliseIndex(index);
        return this.arr[index];
    }

    @Override
    public ImmutableList remove(int index) {
        index = this.checkAndNormaliseIndex(index);
        Object[] new_array = new Object[this.size() - 1];

        for (int idx = 0; idx < index; idx++) {
            new_array[idx] = this.arr[idx];
        }
        for (int idx = index; idx < new_array.length; idx++) {
            new_array[idx] = this.arr[idx + 1];
        }

        return new ImmutableArrayList(new_array);
    }

    @Override
    public ImmutableList set(int index, Object el) {
        index = this.checkAndNormaliseIndex(index);
        ImmutableArrayList new_list = new ImmutableArrayList(this.toArray());
        new_list.arr[index] = el;
        return new_list;
    }

    @Override
    public int indexOf(Object el) {
        int idx = 0;
        for (Object element : this.arr) {
            if (element == el) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.arr.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] new_array = new Object[this.size()];
        for (int idx = 0; idx < this.size(); idx++) {
            new_array[idx] = this.arr[idx];
        }
        return new_array;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }
}
