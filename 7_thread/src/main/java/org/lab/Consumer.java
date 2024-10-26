package org.lab;

public class Consumer extends Thread {
    private String str;
    private FIFO fi;
    private int st;

    public Consumer(String str, FIFO fi, int st) {
        this.str = str;
        this.fi = fi;
        this.st = st;
    }

    @Override
    public void run() {
        go();
    }

    public void go() {
        while (true) {
            long ido = System.currentTimeMillis() % 100000;
            try {
                synchronized (fi) {
                    String message = fi.get();
                    System.out.println("consumed " + str + " " + message + " " + ido);
                }
                Thread.sleep(st);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
