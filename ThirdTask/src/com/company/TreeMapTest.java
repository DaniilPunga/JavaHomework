package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeMapTest {
    @Test
    void testPutToEmptyTreeMap() {
        TreeMap<String, Integer> tree = new TreeMap<>();
        tree.put("One", 1);
        Assertions.assertTrue(tree.containsKey("One"));
    }

    @Test
    void testPutToNotEmptyTreeMap() {
        TreeMap tree = new TreeMap<>();
        tree.put("1", 2);
        tree.put("13", 2);
        tree.put("12", 2);
        Assertions.assertTrue(tree.containsKey("12"));
    }

    @Test
    void testGetElem() {
        TreeMap tree = new TreeMap<>();
        tree.put(1, 2);
        tree.put(13, 2);
        tree.put(1, 3);
        Assertions.assertEquals(3, tree.get(1));
    }

    @Test
    void testContainsElem() {
        TreeMap tree = new TreeMap<>();
        tree.put(1, 2);
        tree.put(13, 2);
        tree.put(12, 2);
        Assertions.assertTrue(tree.containsKey(12));
    }

    @Test
    void testIsEmptyWithEmptyMap() {
        TreeMap tree = new TreeMap<>();
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testRemoveElemMap2() {
        TreeMap tree = new TreeMap<>();
        tree.put(1, 2);
        tree.remove(1);
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testIsEmptyWithNotEmptyMap() {
        TreeMap tree = new TreeMap<>();
        tree.put(12, 2);
        Assertions.assertEquals(1, tree.size());
        Assertions.assertFalse(tree.isEmpty());
    }

}


