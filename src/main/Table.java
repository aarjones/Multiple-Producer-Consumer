package main;

/**
 * A Table object, which serves as a buffer between the Waiter and Customer objects.  Holds a single course.
 *
 * @author Aaron R. Jones
 */
public class Table {
    private String course;  //The name of the course
    private boolean isEmpty;  //flag used to see if the table is empty

    /**
     * Default constructor.  Creates an empty Table with no current course.
     */
    public Table() {
        this.course = "";
        this.isEmpty = true;
    }

    /**
     * Allows a Waiter to serve a course to the Table, if the Table is already empty.
     *
     * @param course The course to add to the Table.
     */
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

    /**
     * Allows a Customer to eat a course from the Table, if the Table is not empty.
     *
     * @return The course eaten from the Table.
     */
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
