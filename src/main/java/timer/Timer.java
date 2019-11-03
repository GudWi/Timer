package timer;

import java.util.*;

public class Timer implements Runnable {
    private Map<TaskTimer, Integer> runnableOperations =
            new TreeMap<>(Comparator.comparingLong((TaskTimer task) -> task.getExecutionTime().getTime()));

    private TaskExecutor executor;

    private int counter = 0;

    public Timer() {
        executor = new TaskExecutor();
        executor.setRunnableOperations(runnableOperations);
    }

    public void addOperation(Runnable operation, Date executionTime) {
        counter++;
        executor.addOperation(operation, executionTime, counter);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            executor.executeOperations();

            if(runnableOperations.size() == 0){
                System.out.println("size = 0");
            }
        }
    }
}
