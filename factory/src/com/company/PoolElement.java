package com.company;

public class PoolElement<Long, V> {
    private V value;
    private long time;

    public PoolElement(long time, V value) {
        this.time = time;
        this.value = value;
    }

    public V getValue() {
        return this.value;
    }

    public long getTime() {
        return time;
    }

    public boolean isAlive(long waitingTime) {
        long curTime = System.currentTimeMillis();
        if (curTime - this.time > waitingTime) {
            return false;
        } else {
            return true;
        }
    }
}