package com.company;

public class ThreadFactory extends ObjectFactory<Thread> {
    @Override
    public Thread createObject() {
        return new Thread();
    }
}