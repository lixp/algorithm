package org.framework.fftt;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lixp
 * Date: 2019/8/14
 * Time: 13:41
 * Mail: lixp0915@163.com
 */
class Car {
    private boolean waxOn = false;
    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }
    public synchronized void buffered() {
        waxOn = false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException {
        while(waxOn == false) {
            wait();
        }
    }
    public synchronized void waitForBuffering() throws InterruptedException {
        while(waxOn == true) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;
    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            System.out.println("Wax on!");
            try {
                //TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffering();
            } catch (InterruptedException e) {
                System.out.println("exiting wax on task end via interrupt");
            }
        }
        System.out.println("end wax on task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                car.waitForWaxing();
                //TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("Wax off!");
                car.buffered();
            } catch (InterruptedException e) {
                System.out.println("exit wax off via interrupt");
            }
        }
        System.out.println("ending wax off task.");
    }
}

public class WaxOMatic {
    public  static void main(String [] args) {
        Car car = new Car();
        WaxOn waxOn = new WaxOn(car);
        WaxOff waxOff = new WaxOff(car);
        Thread waxOnThread = new Thread(waxOn);
        Thread waxOffThread = new Thread(waxOff);
        waxOnThread.start();
        waxOffThread.start();
    }
}
