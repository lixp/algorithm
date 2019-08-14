package org.framework.fftt;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: lixp
 * Date: 2019/8/13
 * Time: 10:32
 * Mail: lixp0915@163.com
 */
class EvenOdd {
    /**
     * 这里true和false代表两层含义，evenOK false意思是偶数没准备好，奇数好了，evenOk true意思是偶数准备好了，奇数可以休息了.
     */
    private boolean evenOK = true;

    private AtomicInteger ai = new AtomicInteger(-1);

    public synchronized void oddPlused() {
        ai.getAndIncrement();
        evenOK = true; // ready to evenplus.
        System.out.println(ai.get());
        notifyAll();
    }

    public synchronized void evenPlused() {
        ai.getAndAdd(1);
        evenOK = false; //ready to oddPlus.
        if(ai.intValue() != 0) {
            System.out.println(ai.get());
        }
        if(ai.intValue() == 100) {
            System.exit(0);
        }
        notify();
    }

    public synchronized void waitForOddPlusing() throws InterruptedException {
        while(evenOK == false) {
            wait();
        }
    }

    public synchronized void waitForEvenPlusing() throws InterruptedException {
        while(evenOK == true) {
            wait();
        }
    }
}

class EvenPlus implements Runnable {
    private EvenOdd evenOdd;

    public EvenPlus(EvenOdd evenOdd) {
        this.evenOdd = evenOdd;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
               evenOdd.evenPlused();
               evenOdd.waitForOddPlusing();
            } catch (InterruptedException e) {
                System.out.println("exiting even plus via interrupt");
            }
        }
        System.out.println("end even plus task");
    }
}

class OddPlus implements Runnable {
    private EvenOdd evenOdd;

    public OddPlus(EvenOdd evenOdd) {
        this.evenOdd = evenOdd;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                evenOdd.waitForEvenPlusing();
                evenOdd.oddPlused();
            } catch (InterruptedException e) {
                System.out.println("exiting odd plus via interrupt");
            }
        }
        System.out.println("end odd plus task");
    }
}

public class WaitNotify {

    public static void main(String[] args) {
        EvenOdd evenOdd = new EvenOdd();
        EvenPlus evenPlus = new EvenPlus(evenOdd);
        OddPlus oddPlus = new OddPlus(evenOdd);
        Thread evenThread = new Thread(evenPlus);
        Thread oddThread = new Thread(oddPlus);
        oddThread.start();
        evenThread.start();
    }
}




