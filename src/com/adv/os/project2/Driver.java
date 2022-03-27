package com.adv.os.project2; /**
 * com.adv.os.project2.Driver.java
 * <p>
 * Demonstrates different scheduling algorithms.
 * <p>
 * Usage:
 * <p>
 * java com.adv.os.project2.Driver <schedule> <algorithm>
 * <p>
 * where
 * schedule is schedule of tasks
 * <p>
 * algorithm = [FCFS, SJF, PRI, RR, PRI-RR]
 */

import com.adv.os.project2.algorithms.*;

import java.util.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) throws IOException {
        /*if (args.length != 2) {
            System.err.println("Please provide two arguments.");
            System.exit(0);
        }*/
        while (true) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Choose Algorithm:");
            String choice = sc.nextLine();
            choice = choice.toUpperCase();
            System.out.println("Enter input file name:");
            String fileName = sc.nextLine();

            // TODO - change the way of file reading.

            BufferedReader inFile = new BufferedReader(new FileReader("C:\\Users\\REDDY\\Desktop\\Adv_OS_Project_2\\src\\com\\adv\\os\\project2\\files\\" + fileName));
            inFile.readLine();  // ignoring the first line in input because of headers.
            String schedule;
            Algorithm scheduler = null;
            List<Task> taskList = new ArrayList<>();
            switch (choice) {
                case "FCFS":
                    while ((schedule = inFile.readLine()) != null) {
                        String[] params = schedule.split(",\\s*");
                        taskList.add(new Task(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                    }
                    scheduler = new FCFS(taskList);
                    break;
                case "SJF":
                    while ((schedule = inFile.readLine()) != null) {
                        String[] params = schedule.split(",\\s*");
                        taskList.add(new Task(params[0], Long.parseLong(params[1]), Integer.parseInt(params[2])));
                    }
                    scheduler = new SJF(taskList);
                    break;
                case "PRI":
                    while ((schedule = inFile.readLine()) != null) {
                        String[] params = schedule.split(",\\s*");
                        taskList.add(new Task(params[0], Integer.parseInt(params[1]), Long.parseLong(params[2])));
                    }
                    scheduler = new Priority(taskList);
                    break;
                case "RR":
                    while ((schedule = inFile.readLine()) != null) {
                        String[] params = schedule.split(",\\s*");
                        taskList.add(new Task(params[0], Integer.parseInt(params[1])));
                    }
                    scheduler = new RR(taskList);
                    break;
                case "PRI-RR":
                    while ((schedule = inFile.readLine()) != null) {
                        String[] params = schedule.split(",\\s*");
                        taskList.add(new Task(params[0], Integer.parseInt(params[1]), Long.parseLong(params[2])));
                    }
                    scheduler = new PriorityRR(taskList);
                    break;
                default:
                    System.err.println("Invalid algorithm");
                    System.exit(0);
            }
            inFile.close();

            System.out.println("\n-----------------------------------------------");
            // start the scheduler
            scheduler.schedule();
            System.out.println("-----------------------------------------------\n");
        }
    }
}
