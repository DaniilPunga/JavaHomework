package Test;

import com.company.ListQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ListQueueTest {

    @Test
    void testIsEmptyForEmptyQueue() {
        ListQueue<String> queue = new ListQueue<>();
        Assertions.assertEquals(true, queue.isEmpty());
    }

    @Test
    void testIsEmptyForNotEmptyQueue() {
        ListQueue<String> queue = new ListQueue<>();
        queue.add("g");
        Assertions.assertEquals(false, queue.isEmpty());
    }

    @Test
    void testSizeForEmptyQueue() {
        ListQueue<String> queue = new ListQueue<>();
        Assertions.assertEquals(0, queue.size());
    }

    @Test
    void testSizeForNotEmptyQueue() {
        ListQueue<String> queue = new ListQueue<>();
        queue.add("g");
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void testPeekForEmptyQueue() {
        ListQueue<String> queue = new ListQueue<>();
        Assertions.assertEquals(null, queue.peek());
    }

    @Test
    void testPeekForNotEmptyQueue() {
        ListQueue<String> queue = new ListQueue<>();
        queue.add("g");
        Assertions.assertEquals("g", queue.peek());
    }

    @Test
    void testPopForEmptyQueue() {
        ListQueue<String> queue = new ListQueue<>();
        Assertions.assertEquals(null, queue.pop());
    }

    @Test
    void testPopForNotEmptyQueue() {
        ListQueue<String> queue = new ListQueue<>();
        queue.add("g");
        Assertions.assertEquals("g", queue.pop());
    }

    @Test
    void testPopForNotEmptyQueue2() {
        ListQueue<String> queue = new ListQueue<>();
        queue.add("a");
        queue.add("g");
        queue.pop();
        Assertions.assertEquals("g", queue.pop());
    }

    @Test
    void testPeekForNotEmptyQueue2() {
        ListQueue<String> queue = new ListQueue<>();
        queue.add("a");
        queue.add("g");
        Assertions.assertEquals("a", queue.peek());
    }

    @Test
    void testAdd() {
        ListQueue<String> queue = new ListQueue<>();
        queue.add("g");
        queue.add("h");
        Assertions.assertEquals(2, queue.size());
    }

}
