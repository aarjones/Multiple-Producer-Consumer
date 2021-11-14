package main;

public class Table {
    private String course;  //The name of the course
    private boolean isEmpty;  //flag used to see if the table is empty
    private Object obj; //Synchronization object, optional

    public Table() {
        this.course = "";
        this.isEmpty = true;
        obj = new Object();
    }

    public synchronized void serve(String course) {
        while(!isEmpty) {
            try {
                wait();
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
        }
        this.course = course;
        this.isEmpty = false;

        notifyAll();
    }

    public synchronized String eat() {
        String toReturn;
        while(isEmpty) {
            try {
                wait();
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
        }
        isEmpty = true;

        notifyAll();

        return this.course;
    }
}
