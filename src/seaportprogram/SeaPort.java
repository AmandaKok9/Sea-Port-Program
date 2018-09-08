/*File: SeaPort.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates SeaPort object and associated lists of docks, ships, ships in
 *queue, and persons that are connected with this port and methods to manage and 
 *search these lists.
 */
package seaportprogram;

import java.util.ArrayList;
import java.util.Scanner;

public class SeaPort extends Thing {

    private ArrayList<Dock> docks = new ArrayList<>();
    private ArrayList<Ship> que = new ArrayList<>();
    private ArrayList<Ship> ships = new ArrayList<>();
    private ArrayList<Person> persons = new ArrayList<>();

    public SeaPort(Scanner scanner) {
        super(scanner);
    }

    /**
     * Calls on the super method to create a String representation of this object,
     * also cycles through each of the ArrayList fields of this object and creates
     * String representations of these objects as well
     * @return returns String representation of this object
     */
    @Override
    public String toString() {
        String description = "\n\nSeaPort: " + super.toString();
        for (Dock md : docks) {
            description += "\n" + md;
        }
        description += "\n\n---List of all ships in que:";
        for (Ship ms : que) {
            description += "\n > " + ms;
        }
        description += "\n\n---List of all Ships:";
        for (Ship ms : ships) {
            description += "\n >" + ms;
        }
        description += "\n\n---List of all Persons:";
        for (Person mp : persons) {
            description += "\n >" + mp;
        }
        return description;
    }//end toString
    
    /**
     * Calls on super method to compare the name of this SeaPort object to inputName.
     * Also, compares inputName to the name of every object associated with this 
     * SeaPort object. If a match is found, the object is added to an ArrayList.
     * All names are changed to lower case to avoid case sensitive search.
     * @param inputName String representation of name search criteria entered by user
     * @param itemsFound ArrayList containing all objects that match inputName
     */
    @Override
    public void findNamesAndAdd(String inputName, ArrayList<Thing> itemsFound) {
        //compare to this SeaPort
        super.findNamesAndAdd(inputName.toLowerCase(), itemsFound);
        //compare to all Docks
        for (Dock md : docks) {
            if (md.getName().toLowerCase().equals(inputName.toLowerCase())) {
                itemsFound.add(md);
            }
        }
        //compare to all Ships
        for (Ship ms : ships) {
            if (ms.getName().toLowerCase().equals(inputName.toLowerCase())) {
                itemsFound.add(ms);
            }
        }
        //compare to all Persons
        for (Person mp : persons) {
            if (mp.getName().toLowerCase().equals(inputName.toLowerCase())) {
                itemsFound.add(mp);
            }
        }
    }//end findNamesAndAdd

    /**
     * Calls on super method to compare the index of this SeaPort object with 
     * indexNumber. Also, compares indexNumber to the index of every object 
     * associated with this SeaPort object.  If a match is found, the object is 
     * added to an ArrayList.
     * @param indexNumber integer representation of index search criteria entered
     * by user
     * @param itemsFound ArrayList containing all objects that match indexNumber
     */
    @Override
    public void findIndicesAndAdd(int indexNumber, ArrayList<Thing> itemsFound) {
        //compare to this SeaPort
        super.findIndicesAndAdd(indexNumber, itemsFound);
        //compare to all Docks
        for (Dock md : docks) {
            if (md.getIndex() == indexNumber) {
                itemsFound.add(md);
            }
        }
        //compare to all Ships
        for (Ship ms : ships) {
            if (ms.getIndex() == indexNumber) {
                itemsFound.add(ms);
            }
        }
        //compare to all Persons
        for (Person mp : persons) {
            if (mp.getIndex() == indexNumber) {
                itemsFound.add(mp);
            }
        }
    }//end compareIndexes

    /**
     * Compares skill to the skill of every Person associated with this SeaPort.
     * If a match is found, the object is added to an ArrayLsit
     * @param skill String representation of skill search criteria entered
     * @param itemsFound ArrayList containing all objects that match skill
     */
    public void findSkillsAndAdd(String skill, ArrayList<Thing> itemsFound) {
        for (Person mp : persons) {
            if (mp.getSkill().toLowerCase().equals(skill.toLowerCase())) {
                itemsFound.add(mp);
            }
        }
    }//end findSkillsAndAdd

    /**
     * Compares all Dock indexes associated with this SeaPort to ship. If the index
     * of the dock and the parent of the ship match, the ship is added to the dock
     * and it is added to the list of ships.
     * @param ship Ship object to compare to all Docks
     */
    public void addShipToDock(Ship ship) {
        for (Dock md : docks) {
            int compareValue = md.compareTo(ship);

            if (compareValue > 0) {
                md.addShip(ship);
                ships.add(ship);
            }
        }
    }//end addShipToDock

    /**
     * Adds ship to the general list of all ships
     * @param ship Ship object to add to ships ArrayList
     */
    public void addShip(Ship ship) {
        ships.add(ship);
    }

    /**
     * Adds ship to the queue of ships waiting to dock
     * @param ship Ship object to add to queue ArrayList
     */
    public void addQueShip(Ship ship) {
        que.add(ship);
    }

    /**
     * Adds dock to the ArrayList of Docks associated with this SeaPort
     * @param dock Dock object to add to docks ArrayList
     */
    public void addDock(Dock dock) {
        docks.add(dock);
    }

    /**
     * Adds person to the ArrayList of Persons associated with this SeaPort
     * @param person Person object to add to persons ArrayList
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Returns the ArrayList of all docks associated with this SeaPort
     * @return returns docks ArrayList
     */
    public ArrayList<Dock> getDocks() {
        return docks;
    }

    /**
     * Returns the ArrayList of all ships associated with this SeaPort
     * @return  returns ships ArrayList
     */
    public ArrayList<Ship> getAllShips() {
        return ships;
    }

    /**
     * Returns the ArrayList of all persons associated with this SeaPort
     * @return returns persons ArrayList
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }
}//end SeaPort class
