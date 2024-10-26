package org.lab;

import java.util.LinkedList;

public class FIFO {
    private final LinkedList<String> list = new LinkedList<>();
    private final int capacity = 10;

    public synchronized void put(String item) throws InterruptedException {
        System.out.println("put method called by thread ID: " + Thread.currentThread().getName());
        while (list.size() == capacity) {
            wait();
        }
        list.add(item);
        notifyAll();
    }

    public synchronized String get() throws InterruptedException {
        System.out.println("get method called by thread ID: " + Thread.currentThread().getName());
        while (list.isEmpty()) {
            wait();
        }
        String item = list.removeFirst();
        notifyAll();
        return item;
    }
}
