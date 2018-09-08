/*File: Ship.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates a Ship object that contains necessary information and methods
 *to describe a basic ship.
 */
package seaportprogram;

import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends Thing {

    private PortTime arrivalTime, dockTime;                  //not used in this project, will be used in future versions
    private double draft, length, weight, width;
    private ArrayList<Job> jobs;

    public Ship(Scanner scanner) {
        super(scanner);
        weight = scanner.nextDouble();
        length = scanner.nextDouble();
        width = scanner.nextDouble();
        draft = scanner.nextDouble();
    }//end constructor
    
    /**
     * Calls on the super method to create a String representation of this object
     * @return returns String representation of the Ship
     */
    @Override
    public String toString(){
        String description = super.toString();
        return description;
    }
    
    /**
     * Adds job to Job ArrayList
     * @param job Job that will be added to Job
     */
    public void addJob(Job job){
        jobs.add(job);
    }
}//end Ship class
