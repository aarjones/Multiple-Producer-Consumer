package main;

import java.util.Random;

public class Waiter implements Runnable {
    private final static int MAX_WAITER_MILLIS = 4000; //must wait between 0 and 4 seconds
    private final static int N_COURSES = 3; //number of courses is exactly 3

    private Table[] tables; //table objects the waiter operates on
    private String waiterName; //name of this waiter
    private String[] customerNames; //names of the customers served by waiter
    private String[][] courses; //Multi-dimensional array of courses for each customer of this waiter: course[i][j] jth course for ith customer

    public Waiter(Table[] tables, String waiterName, String[] customerNames, String[][] courses) {
        this.tables = tables;
        this.waiterName = waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }

    public void run() {
        Random rng = new Random();

        for(int j = 0; j < courses[0].length; j++) {
            for(int i = 0; i < customerNames.length; i++) {
                System.out.println(waiterName + " serves " + customerNames[i] + " " + courses[i][j]);
                tables[i].serve(courses[i][j]);
                try {
                    Thread.sleep( rng.nextInt(MAX_WAITER_MILLIS));
                } catch(InterruptedException ie) {
                    System.err.println(ie.getMessage());
                }
            }
        }
    }
}
