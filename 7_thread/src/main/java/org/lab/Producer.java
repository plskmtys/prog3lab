package org.lab;

public class Producer implements Runnable {
    private String str;
    private FIFO fi;
    private int st;

    public Producer(String str, FIFO fi, int st) {
        this.str = str;
        this.fi = fi;
        this.st = st;
    }

    @Override
    public void run() {
        go();
    }

    public void go() {
        int i = 0;
        while (true) {
            long ido = System.currentTimeMillis() % 100000;
            String message = str + " " + i + " " + ido;
            try {
                synchronized (fi) {
                    fi.put(message);
                }
                System.out.println("produced " + message);
                Thread.sleep(st);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}