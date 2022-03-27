package com.adv.os.project2.algorithms;

import com.adv.os.project2.Algorithm;
import com.adv.os.project2.CPU;
import com.adv.os.project2.Task;

import java.util.ArrayList;
import java.util.List;

public class FCFS implements Algorithm {

    private List<Task> taskList;

    public FCFS(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void schedule() {
        List<Integer> burstTimeList = new ArrayList<>();
        while (!taskList.isEmpty()) {
            Task nextTask = pickNextTask();
            CPU.run(nextTask, nextTask.getBurstTime());
            burstTimeList.add(nextTask.getBurstTime()); //adding burst time to list in FCFS order.
        }
        //printAverageWaitingAndTurnAroundTime(burstTimeList); //prints average waiting time and average turn around time.
    }

    @Override
    public Task pickNextTask() {
        Task nextTask = null;
        int earlyArrivalTime = Integer.MAX_VALUE;
        for (Task task : taskList) {
            if (task.getOrder() < earlyArrivalTime) {
                earlyArrivalTime = task.getOrder();
                nextTask = task;
            }
        }
        taskList.remove(nextTask);
        return nextTask;
    }

    private void printAverageWaitingAndTurnAroundTime(List<Integer> burstTimeList) {
        int size = burstTimeList.size();
        List<Integer> waitingTimeList = getWaitingTimeList(burstTimeList);
        List<Integer> turnAroundTimeList = getTurnAroundTimeList(burstTimeList, waitingTimeList);
        double totalWaitingTime = 0;
        double totalTurnAroundTime = 0;
        for (int i = 0; i < size; i++) {
            totalWaitingTime = totalWaitingTime + waitingTimeList.get(i);
            totalTurnAroundTime = totalTurnAroundTime + turnAroundTimeList.get(i);
        }
        System.out.println("\nThe average waiting time is = " + totalWaitingTime/size);
        System.out.println("The average turn around time is = " + totalTurnAroundTime/size);
    }

    private List<Integer> getWaitingTimeList(List<Integer> burstTimeList) {
        List<Integer> waitingTimeList = new ArrayList<>();
        int currentProcessWaitingTime = 0;
        waitingTimeList.add(currentProcessWaitingTime); // for first process waiting time is zero.
        for (int i = 1; i < burstTimeList.size(); i++) {
            currentProcessWaitingTime = waitingTimeList.get(i - 1) + burstTimeList.get(i - 1);
            waitingTimeList.add(currentProcessWaitingTime);
        }
        return waitingTimeList;
    }

    private List<Integer> getTurnAroundTimeList(List<Integer> burstTimeList, List<Integer> waitingTimeList) {
        List<Integer> turnAroundTimeList = new ArrayList<>();
        int currentProcessTurnAroundTime;
        for (int i = 0; i < burstTimeList.size(); i++) {
            currentProcessTurnAroundTime = waitingTimeList.get(i) + burstTimeList.get(i);
            turnAroundTimeList.add(currentProcessTurnAroundTime);
        }
        return turnAroundTimeList;
    }
}
