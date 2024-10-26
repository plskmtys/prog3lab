package org.lab;

import java.util.ArrayList;
import java.util.Random;

public class Application {
    public static void main(String[] args) {
        FIFO fi = new FIFO();
        Random random = new Random();

        ArrayList<Thread> prods = new ArrayList<>();
        for (int i = 1; i < 4; ++i) {
            Producer prod = new Producer("prod" + i, fi, random.nextInt(2000));
            Thread prodThread = new Thread(prod);
            prods.add(prodThread);
            prodThread.start();
        }

        ArrayList<Thread> conss = new ArrayList<>();
        for (int i = 1; i < 5; ++i) {
            Consumer cons = new Consumer("cons" + i, fi, random.nextInt(2000));
            Thread consThread = new Thread(cons);
            conss.add(consThread);
            consThread.start();
        }
    }
}
