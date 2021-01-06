package com.company;

public class PoolElement<Long, V> {
    private V value;
    private long time;

    public PoolElement(long time, V value) {
        this.time = time;
        this.value = value;
    }

    public V takeValue() {
        return this.value;
    }

    public long takeTime() {
        return this.time;
    }

    public boolean isAlive(long waitingTime) {
        long curTime = System.currentTimeMillis();
        long time_of_work = curTime - this.time;
        return !(time_of_work > waitingTime);
    }
}