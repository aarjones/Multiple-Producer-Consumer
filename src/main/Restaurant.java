package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;

public class Restaurant {
    private static final int N_COURSES = 3;

    public static void main(String[] args) {
        //Create arrays for customer, waiter objects; get input
        String fileName;
        int numWaiters;
        Waiter[] waiters = null;
        ArrayList<Customer> customers = new ArrayList<Customer>();

        System.out.print("Enter the name of the file to test: ");

        try {
            //create scanner around file
            Scanner stdIn = new Scanner(System.in);
            fileName = stdIn.next(); //get filename, input

            if(! fileName.substring(fileName.length() - 4).equalsIgnoreCase(".txt")) //correct for extension
                fileName += ".txt";

            Scanner fileScan = new Scanner(new File(fileName)); //open file with scanner

            //initialize waiters array
            if(fileScan.hasNextInt())
                numWaiters = Integer.parseInt(fileScan.nextLine());
            else
                throw new IllegalArgumentException("No number of waiters given");

            waiters = new Waiter[numWaiters];

            for(int i = 0; i < numWaiters; i++) { //loop through each waiter
                String currentLine;
                String waiterName;
                int numCustomers;

                if(fileScan.hasNextLine())
                    currentLine = fileScan.nextLine();
                else
                    throw new IllegalArgumentException("Incorrect number of lines in the file: not enough waiters");

                Scanner lineScan = new Scanner(currentLine); //with a new scanner on the current line of the file

                if(lineScan.hasNext())
                    waiterName = lineScan.next(); //get the name of the waiter
                else
                    throw new IllegalArgumentException("Bad line: not enough waiters");

                if(lineScan.hasNextInt())
                    numCustomers = Integer.parseInt(lineScan.next()); //and the number of customers for that waiter
                else
                    throw new IllegalArgumentException("Bad line: No Customer Number for waiter " + waiterName);

                Table[] tables = new Table[numCustomers]; //create a set of tables for the current waiter
                String[] customerNames = new String[numCustomers]; //and the names of the customers for the current waiter
                String[][] courses = new String[numCustomers][N_COURSES]; //and the customer/course groups

                 for(int j = 0; j < numCustomers; j++) {
                     tables[j] = new Table(); //fill each waiter's table array with tables

                     if(lineScan.hasNext())
                        customerNames[j] = lineScan.next(); //and customers
                     else
                         throw new IllegalArgumentException("Bad line: not enough customers for waiter " + waiterName);

                     customers.add(new Customer(tables[j], customerNames[j])); //add name and table to a customer collection
                     for(int k = 0; k < N_COURSES; k++) {
                         if(lineScan.hasNext())
                            courses[j][k] = lineScan.next(); //import the courses for each customer
                         else
                             throw new IllegalArgumentException("Bad line: not enough courses for customer " + customerNames[j]);
                    }
                }
                waiters[i] = new Waiter(tables, waiterName, customerNames, courses); //create the waiter object
                lineScan.close();
            }

            if(fileScan.hasNextLine())
                throw new IllegalArgumentException("Incorrect number of lines in the file: too many waiters");

            stdIn.close();
            fileScan.close();

        } catch(FileNotFoundException fnfe) {
            System.err.println("File does not exist: " + fnfe.getMessage());
            System.err.println("Exiting...");
            System.exit(1);
        } catch(IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("Exiting...");
            System.exit(2);
        }

        //Combine threads into single array
        Thread[] allThreads = new Thread[waiters.length + customers.size()];
        for(int i = 0; i < allThreads.length; i++) {
            if(i < waiters.length) {
                allThreads[i] = new Thread(waiters[i]);
            }
            else {
                allThreads[i] = new Thread(customers.get(i - waiters.length));
            }
        }

        //Start threads
        for(Thread thread : allThreads)
            thread.start();
    }

}
