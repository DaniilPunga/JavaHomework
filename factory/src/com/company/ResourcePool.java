package com.company;

import java.util.concurrent.*;

public class ResourcePool<V> {

    private BlockingQueue<PoolElement<Long, V>> pool;
    private LinkedBlockingQueue<V> executableObjects = new LinkedBlockingQueue<V>();
    private ExecutorService executor = Executors.newCachedThreadPool();
    private ObjectFactory<V> objectFactory;

    private int size;
    private int waitingTime;
    private boolean poolIsTerminated;

    public ResourcePool(int size, int waitingTime, ObjectFactory<V> objectFactory) {
        this.size = size;
        this.objectFactory = objectFactory;
        this.waitingTime = waitingTime;
        pool = new LinkedBlockingQueue<>(size);
        createPool();
        poolIsTerminated = false;
    }

    public ResourcePool(int waitingTime, ObjectFactory<V> objectFactory) {
        this(Runtime.getRuntime().availableProcessors(), waitingTime, objectFactory);
    }

    public void createPool() {
        long curTime;
        for (int i = 0; i < size; ++i) {
            curTime = System.currentTimeMillis();
            PoolElement<Long, V> newObj = new PoolElement<>(curTime, createObject());
            pool.add(newObj);
        }
    }

    public int getPoolSize() {
        return this.pool.size();
    }

    public V createObject() {
        V newObject = objectFactory.createObject();
        return newObject;
    }

    public V acquire() {
        if (poolIsTerminated) {
            throw new IllegalStateException("Pool is terminated!");
        }
        long curTime;
        V value;
        if (pool.size() == 0) {
            curTime = System.currentTimeMillis();
            PoolElement<Long, V> newResource = tryCreateNewResource(curTime);
            if (newResource == null) {
                return null;
            }
        }
        try {
            PoolElement<Long, V> requestedResource = pool.take();
            if (requestedResource.isAlive(waitingTime)) {
                value = requestedResource.getValue();
                executableObjects.add(value);
                return value;
            } else {
                pool.remove(requestedResource);
                curTime = System.currentTimeMillis();
                PoolElement<Long, V> newResource = tryCreateNewResource(curTime);
                if (newResource != null) {
                    value = newResource.getValue();
                    executableObjects.add(value);
                    return value;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PoolElement<Long, V> tryCreateNewResource(long curTime) {
        PoolElement<Long, V> newResource = new PoolElement<>(curTime, createObject());
        if (pool.add(newResource)) {
            if (newResource.isAlive(waitingTime)) {
                return newResource;
            } else {
                System.out.println("Error creating a new pool item. Null will be returned");
                return null;
            }
        } else {
            System.out.println("Error creating a new pool item. Null will be returned");
            return null;
        }
    }

    public void returnBack(V value) {
        if (poolIsTerminated) {
            throw new IllegalStateException("Pool is terminated!");
        }
        if (value != null) {
            executableObjects.remove(value);
            long curTime = System.currentTimeMillis();
            PoolElement<Long, V> retResource = new PoolElement<>(curTime, value);
            pool.offer(retResource);
        }
    }

    public void shutdown() {
        poolIsTerminated = true;
        executor.shutdownNow();
    }
}
