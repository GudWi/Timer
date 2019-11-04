package timer;

import java.util.*;

class TaskExecutor {
    private Map<TaskForTimer, Integer> runnableOperations;

    TaskExecutor(Map<TaskForTimer, Integer> runnableOperations){
        this.runnableOperations = runnableOperations;
    }

    synchronized void addOperation(Runnable operation, Date executionTime, int counter){
        TaskForTimer taskTimer = new TaskForTimer(operation, executionTime, counter);

        if(runnableOperations.containsKey(taskTimer)){
            runnableOperations.put(taskTimer, runnableOperations.get(taskTimer) + 1);
        } else {
            System.out.println("put operation");
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
            List<TaskForTimer> trash = new ArrayList<>();
            long timeRange = 0;

        while(runnableOperations.size() > 0) {
            Iterator<Map.Entry<TaskForTimer, Integer>> itr = runnableOperations.entrySet().iterator();

            try {
                while (itr.hasNext()) {
                    Map.Entry<TaskForTimer, Integer> entry = itr.next();

                    timeRange = entry.getKey().getExecutionTime().getTime() - new Date().getTime();

                    if (timeRange <= 0) {
                        for (int i = 0; i < entry.getValue(); i++) {
                            if(entry.getKey().getTask() != null) {
                                entry.getKey().getTask().run();
                            }
                        }

                        trash.add(entry.getKey());
                    } else {
                        break;
                    }
                }

                for (TaskForTimer taskTimer : trash) {
                    runnableOperations.remove(taskTimer);
                }

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
