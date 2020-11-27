package com.company;

import java.util.*;

public class ListQueue<Item> implements Queue<Item> {
    private DoublList<Item> queue = new DoublList();

    public ListQueue() {
        queue = new DoublList<>();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = queue.isEmpty();
        return isEmpty;

    }


    @Override
    public Item peek() {
        boolean isEmpty = queue.isEmpty();
        if (isEmpty) {
            return null;
        } else {
            return queue.get(0);
        }
    }

    public Item pop() {
        boolean isEmpty = queue.isEmpty();
        if (isEmpty) {
            return null;
        } else {

            return queue.remove(0);
        }
    }

    @Override
    public boolean add(Item e) {

        return queue.add(e);

    }


    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Iterator<Item> iterator() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Unsupported method");
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean offer(Item e) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Item remove() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Item poll() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Item element() {
        throw new UnsupportedOperationException("Unsupported method");
    }


}
