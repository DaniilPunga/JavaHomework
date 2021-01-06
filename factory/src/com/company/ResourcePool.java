package com.company;

import java.util.concurrent.*;

public class ResourcePool<V> {

    private BlockingQueue<PoolElement<Long, V>> pool;
    private LinkedBlockingQueue<V> executableObjects = new LinkedBlockingQueue<V>();
    private ExecutorService executor = Executors.newCachedThreadPool();
    private ObjectFactory<V> resourceFactory;

    private int size;
    private int waitingTime;
    private boolean poolShaddowned;

    public ResourcePool(int size, int waitingTime, ObjectFactory<V> objectFactory) {
        this.size = size;
        this.resourceFactory = objectFactory;
        this.waitingTime = waitingTime;
        pool = new LinkedBlockingQueue<>(size);
        for (int i = 0; i < size; ++i) {
            V newObject = objectFactory.createObject();
            PoolElement<Long, V> newObj = new PoolElement<>(CurrentTime(), newObject);
            pool.add(newObj);
        }
        poolShaddowned = false;
    }

    public ResourcePool(int waitingTime, ObjectFactory<V> objectFactory) {
        this.size = Runtime.getRuntime().availableProcessors();
        this.resourceFactory = objectFactory;
        this.waitingTime = waitingTime;
        pool = new LinkedBlockingQueue<>(size);
        for (int i = 0; i < size; ++i) {
            V newObject = objectFactory.createObject();
            PoolElement<Long, V> newObj = new PoolElement<>(CurrentTime(), newObject);
            pool.add(newObj);
        }
        poolShaddowned = false;
    }

    public int getPoolSize() {
        return this.pool.size();
    }

    public V takeObject() {
        if (!poolShaddowned) {
            long curTime;
            V value;
            try {
                PoolElement<Long, V> requestedResource = pool.take();
                if (!requestedResource.isAlive(waitingTime)) {
                    pool.remove(requestedResource);
                    PoolElement<Long, V> newResource = createNewResource();
                    value = newResource.takeValue();
                    if (newResource == null) {
                        return null;
                    }
                } else {
                    value = requestedResource.takeValue();
                }
                executableObjects.put(value);
                return value;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        throw new IllegalStateException("Pool is Shaddowned!");
    }

    public PoolElement<Long, V> createNewResource() {
        long curTime = CurrentTime();
        V newObject = resourceFactory.createObject();
        PoolElement<Long, V> newResource = new PoolElement<>(curTime, newObject);
        if (pool.add(newResource) && newResource.isAlive(waitingTime)) {
            return newResource;
        } else {
            System.out.println("Error creating a new pool item. Null will be returned");
            return null;
        }
    }

    private long CurrentTime() {
        return System.currentTimeMillis();
    }

    public void dropRes(V value) {
        if (poolShaddowned) {
            throw new IllegalStateException("Pool is terminated!");
        } else if (value == null) {
            throw new IllegalArgumentException("Resource is null");
        } else {
            executableObjects.remove(value);
            PoolElement<Long, V> retResource = new PoolElement<>(CurrentTime(), value);
            pool.offer(retResource);
        }
    }

    public void shutdown() {
        poolShaddowned = true;
        executor.shutdownNow();
    }
}
