package main;

import java.util.Random;

/**
 * A Customer object, which eats courses from a Table.
 *
 * @author Aaron R. Jones
 */
public class Customer implements Runnable {
    private final static int MAX_CUSTOMER_MILLIS = 4000; //must wait for 0 to 4 seconds
    private final static int N_COURSES = 3; //number of courses

    private Table table; //where the customer is sitting
    private String customerName; //name of this customer

    /**
     * A Customer object, which is associated with a single Table.  The Customer eats a course from the Table.
     *
     * @param table The Table associated with this Customer.
     * @param customerName The name of this Customer.
     */
    public Customer(Table table, String customerName) {
        this.table = table;
        this.customerName = customerName;
    }

    /**
     * Runs a Customer thread, which eats a course from a Table whenever the Table is not empty.  Will only eat N_COURSES courses before exiting.
     */
    public void run() {
        Random rng = new Random();
        int count = 0;
        String currentCourse;

        while(count < N_COURSES) {
            currentCourse = table.eat();
            System.out.println(customerName + " is eating " + currentCourse);

            try {
                Thread.sleep(rng.nextInt(MAX_CUSTOMER_MILLIS));
            } catch(InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
            count++;
        }
    }
}
