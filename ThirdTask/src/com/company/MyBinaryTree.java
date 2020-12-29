package com.company;

import java.lang.Math;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MyBinaryTree<Key, Val> {

    public TreeNode<Key, Val> root;

    public MyBinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    int getHeight(TreeNode<Key, Val> root) {
        if (root != null) {
            return root.getHeight();
        } else {
            return 0;
        }
    }

    int getBalanceFactor(TreeNode<Key, Val> root) {
        if (root == null) {
            return 0;
        } else {
            return getHeight(root.getRight()) - getHeight(root.getLeft());
        }
    }

    void fixHeight(TreeNode<Key, Val> root) {
        if (root == null) {
            return;
        }
        int leftHeight = getHeight(root.getLeft());
        int rightHeight = getHeight(root.getRight());
        root.newheight(Math.max(leftHeight, rightHeight) + 1);
    }

    TreeNode<Key, Val> balance(TreeNode<Key, Val> root) {
        fixHeight(root);
        if (getBalanceFactor(root) == 2) {
            if (getBalanceFactor(root.getRight()) < 0) {
                TreeNode<Key, Val> current = root.getRight();
                TreeNode<Key, Val> leftTree = current.getLeft();
                current.newleft(leftTree.getRight());
                leftTree.newright(current);
                fixHeight(current);
                fixHeight(leftTree);
                root.newright(leftTree);
            }
            TreeNode<Key, Val> current = root;
            TreeNode<Key, Val> rightTree = current.getRight();
            current.newright(rightTree.getLeft());
            rightTree.newleft(current);
            fixHeight(current);
            fixHeight(rightTree);
            return rightTree;
        }
        if (getBalanceFactor(root) == (-2)) {
            if (getBalanceFactor(root.getLeft()) > 0) {
                TreeNode<Key, Val> current = root.getLeft();
                TreeNode<Key, Val> rightTree = current.getRight();
                current.newright(rightTree.getLeft());
                rightTree.newleft(current);
                fixHeight(current);
                fixHeight(rightTree);
                root.newleft(rightTree);
            }
            TreeNode<Key, Val> current = root;
            TreeNode<Key, Val> leftTree = current.getLeft();
            current.newleft(leftTree.getRight());
            leftTree.newright(current);
            fixHeight(current);
            fixHeight(leftTree);
            return leftTree;
        }
        return root;
    }

    private TreeNode<Key, Val> addRecursive(Key key, Val value, TreeNode<Key, Val> root) {
        TreeNode<Key, Val> newNode = new TreeNode<>(key, value);

        if (value == null) {
            throw new NullPointerException("Null values are not permitted!");
        } else if (root == null) {
            root = newNode;
        } else if (root.compareTo(key) < 0) {
            root.newleft(addRecursive(key, value, root.getLeft()));
        } else if (root.compareTo(key) > 0) {
            root.newright(addRecursive(key, value, root.getRight()));
        } else if (root.compareTo(key) == 0) {
            root.newvalue(value);
            return root;
        }
        return balance(root);
    }


    public Val add(Key key, Val value) {
        this.root = addRecursive(key, value, this.root);
        return this.root.getVal();
    }

    public boolean contains(Key key) {
        TreeNode<Key, Val> current = this.root;
        while (current != null) {
            if (current.compareTo(key) < 0) {
                current = current.getLeft();
            } else if (current.compareTo(key) > 0) {
                current = current.getRight();
            } else {
                return true;
            }
        }
        return false;

    }

    public Val get(Key key) {
        if (this.contains(key) != true) {
            throw new NoSuchElementException();
        }
        TreeNode<Key, Val> current = this.root;
        while (current != null) {
            if (current.compareTo(key) < 0) {
                current = current.getLeft();
            } else if (current.compareTo(key) > 0) {
                current = current.getRight();
            } else {
                return current.getVal();
            }
        }
        return null;

    }


    private TreeNode<Key, Val> removeSmallestValue(TreeNode<Key, Val> root) {
        if (root.getLeft() == null) {
            return root.getRight();
        } else {
            root.newleft(removeSmallestValue(root.getLeft()));
            return balance(root);
        }
    }


    private TreeNode<Key, Val> removeRecursive(Key key, TreeNode<Key, Val> root) {
        if (this.contains(key) != true) {
            throw new NoSuchElementException();
        }
        TreeNode<Key, Val> current = root;

        if (current == null) {
            return null;
        }

        if (current.compareTo(key) > 0) {
            current.newright(removeRecursive(key, current.getRight()));
        } else if (current.compareTo(key) < 0) {
            current.newleft(removeRecursive(key, current.getLeft()));
        } else {
            TreeNode<Key, Val> leftTree = current.getLeft();
            TreeNode<Key, Val> rightTree = current.getRight();
            if (rightTree == null) {
                return balance(leftTree);
            }
            TreeNode<Key, Val> currentSmallest = rightTree;
            while (currentSmallest.getLeft() != null) {
                currentSmallest = currentSmallest.getLeft();
            }
            TreeNode<Key, Val> min = currentSmallest;
            min.newright(removeSmallestValue(rightTree));
            min.newleft(leftTree);

            return balance(min);
        }
        return balance(current);
    }

    public void remove(Key key) {
        this.root = removeRecursive(key, this.root);
        return;
    }


}