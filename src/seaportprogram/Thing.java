/*File: Thing.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates basic structure and methods of a Thing class to be extended
 *and used by other subclasses.
 */
package seaportprogram;

import java.util.ArrayList;
import java.util.Scanner;

public class Thing implements Comparable<Thing> {

    int index;
    String name;
    int parent;

    public Thing(Scanner scanner) {
        if (scanner.hasNext()) {
            name = scanner.next();
        }
        if (scanner.hasNextInt()) {
            index = scanner.nextInt();
        }
        if (scanner.hasNextInt()) {
            parent = scanner.nextInt();
        } else {
            System.out.println("Problem");
        }
    }//end constructor

    public Thing() {                              //DELETE need this for World constructor - but is this bad?

    }//end no-arg Constructor

    /**
     * Returns the parent field of the object
     *
     * @return returns parent
     */
    public int getParent() {
        return parent;
    }

    /**
     * Returns the index field of the object
     *
     * @return returns index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the name field of the object
     *
     * @return returns name
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a string containing both the name and index fields.
     *
     * @return returns created string.
     */
    public String toString() {
        String description = name + " " + index;
        return description;
    }

    /**
     * Compares the index of one Thing object to the parent of another Thing
     *
     * @param thing Thing object to be compared
     * @return returns a positive value if the fields match, returns a negative
     * value if the fields differ.
     */
    @Override
    public int compareTo(Thing thing) {
        if (index == thing.parent) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Compares the index of one Thing object to an integer. If the values
     * match, the Thing is added to an ArrayList
     *
     * @param indexNumber integer representation of another index
     * @param itemsFound ArrayList containing all objects that match indexNumber
     */
    public void findIndicesAndAdd(int indexNumber, ArrayList<Thing> itemsFound) {
        if (index == indexNumber) {
            itemsFound.add(this);
        }
    }

    /**
     * Compare the name of one Thing object to a String. If the values match,
     * the Thing is added to an ArrayList
     *
     * @param inputName String representation of another object's name. Should
     * be in lower case
     * @param itemsFound ArrayList containing all objects that match inputName
     */
    public void findNamesAndAdd(String inputName, ArrayList<Thing> itemsFound) {
        if (name.toLowerCase().equals(inputName)) {
            itemsFound.add(this);
        }
    }

    /**
     * Converts both a Thing name and an input name to lower case and compares
     * them.
     *
     * @param thing Thing object to be compared to a String
     * @param inputName String to be compared to a Thing name
     * @return returns true is the converted inputs match, returns false if they
     * do not match.
     */
    public boolean compareNames(Thing thing, String inputName) {            
        boolean isEqual = false;

        String thingNameLowerCase = thing.getName().toLowerCase();
        String inputNameLowerCase = inputName.toLowerCase();

        if (thingNameLowerCase.equals(inputNameLowerCase)) {
            isEqual = true;
        }

        return isEqual;
    }

}//end Thing class
