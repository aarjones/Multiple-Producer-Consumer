package main;

import java.util.Random;

/**
 * A Waiter object, which serves courses to multiple Tables.
 *
 * @author Aaron R. Jones
 */
public class Waiter implements Runnable {
    private final static int MAX_WAITER_MILLIS = 4000; //must wait between 0 and 4 seconds
    private final static int N_COURSES = 3; //number of courses is exactly 3

    private Table[] tables; //table objects the waiter operates on
    private String waiterName; //name of this waiter
    private String[] customerNames; //names of the customers served by waiter
    private String[][] courses; //Multi-dimensional array of courses for each customer of this waiter: course[i][j] jth course for ith customer

    /**
     * A Waiter object, which serves multiple Tables.  Each Table has a single Customer.  The Waiter serves a single course at a time, in order, to each Customer's Table.
     *
     * @param tables An array of the Tables served by this Waiter.
     * @param waiterName The name of this Waiter.
     * @param customerNames An array of the Customer names served by this Waiter.
     * @param courses A 2-D array denoting the courses to be served to each Customer.  courses[i][j] denotes the jth course for the ith Customer.
     */
    public Waiter(Table[] tables, String waiterName, String[] customerNames, String[][] courses) {
        this.tables = tables;
        this.waiterName = waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }

    /**
     * Runs a Waiter thread, which serves each Customer, in order, only one course at a time.  Waits for between 0 and 4 seconds after serving.
     */
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
