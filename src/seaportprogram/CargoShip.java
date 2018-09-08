/*File: CargoShip.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates a CargoShip object that expands on the basic Ship structure to
 *describe a cargo ship.
 */
package seaportprogram;

import java.util.Scanner;

public class CargoShip extends Ship {
    
    private double cargoValue, cargoVolume, cargoWeight;
    
    public CargoShip(Scanner scanner){
        super(scanner);
        cargoWeight = scanner.nextDouble();
        cargoVolume = scanner.nextDouble();
        cargoValue = scanner.nextDouble();
    }//end constructor
    
    /**
     * Calls on the super method to create a String representation of this object
     * @return returns a String representation of this CargoShip
     */
    @Override
    public String toString(){
        String description = "Cargo Ship: " + super.toString();
        return description;
    }
}//end CargoShip class
