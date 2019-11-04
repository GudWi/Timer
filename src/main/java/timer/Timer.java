package timer;

import java.util.*;

public class Timer implements Runnable {
    private Map<TaskForTimer, Integer> runnableOperations = new TreeMap<>();
    private TaskExecutor executor;

    private int counter = 0;
    private boolean stopped = false;

    public Timer() {
        executor = new TaskExecutor(runnableOperations);
    }

    public void addOperation(Runnable operation, Date executionTime) {
        counter++;
        executor.addOperation(operation, executionTime, counter);
    }

    public void addCollectionOfOperations(HashMap<Runnable, Date> operations){
        for(Map.Entry<Runnable, Date> operation : operations.entrySet()){
            counter++;
            executor.addOperation(operation.getKey(), operation.getValue(), counter);
        }
    }

    @Override
    public void run() {
        while (!stopped) {
            executor.executeOperations();

            if(runnableOperations.size() == 0){
                System.out.println("size of runnable operations = 0");
            }
        }

        System.out.println("Thread is closed");
    }

    public void close(){
        System.out.println("close");
        stopped = true;
    }
}
