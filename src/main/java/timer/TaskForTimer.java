package timer;

import java.util.Date;

public class TaskForTimer implements Comparable{
    private int orderNum;
    private Runnable task;
    private Date executionTime;

    TaskForTimer(Runnable task, Date executionTime, int orderNum){
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
        TaskForTimer taskTimer = (TaskForTimer) o;
        return (executionTime.equals(taskTimer.executionTime) && task.equals(taskTimer.task) && orderNum == taskTimer.orderNum);
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
        if (o == null || getClass() != o.getClass()) return -1;
        TaskForTimer taskTimer = (TaskForTimer) o;
        if(getExecutionTime().getTime() < taskTimer.getExecutionTime().getTime()) return -1;
        else if(getExecutionTime().getTime() == taskTimer.getExecutionTime().getTime()){
            if(getOrderNum() == taskTimer.getOrderNum()) return 0;
            else return -1;
        }
        else return 1;
    }
}
