package com.company;

import java.util.Comparator;

public class TreeNode<Key, Val> {
    Key key;
    Val value;
    TreeNode<Key, Val> left;
    TreeNode<Key, Val> right;
    int height;

    TreeNode(Key key, Val data) {
        this.value = data;
        this.key = key;
        this.right = null;
        this.left = null;
        this.height = 1;
    }

    public int getHeight() {
        return this.height;
    }

    public Val getVal() {
        return this.value;
    }

    public Key getKey() {
        return this.key;
    }

    public TreeNode<Key, Val> getLeft() {
        return this.left;
    }

    public TreeNode<Key, Val> getRight() {
        return this.right;
    }

    public int compareTo(Key val1) {
        Comparator<Key> comp = new Comparator<Key>() {
            @Override
            public int compare(Key o1, Key o2) {
                try {
                    Comparable val1 = (Comparable) o1;
                    Comparable val2 = (Comparable) o2;
                    return val1.compareTo(val2);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        return comp.compare(val1, this.key);
    }
}
