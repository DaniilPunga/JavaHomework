package com.company;


import java.util.*;

public class DoublList<Item> implements List<Item> {
    private Node<Item> head, tail;
    private int sizeL;

    public DoublList() {
        head = null;
        tail = null;
        sizeL = 0;
    }


    @Override
    public Item remove(int index) {
        int i = 0;
        checkIndex(index);
        Node current = head;
        while (current != null) {
            if (i == index) {
                if (current == head) {
                    if (sizeL <= 1) {
                        head = null;
                        sizeL--;
                        return (Item) current.data;
                    } else {
                        head = current.next;
                        sizeL--;
                        current.next.prev = null;
                        return (Item) current.data;
                    }
                }
                sizeL--;
                current.prev.next = current.next;
                current.next.prev = current.prev;
                return (Item) current.data;
            }
            current = current.next;
            ++i;
        }
        return null;

    }

    @Override
    public void add(int index, Item data) {
        checkValue(data);
        checkIndex(index);
        if (index > sizeL) {
            System.out.println("Индекс больше длинны масива");
        } else if (head == null) {
            Node current = new Node<>(data);
            head = current;
            tail = current;
            ++sizeL;
        } else if (index == sizeL) {
            Node<Item> current = new Node(data);
            tail.next = current;
            current.prev = tail;
            tail = current;
            ++sizeL;
        } else if (index == 0) {
            Node<Item> current = new Node(data);
            current.next = head;
            head = current;
            ++sizeL;
        } else {
            int i = 0;
            Node current = head;
            Node added = new Node(data);
            while (current != null) {
                if (i == index) {
                    Node privious = current.prev;
                    privious.next = added;
                    added.prev = privious;
                    current.prev = added;
                    added.next = current;

                }
                current = current.next;
                ++i;
            }
            ++sizeL;
        }
    }


    @Override
    public boolean add(Item data) {

        add(sizeL, data);
        return true;
    }

    @Override
    public boolean contains(Object object) {
        if (sizeL == 0) {
            throw new EmptyStackException();
        }
        Node current = head;
        while (current != null) {
            if (current.data.equals(object)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Item get(int index) {
        int i = 0;
        checkIndex(index);
        Node current = head;
        while (current != null) {
            if (i == index) {
                return (Item) current.data;
            }
            current = current.next;
            ++i;
        }
        return null;
    }


    public boolean isEmpty() {
        if (sizeL == 0) {
            return true;
        } else {
            return false;
        }

    }

    public int size() {
        return sizeL;

    }

    private void checkIndex(int index) {
        if ((index > sizeL) || (index < 0)) {
            throw new IndexOutOfBoundsException("Index > list size or Index < 0");
        }
    }

    private void checkValue(Item data) {
        if (data == null) {
            throw new NullPointerException("Null elements are not permitted");
        }
    }

    @Override
    public List<Item> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Iterator<Item> iterator() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("This operation is not supported");
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean addAll(int index, Collection<? extends Item> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Item set(int index, Item element) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public ListIterator<Item> listIterator() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public ListIterator<Item> listIterator(int index) {
        throw new UnsupportedOperationException("This operation is not supported");
    }


}
