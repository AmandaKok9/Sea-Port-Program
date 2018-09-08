/*File: SeaPortProgram.java
 *Date: 8/27/18
 *Author: Amanda Kok
 *Purpose: Creates a GUI that prompts user for a file, reads file, and creates an
 *associated data structure. Allows user to search data structure once created.
 */
package seaportprogram;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SeaPortProgram extends JFrame {

    private JTextArea resultDisplay;
    private JComboBox searchList;
    private JTextField searchField;
    private JFileChooser fileChooser;
    private JPanel panel;
    private File userFile;
    private World world;

    public SeaPortProgram() {

        setTitle("Sea Port Program");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        //create text area within scroll pane
        resultDisplay = new JTextArea();
        resultDisplay.setFont(new Font("Monospaced", 0, 12));
        resultDisplay.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultDisplay);
        panel.add(scrollPane, BorderLayout.CENTER);

        //create button/search panel, add buttons in flowlayout
        JPanel searchPanel = new JPanel();

        JButton btnRead = new JButton("Read");
        searchPanel.add(btnRead);

        JButton btnDisplay = new JButton("Display");
        searchPanel.add(btnDisplay);

        JLabel targetLabel = new JLabel("Search Target:");
        searchPanel.add(targetLabel);

        searchField = new JTextField("", 10);
        searchPanel.add(searchField);

        String[] searchStrings = {"Name", "Index", "Skill", "Persons at Port Name",
            "Persons at Port Index", "Docks at Port Name", "Docks at Port Index"};
        searchList = new JComboBox(searchStrings);
        searchPanel.add(searchList);

        JButton btnSearch = new JButton("Search");
        searchPanel.add(btnSearch);

        panel.add(searchPanel, BorderLayout.NORTH);

        fileChooser = new JFileChooser();

        setVisible(true);
        
        //add functionality to buttons
        btnRead.addActionListener((ActionEvent e) -> {
            readFile();
        });

        btnDisplay.addActionListener((ActionEvent e) -> {
            displayWorld();
        });

        btnSearch.addActionListener((ActionEvent e) -> {
                searchWorld();
        });

    }//end no-arg constructor

    /**
     * Prompts user to select file with JFileChooser. Initializes a World object
     * and calls on this object to read the file and create an associated data
     * structure. Gives user feedback in text area about actions being taken.
     */
    public void readFile() {
        resultDisplay.append("Read File Button Pressed\n");
        int returnValue = fileChooser.showOpenDialog(panel);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            userFile = fileChooser.getSelectedFile();
            resultDisplay.append(userFile + " has been selected\n");
            resultDisplay.append("Creating world\n");

            world = new World(userFile);
            world.readFile();
        }
    }//end readFile

    /**
     * Shows the data structure created by World using toString method. If no
     * data structure is found, displays an error message to user.
     */
    public void displayWorld() {
        try{
            resultDisplay.append("Displaying World:\n");
            resultDisplay.append(world.toString());
        }catch(NullPointerException ne){
            JOptionPane.showMessageDialog(null, "Please Select a File First", 
                    "Error: No Existing Data Structure", JOptionPane.ERROR_MESSAGE);
        }
    }//end displayWorld
    
    /**
     * Using user input, searches world structure and adds all matching items to
     * an ArrayList.  Displays found matches or confirms no matches exist.
     */
    public void searchWorld(){
        ArrayList<Thing> foundItems = world.searchWorld((String) (searchList.getSelectedItem()), searchField.getText());
            if (foundItems.isEmpty()) {
                resultDisplay.append("\n---No matches found for '" + searchList.getSelectedItem() + "' '" + searchField.getText() + "':\n");
            } else {
                resultDisplay.append("\n---Search Results for '" + searchList.getSelectedItem() + "' '" + searchField.getText() + "':\n");
                for (Thing found : foundItems) {
                    resultDisplay.append(found.toString() + "\n");
                }
            }
    }//end searchWorld

    public static void main(String[] args) {

        SeaPortProgram seaPort = new SeaPortProgram();

    }//end main

}//end SeaPortProgram class
