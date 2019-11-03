package main;

import timer.Timer;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Date currentTime = new Date();

        Runnable task = () -> System.out.println("task");
        Runnable task0 = () -> System.out.println("task0");
        Runnable task1 = () -> System.out.println("task1");
        Runnable task2 = () -> System.out.println("task2");
        Runnable task3 = () -> System.out.println("task3");
        Runnable task4 = () -> System.out.println("task4");
        Runnable task5 = () -> System.out.println("task5");
        Runnable task6 = () -> System.out.println("task6");

        timer.addOperation(task, currentTime);
        timer.addOperation(task0, currentTime);
        timer.addOperation(task2, new Date(currentTime.getTime() + 20000));
        timer.addOperation(task1, new Date(currentTime.getTime() + 10000));
        timer.addOperation(task4, new Date(currentTime.getTime() + 40000));
        timer.addOperation(task3, new Date(currentTime.getTime() + 30000));

        Thread taskEx = new Thread(timer);
        taskEx.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        timer.addOperation(task5, new Date(currentTime.getTime() + 10000));

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        timer.addOperation(task6, new Date(currentTime.getTime() + 60000));
    }
}
