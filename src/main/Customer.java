package main;

import java.util.Random;

public class Customer implements Runnable {
    private final static int MAX_CUSTOMER_MILLIS = 4000; //must wait for 0 to 4 seconds
    private final static int N_COURSES = 3; //number of courses

    private Table table; //where the customer is sitting
    private String customerName; //name of this customer

    public Customer(Table table, String customerName) {
        this.table = table;
        this.customerName = customerName;
    }

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
