package Test;

import com.company.DoublList;
import com.company.ListQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoublListTest {

    @Test
    void testIsEmptyForEmptyList() {
        DoublList<String> list = new DoublList<>();
        Assertions.assertEquals(true, list.isEmpty());
    }

    @Test
    void testIsEmptyFornotEmptyList() {
        DoublList<String> list = new DoublList<>();
        list.add("9");
        Assertions.assertEquals(false, list.isEmpty());
    }


    @Test
    void testSizeForEmptyList() {
        DoublList<String> list = new DoublList<>();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testSizeForNotEmptyList() {
        DoublList<String> list = new DoublList<>();
        list.add("g");
        Assertions.assertEquals(1, list.size());
    }

    @Test
    void testRemoveForEmptyList() {
        DoublList<String> list = new DoublList<>();
        Assertions.assertEquals(null, list.remove(0));
    }

    @Test
    void testRemoveForNotEmptyList() {
        DoublList<String> list = new DoublList<>();
        list.add("g");
        Assertions.assertEquals("g", list.remove(0));

    }

    @Test
    void testRemoveForNotEmptyList2() {
        DoublList<String> list = new DoublList<>();
        list.add("a");
        list.add("g");
        Assertions.assertEquals("a", list.remove(0));
    }

    @Test
    void testAdd() {
        DoublList<String> list = new DoublList<>();
        list.add("a");
        list.add("c");
        list.add(0, "g");
        list.add(2, "b");
        Assertions.assertEquals("b", list.remove(2));
    }

    @Test
    void testGet() {
        DoublList<String> list = new DoublList<>();
        list.add("a");
        list.add("c");
        list.add(0, "g");
        list.add(2, "b");
        Assertions.assertEquals("g", list.get(0));
    }

    @Test
    void testGetFromEmpty() {
        DoublList<String> list = new DoublList<>();

        Assertions.assertEquals(null, list.get(0));
    }

    @Test
    void testContain() {
        DoublList<String> list = new DoublList<>();
        list.add("a");
        list.add("c");
        list.add(0, "g");
        list.add(2, "b");
        Assertions.assertEquals(true, list.contains("g"));
    }

    @Test
    void testContainFalse() {
        DoublList<String> list = new DoublList<>();
        list.add("a");
        list.add("c");
        list.add(0, "g");
        list.add(2, "b");
        Assertions.assertEquals(false, list.contains("e"));
    }

}
