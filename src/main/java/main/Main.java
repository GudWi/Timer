package main;

import timer.Timer;

import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Date currentTime = new Date();

        Runnable task = () -> {System.out.println("task");

        for(int i = 0; i < 10; i++){
            System.out.println(1);}
        };

        Runnable task0 = () -> {
            System.out.println("task0");
            for(int i = 0; i < 10; i++){
                System.out.println(2);
        }};

        Runnable task1 = () -> System.out.println("task1");
        Runnable task2 = () -> System.out.println("task2");
        Runnable task3 = () -> System.out.println("task3");
        Runnable task4 = () -> System.out.println("task4");
        Runnable task5 = () -> System.out.println("task5");
        Runnable task6 = () -> System.out.println("task6");

        HashMap<Runnable, Date> operations = new HashMap<>();

        operations.put(task, currentTime);
        operations.put(task0, currentTime);
        operations.put(task2, new Date(currentTime.getTime() + 20000));
        operations.put(task1, new Date(currentTime.getTime() + 10000));
        operations.put(task4, new Date(currentTime.getTime() + 40000));
        operations.put(task3, new Date(currentTime.getTime() + 30000));

        timer.addCollectionOfOperations(operations);

        Thread taskEx = new Thread(timer);
        taskEx.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        timer.addOperation(task5, new Date(new Date().getTime() + 10000));

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        timer.addOperation(task6, new Date(new Date().getTime() + 20000));
        timer.close();
    }
}
