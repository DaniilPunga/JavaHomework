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
            return root.height;
        } else {
            return 0;
        }
    }

    int getBalanceFactor(TreeNode<Key, Val> root) {
        if (root == null) {
            return 0;
        } else {
            return getHeight(root.right) - getHeight(root.left);
        }
    }

    void fixHeight(TreeNode<Key, Val> root) {
        if (root == null) {
            return;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        root.height = Math.max(leftHeight, rightHeight) + 1;
    }

    TreeNode<Key, Val> balance(TreeNode<Key, Val> root) {
        fixHeight(root);
        if (getBalanceFactor(root) == 2) {
            if (getBalanceFactor(root.right) < 0) {
                TreeNode<Key, Val> current = root.right;
                TreeNode<Key, Val> leftTree = current.left;
                current.left = leftTree.right;
                leftTree.right = current;
                fixHeight(current);
                fixHeight(leftTree);
                root.right = leftTree;
            }
            TreeNode<Key, Val> current = root;
            TreeNode<Key, Val> rightTree = current.right;
            current.right = rightTree.left;
            rightTree.left = current;
            fixHeight(current);
            fixHeight(rightTree);
            return rightTree;
        }
        if (getBalanceFactor(root) == (-2)) {
            if (getBalanceFactor(root.left) > 0) {
                TreeNode<Key, Val> current = root.left;
                TreeNode<Key, Val> rightTree = current.right;
                current.right = rightTree.left;
                rightTree.left = current;
                fixHeight(current);
                fixHeight(rightTree);
                root.left = rightTree;
            }
            TreeNode<Key, Val> current = root;
            TreeNode<Key, Val> leftTree = current.left;
            current.left = leftTree.right;
            leftTree.right = current;
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
            root.left = addRecursive(key, value, root.left);
        } else if (root.compareTo(key) > 0) {
            root.right = addRecursive(key, value, root.right);
        } else if (root.compareTo(key) == 0) {
            root.value = value;
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
                current = current.left;
            } else if (current.compareTo(key) > 0) {
                current = current.right;
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
                current = current.left;
            } else if (current.compareTo(key) > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;

    }


    private TreeNode<Key, Val> removeSmallestValue(TreeNode<Key, Val> root) {
        if (root.left == null) {
            return root.right;
        } else {
            root.left = removeSmallestValue(root.left);
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
            current.right = removeRecursive(key, current.right);
        } else if (current.compareTo(key) < 0) {
            current.left = removeRecursive(key, current.left);
        } else {
            TreeNode<Key, Val> leftTree = current.left;
            TreeNode<Key, Val> rightTree = current.right;
            if (rightTree == null) {
                return balance(leftTree);
            }
            TreeNode<Key, Val> currentSmallest = rightTree;
            while (currentSmallest.left != null) {
                currentSmallest = currentSmallest.left;
            }
            TreeNode<Key, Val> min = currentSmallest;
            min.right = removeSmallestValue(rightTree);
            min.left = leftTree;

            return balance(min);
        }
        return balance(current);
    }

    public void remove(Key key) {
        this.root = removeRecursive(key, this.root);
        return;
    }


}