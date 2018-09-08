/*File: PassengerShip.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates a PassengerShip object that expands on the basic Ship structure to
 *describe a passenger ship.
 */
package seaportprogram;

import java.util.Scanner;

public class PassengerShip extends Ship {
    
    private int numberOfOccupiedRooms, numberOfPassengers, numberOfRooms;
    
    public PassengerShip(Scanner scanner){
        super(scanner);
        numberOfPassengers = scanner.nextInt();
        numberOfRooms = scanner.nextInt();
        numberOfOccupiedRooms = scanner.nextInt();
    }//end constructor
    
    /**
     * Calls on the super method to create a String representation of this object
     * @return returns a String representation of this PassengerShip
     */
    @Override
    public String toString(){
        String description = "Passenger Ship: " + super.toString();
        return description;
    }
}//end PassengerShip class
