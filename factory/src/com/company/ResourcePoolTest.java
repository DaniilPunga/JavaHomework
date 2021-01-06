package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;


class ResourcePoolTest {

    @Test
    void testCreatePool() {
        int size = 10;
        ResourcePool<Thread> threadPool = new ResourcePool<>(size, 2000, new ThreadFactory());
        Assertions.assertEquals(10, threadPool.getPoolSize());
        size = 15;
        ResourcePool<FileWriter> filePool = new ResourcePool<>(size, 2000, new FileFactory());
        Assertions.assertEquals(15, filePool.getPoolSize());
    }

    @Test
    void throwsExceptionTakeObjectPoolIsTerminated() {
        int size = 7;
        ResourcePool<Thread> threadPool = new ResourcePool<>(size, 2000, new ThreadFactory());
        threadPool.shutdown();
        Assertions.assertThrows(IllegalStateException.class, () -> threadPool.takeObject());
    }

    @Test
    void throwsExceptionDropResPoolIsTerminated() {
        int size = 5;
        ResourcePool<Thread> threadPool = new ResourcePool<>(size, 2000, new ThreadFactory());
        Thread worker = threadPool.takeObject();
        threadPool.shutdown();
        Assertions.assertThrows(IllegalStateException.class, () -> threadPool.dropRes(worker));
    }


}
