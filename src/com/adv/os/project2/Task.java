package com.adv.os.project2;

import java.util.concurrent.atomic.AtomicInteger;

public class Task {

    private int tid;
    private String name;
    private Integer order;
    private Integer burstTime;
    private Long arrivalTime;
    private Long priority;

    private static AtomicInteger tidAllocator = new AtomicInteger();

    public Task() {
    }

    //constructor for FCFS
    public Task(String name, Integer order, Integer burstTime) {
        this.name = name;
        this.order = order;
        this.burstTime = burstTime;
    }

    //constructor for SJF
    public Task(String name, Long arrivalTime, Integer burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    //constructor for RR
    public Task(String name, Integer burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }

    //constructor for Priority
    public Task(String name, Integer burstTime, Long priority) {
        this.name = name;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(Integer burstTime) {
        this.burstTime = burstTime;
    }

    public static AtomicInteger getTidAllocator() {
        return tidAllocator;
    }

    public static void setTidAllocator(AtomicInteger tidAllocator) {
        Task.tidAllocator = tidAllocator;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }
}