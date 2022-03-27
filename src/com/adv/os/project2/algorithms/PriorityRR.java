package com.adv.os.project2.algorithms;

import com.adv.os.project2.Algorithm;
import com.adv.os.project2.CPU;
import com.adv.os.project2.Task;

import java.util.*;
import java.util.stream.Collectors;

public class PriorityRR implements Algorithm {

    List<Task> taskList = null;
    private Integer timeQuantam;
    private Map<Long, List<Task>> priorityTaskMap;
    private Integer allocatedCPUTimeForTask;

    public PriorityRR(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void schedule() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the time quantum in milliseconds: ");
        timeQuantam = scanner.nextInt();
        Map<Long, List<Task>> priorityTaskMap = getPriorityTaskMap(taskList);
        while (!priorityTaskMap.isEmpty()) {
            Task currentTask = pickNextTask();
            CPU.run(currentTask, allocatedCPUTimeForTask);
        }
    }

    @Override
    public Task pickNextTask() {
        Task tempTask = null;
        Iterator<Map.Entry<Long, List<Task>>> iterator = priorityTaskMap.entrySet().iterator();
        while (iterator.hasNext()) {
            List<Task> tempTaskList = iterator.next().getValue();
            if (tempTaskList.size() == 1) {
                allocatedCPUTimeForTask = tempTaskList.get(0).getBurstTime();
                iterator.remove();
                return tempTaskList.get(0);
            }

            tempTask = tempTaskList.get(0);
            if (timeQuantam >= tempTask.getBurstTime()) {
                allocatedCPUTimeForTask = tempTask.getBurstTime();
                tempTask.setBurstTime(0);
            } else {
                allocatedCPUTimeForTask = timeQuantam;
                tempTask.setBurstTime(tempTask.getBurstTime() - timeQuantam);
            }

            tempTaskList.remove(tempTask);
            if (tempTask.getBurstTime() > 0) {
                tempTaskList.add(tempTask);
            }
            break;
        }
        return tempTask;
    }

    private Map<Long, List<Task>> getPriorityTaskMap(List<Task> taskList) {
        priorityTaskMap = new LinkedHashMap<>();
        for (Task task : taskList) {
            if (priorityTaskMap.get(task.getPriority()) == null) {
                priorityTaskMap.put(task.getPriority(), new ArrayList<>());
            }
            priorityTaskMap.get(task.getPriority()).add(task);
        }
        priorityTaskMap = priorityTaskMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return priorityTaskMap;
    }
}
