package com.company;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of Map interface, based on RedBlackTree class.
 */

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {
    public MyBinaryTree<K, V> tree;
    private int size = 0;

    public TreeMap() {
        this.tree = new MyBinaryTree();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return tree.contains((K) key);
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("This method is not implemented yet.");
    }

    @Override
    public V get(Object key) {
        return tree.get((K) key);
    }


    @Override
    public V put(K key, V value) {
        final V add = tree.add(key, value);
        if (add == null) {
            size = 0;
            return null;
        }
        size++;
        return add;
    }


    @Override
    public V remove(Object key) {
        tree.remove((K) key);
        size--;
        return null;
    }

    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException("This method is not implemented yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method is not implemented yet.");
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("This method is not implemented yet.");
    }

    @Override
    public Collection values() {
        throw new UnsupportedOperationException("This method is not implemented yet.");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("This method is not implemented yet.");
    }
}