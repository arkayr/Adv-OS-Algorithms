package com.adv.os.project2;

/**
 * "Virtual" com.adv.os.project2.CPU
 * <p>
 * This virtual com.adv.os.project2.CPU also maintains system time.
 *
 * @author Praveen Thogaru
 */

public class CPU {
    /**
     * Run the specified task for the specified slice of time.
     */
    public static void run(Task task, int slice) {
        System.out.println("Process " + task.getName() + " will run for "+ slice + " ms");
    }
}