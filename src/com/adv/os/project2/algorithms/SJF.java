package com.adv.os.project2.algorithms;

import com.adv.os.project2.Algorithm;
import com.adv.os.project2.CPU;
import com.adv.os.project2.Task;

import java.util.List;

public class SJF implements Algorithm {

    private List<Task> taskList;
    private int timePeriod = 0;

    public SJF(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void schedule() {
        Task previousTask = null;
        int allocatedCPUTimeForTask = 0;
        while (!taskList.isEmpty()) {
            Task currentTask = pickNextTask();
            if (previousTask != null && previousTask != currentTask) {
                CPU.run(previousTask, allocatedCPUTimeForTask);
                allocatedCPUTimeForTask = 0;
            }
            allocatedCPUTimeForTask++;
            previousTask = currentTask;
        }
        CPU.run(previousTask, allocatedCPUTimeForTask);
    }

    @Override
    public Task pickNextTask() {
        Task nextTask = null;
        int shortestBurstTime = Integer.MAX_VALUE;
        for (Task task : taskList) {
            if (task.getArrivalTime() <= timePeriod && task.getBurstTime() < shortestBurstTime) {
                shortestBurstTime = task.getBurstTime();
                nextTask = task;
            }
        }
        nextTask.setBurstTime(nextTask.getBurstTime() - 1);
        if (nextTask.getBurstTime() == 0) {
            taskList.remove(nextTask);
        }
        timePeriod++;
        return nextTask;
    }
}
