package com.adv.os.project2.algorithms;

import com.adv.os.project2.Algorithm;
import com.adv.os.project2.CPU;
import com.adv.os.project2.Task;

import java.util.List;
import java.util.Scanner;

public class RR implements Algorithm {

    private List<Task> taskList;
    private Integer timeQuantam;
    private Integer allocatedCPUTimeForTask;

    public RR(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void schedule() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the time quantum in milliseconds: ");
        timeQuantam = scanner.nextInt();
        while (!taskList.isEmpty()) {
            Task currentTask = pickNextTask();
            CPU.run(currentTask, allocatedCPUTimeForTask);
        }
    }

    @Override
    public Task pickNextTask() {
        Task currentTask = null;
        for (Task task : taskList) {
            if (timeQuantam >= task.getBurstTime()) {
                allocatedCPUTimeForTask = task.getBurstTime();
                task.setBurstTime(0);
            } else {
                allocatedCPUTimeForTask = timeQuantam;
                task.setBurstTime(task.getBurstTime() - timeQuantam);
            }
            currentTask = task;
            break;
        }
        taskList.remove(currentTask);
        if (currentTask.getBurstTime() > 0) {
            taskList.add(currentTask);
        }
        return currentTask;
    }
}
