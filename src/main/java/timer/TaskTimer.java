package timer;

import java.util.Date;

public class TaskTimer implements Comparable{
    private int orderNum;
    private Runnable task;
    private Date executionTime;

    TaskTimer(Runnable task, Date executionTime, int orderNum){
        this.task = task;
        this.executionTime = executionTime;
        this.orderNum = orderNum;
    }

    Date getExecutionTime() {
        return executionTime;
    }

    Runnable getTask() {
        return task;
    }

    private int getOrderNum() {
        return orderNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTimer taskTimer = (TaskTimer) o;
        return (executionTime.equals(taskTimer.executionTime) & task.equals(taskTimer.task) & orderNum == taskTimer.orderNum);
    }

    @Override
    public int hashCode() {
        int result = getOrderNum();

        result = (int) (31 + 29 * result + ((getExecutionTime() == null) ? 0 : getExecutionTime().getTime()) +
                        ((getTask() == null) ? 0 : getTask().hashCode()));

        return result;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return -1;
        TaskTimer taskTimer = (TaskTimer) o;
        return (executionTime.equals(taskTimer.executionTime) & task.equals(taskTimer.task) & orderNum == taskTimer.orderNum) ? 1 : -1;
    }
}
