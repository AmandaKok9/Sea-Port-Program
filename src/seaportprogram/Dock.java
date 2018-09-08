/*File: Dock.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates a Dock object that contains necessary information and methods
 *to describe a basic dock.
 */

package seaportprogram;

import java.util.Scanner;

public class Dock extends Thing {
    
    private Ship ship;
    
    public Dock(Scanner scanner){
        super(scanner);
    }//end constructor
    
    /**
     * Calls on super method to create a formatted String representation of this 
     * object including the additional field ship
     * @return returns a String representation of this object 
     */
    @Override
    public String toString(){
        String description = "\nDock: " + super.toString() 
                + "\n   Ship: " + ship;
        return description;
    }//end toString
    
    /**
     * Initializes the field ship with the passed in argument
     * @param ship Ship object that will be connected to ship field
     */
    public void addShip(Ship ship){
        this.ship = ship;
    }
}//end Dock class
