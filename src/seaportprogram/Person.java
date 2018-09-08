/*File: Person.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates a Person object that contains necessary information and methods
 *to describe a person and their abilities.
 */

package seaportprogram;

import java.util.Scanner;

public class Person extends Thing {
    
    private String skill;
    
    public Person(Scanner scanner){
        super(scanner);
        skill = scanner.next();
    }//end constructor
    
    /**
     * Calls on super method to create a String representation of this object
     * including the additional field skill
     * @return returns a String representation of this object
     */
    @Override
    public String toString(){
        String description = "Person: " + super.toString() + " " + skill;
        return description;
    }
    
    /**
     * Returns the skill field
     * @return returns skill
     */
    public String getSkill(){
        return skill;
    }
}//end Person class
