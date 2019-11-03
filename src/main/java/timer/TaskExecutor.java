package timer;

import java.util.*;

class TaskExecutor {
    private Map<TaskTimer, Integer> runnableOperations;

    void setRunnableOperations(Map<TaskTimer, Integer> runnableOperations) {
        this.runnableOperations = runnableOperations;
    }

    synchronized void addOperation(Runnable operation, Date executionTime, int counter){
        TaskTimer taskTimer = new TaskTimer(operation, executionTime, counter);
        System.out.println(taskTimer.hashCode());

        if(runnableOperations.containsKey(taskTimer)){
            runnableOperations.put(taskTimer, runnableOperations.get(taskTimer) + 1);
        } else {
            //System.out.println("put operation");
            runnableOperations.put(taskTimer, 1);
        }

        notifyAll();
    }

    synchronized void executeOperations() {
            while (runnableOperations.size() < 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            List<TaskTimer> trash = new ArrayList<>();
            long timeRange = 0;

        while(runnableOperations.size() > 0) {
            Iterator<Map.Entry<TaskTimer, Integer>> itr = runnableOperations.entrySet().iterator();

            try {
                while (itr.hasNext()) {

                    Map.Entry<TaskTimer, Integer> entry = itr.next();

                    timeRange = entry.getKey().getExecutionTime().getTime() - new Date().getTime();

                    if (timeRange <= 0) {
                        for (int i = 0; i < entry.getValue(); i++) {
                            Thread thread = new Thread(entry.getKey().getTask());
                            thread.start();
                            thread.join();
                        }

                        trash.add(entry.getKey());
                    } else {
                        break;
                    }
                }

                for (TaskTimer taskTimer : trash) {
                    runnableOperations.remove(taskTimer);
                }

                System.out.println(timeRange);

                if(timeRange > 0) {
                    wait(timeRange);
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        notify();
    }
}
