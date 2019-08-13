package org.framework.fftt;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: lixp
 * Date: 2019/8/13
 * Time: 10:32
 * Mail: lixp0915@163.com
 */
public class WaitNotify {

    private static List<Integer> list = new ArrayList<>();

    private static class EvenOdd implements Runnable {
        private int start;
        private Object evenObject;
        private Object oddObject;

        EvenOdd(int start, Object evenObject, Object oddObject){
            this.start = start;
            this.evenObject = evenObject;
            this.oddObject = oddObject;
        }

        @Override
        public void run() {
            for(int i = start; i < 20; i+= 2) {

                synchronized (oddObject) {
                    if(i % 2 != 0) {
                        try {
                            oddObject.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            //oddObject.notify();
                        }
                    }
                }
                System.out.println("thead id" + Thread.currentThread().getId() + "-----" + i);
                synchronized (evenObject) {
                    try {
                            oddObject.notify();
                        } finally {
                            //oddObject.notify();
                        }
                }
            }
        }
    }

    public static void main(String[] args) {
        Object evenMonitor = new Object();
        Object oddMonitor = new Object();
        EvenOdd even = new EvenOdd(0, evenMonitor,oddMonitor);
        EvenOdd odd = new EvenOdd(1, evenMonitor, oddMonitor);
        Thread evenThread = new Thread(even);
        evenThread.setPriority(10);
        Thread oddThread = new Thread(odd);
        oddThread.setPriority(8);
        evenThread.start();
        oddThread.start();
    }
}
