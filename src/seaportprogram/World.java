/*File: World.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates data structure to represent contents of a file. Methods read 
 *a file and make necessary connections between classes to create a searchable 
 *data structure.
 */
package seaportprogram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class World extends Thing {

    private ArrayList<SeaPort> ports = new ArrayList<>();
    private PortTime time;                                //not used here, laying ground work for future projects
    private File file;

    public World(File file) {
        this.file = file;
    }

    /**
     * Reads file one line at a time. Passes this line to processLine. Shows
     * error message to user if there is an error reading the file.
     */
    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String fileLine = br.readLine();

            while (fileLine != null) {
                processLine(fileLine);

                fileLine = br.readLine();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//end readFile

    /**
     * Reads first item of the line. Then, takes appropriate action based on
     * this item. This includes creating the appropriate object and making all
     * necessary connections to related classes.
     *
     * @param fileLine String representing one line from a file
     */
    public void processLine(String fileLine) {

        Scanner scanner = new Scanner(fileLine);

        if (!scanner.hasNext()) {
            return;
        }
        switch (scanner.next()) {
            case "//":
                return;
            case "port":
                ports.add(new SeaPort(scanner));
                break;
            case "dock":
                addDock(scanner);
                break;
            case "ship":
                addShip(scanner);
                break;
            case "cship":
                addCargoShip(scanner);
                break;
            case "pship":
                addPassengerShip(scanner);
                break;
            case "person":
                addPerson(scanner);
                break;
            case "job":
                //empty for now, functionality will be added in future projects
                break;
        }
    }//ends processLine

    /**
     * Creates new Dock, adds this object to appropriate SeaPort
     *
     * @param scanner Scanner object passed in from processLine. Scanner reads a
     * line from a file, the line contains needed information to make object.
     */
    public void addDock(Scanner scanner) {
        Dock dock = new Dock(scanner);

        for (SeaPort sp : ports) {
            int compareValue = sp.compareTo(dock);

            if (compareValue > 0) {
                sp.addDock(dock);
            }
        }
    }//end addDock

    /**
     * Creates new Ship and passes it to addShipToList to make needed
     * connections within the data structure
     *
     * @param scanner Scanner object passed in from processLine. Scanner reads a
     * line from a file, the line contains needed information to make object.
     */
    public void addShip(Scanner scanner) {
        Ship ship = new Ship(scanner);
        addShipToList(ship);
    }//end addShip

    /**
     * Creates new PassengerShip and passes it to addShipToList to make needed
     * connections within the data structure.
     *
     * @param scanner Scanner object passed in from processLine. Scanner reads a
     * line from a file, the line contains needed information to make object.
     */
    public void addPassengerShip(Scanner scanner) {
        PassengerShip pship = new PassengerShip(scanner);
        addShipToList(pship);
    }//end addPassengerShip

    /**
     * Creates new CargoShip and passes it to addShipToList to make needed
     * connections within the data structure.
     *
     * @param scanner Scanner object passed in from processLine. Scanner reads a
     * line from a file, the line contains needed information to make object.
     */
    public void addCargoShip(Scanner scanner) {
        CargoShip cship = new CargoShip(scanner);
        addShipToList(cship);
    }//end addCargoShip

    /**
     * Tests if ship has an associated port within the data structure. If a port
     * is not found, the ship is passed to findDockAddShip
     *
     * @param ship Ship object to compare to SeaPort list.
     */
    public void addShipToList(Ship ship) {
        boolean portFound = findPortAddShip(ship);

        if (!portFound) {
            findDockAddShip(ship);
        }
    }//end addShipToList

    /**
     * Compares ship to each SeaPort in structure to find a parent. If a parent
     * port is found, the ship is added to that port and it is added to the
     * general list of all ships in the structure.
     *
     * @param ship Ship object to compare to SeaPort list
     * @return returns true if a parent port is found, false if no matches are
     * found
     */
    public boolean findPortAddShip(Ship ship) {

        boolean portFound = false;

        for (SeaPort sp : ports) {
            int compareValue = sp.compareTo(ship);

            if (compareValue > 0) {
                sp.addShip(ship);
                sp.addQueShip(ship);
                portFound = true;
            }
        }

        return portFound;

    }//end findPortAddShip

    /**
     * Compares ship to each SeaPort's list of associated docks using
     * addShipToDock. If a match is found, the ship is added to the dock.
     *
     * @param ship Ship object to compare to SeaPort list
     */
    public void findDockAddShip(Ship ship) {

        for (SeaPort sp : ports) {
            sp.addShipToDock(ship);
        }
    }//end findDockAddShip

    /**
     * Creates new Person, compares it to the list of SeaPorts, and add person
     * to the SeaPort if the port is listed as the parent of the Person.
     *
     * @param scanner Scanner object passed in from processLine. Scanner reads a
     * line from a file, the line contains needed information to make object.
     */
    public void addPerson(Scanner scanner) {
        Person person = new Person(scanner);

        for (SeaPort sp : ports) {
            int compareValue = sp.compareTo(person);

            if (compareValue > 0) {
                sp.addPerson(person);
            }
        }
    }//end addPerson

    /**
     * Based on boxSelection, corresponding search is initialized through the
     * list of all SeaPorts in World. All objects matching textInput are added
     * to an ArrayList and returned. Error is shown to user if textInput and
     * boxSelection do not correspond.
     *
     * @param boxSelection String representation of JComboBox option selected by
     * user
     * @param textInput input by user to be used as search criteria
     * @return returns an ArrayList of items that match textInput
     */
    public ArrayList<Thing> searchWorld(String boxSelection, String textInput) {
        ArrayList<Thing> foundItems = new ArrayList<>();

        switch (boxSelection) {
            case "Name":

                for (SeaPort sp : ports) {
                    sp.findNamesAndAdd(textInput.toLowerCase(), foundItems);
                }
                break;

            case "Index":
                
                int indexInput = testIfInputIsNumeric(textInput);

                for (SeaPort sp : ports) {
                    sp.findIndicesAndAdd(indexInput, foundItems);
                }
                break;

            case "Skill":

                for (SeaPort sp : ports) {
                    sp.findSkillsAndAdd(textInput.toLowerCase(), foundItems);
                }
                break;
                
            case "Persons at Port Name":

                for (SeaPort sp : ports) {
                    if (compareNames(sp, textInput)) {     
                        for (Person mp : sp.getPersons()) {
                            foundItems.add(mp);
                        }
                    }
                }
                break;
                
            case "Persons at Port Index":
                
                int portPersonIndex = testIfInputIsNumeric(textInput);
                
                for(SeaPort sp : ports){
                    if(sp.getIndex() == portPersonIndex){
                        for(Person mp : sp.getPersons()){
                            foundItems.add(mp);
                        }
                    }
                }
                break;
                
            case "Docks at Port Name":
                
                for(SeaPort sp : ports){
                    if(compareNames(sp, textInput)){
                        for(Dock md : sp.getDocks()){
                            foundItems.add(md);
                        }
                    }
                }
                break;
                
            case "Docks at Port Index":
                int portDockIndex = testIfInputIsNumeric(textInput);
                
                for(SeaPort sp : ports){
                    if(sp.getIndex() == portDockIndex){
                        for(Dock md : sp.getDocks()){
                            foundItems.add(md);
                        }
                    }
                }
                break;
        }
        return foundItems;
    }//end searchWorld

    /**
     * Checks if a String is an integer and returns the converted integer.
     * @param indexInput String that may represent integer
     * @return returns a integer representation of indexInput if the string is
     * numeric. Returns a negative number and an error message if it is not numeric.
     */
    public int testIfInputIsNumeric(String indexInput) {
        
        try {

            int textIntInput = Integer.parseInt(indexInput);
            return textIntInput;
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number to search by index",
                    "Error: Invalid Search Format", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    /**
     * Creates a String with a label and a representation of the created data
     * structure
     *
     * @return returns String representation of the World / data structure
     */
    @Override
    public String toString() {
        String description = "\n>>>>>> The World:";
        for (SeaPort sp : ports) {
            description += "\n" + sp;
        }
        description += "\n";
        return description;
    }//end toString

}//end World class
