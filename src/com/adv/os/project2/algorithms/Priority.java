package com.adv.os.project2.algorithms;

import com.adv.os.project2.Algorithm;
import com.adv.os.project2.CPU;
import com.adv.os.project2.Task;

import java.util.List;

public class Priority implements Algorithm {

    private List<Task> taskList;

    public Priority(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void schedule() {
        while (!taskList.isEmpty()) {
            Task nextTask = pickNextTask();
            CPU.run(nextTask, nextTask.getBurstTime());
        }
    }

    @Override
    public Task pickNextTask() {
        Task nextTask = null;
        long higherPriority = Integer.MAX_VALUE;
        for (Task task : taskList) {  //low numbers have high priority
            if(task.getPriority() < higherPriority) {
                higherPriority = task.getPriority();
                nextTask = task;
            }
        }
        taskList.remove(nextTask);
        return nextTask;
    }
}
