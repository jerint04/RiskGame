package View;

import javax.swing.*;
/**
 * DisplayGuiHelp class
 *
 * @author Jerin
 * @version 1.0.0
 */
public class DisplayGuiHelp extends JFrame {
     JFrame theFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    JTextArea theText = new JTextArea(5,25);
    JTextArea theTextTwo = new JTextArea(5,25);

    /**
     * This is a Constructor for DisplayGuiHelp class which sets GUI title,size and location
     */


    public DisplayGuiHelp(){
        theFrame.setTitle("Risk Game");
        theFrame.setSize(500, 1000);
        theFrame.setLocation(550, 400);
    }

    /**
     * This method is used print the screen
     * @param text, String
     */
    public void printScreen(String text){
        theText.setText(text);
        mainPanel.add(theText);
        theFrame.getContentPane().add(mainPanel);
         theFrame.pack();
         theFrame.setVisible(true);

    }

    /**
     * This method is used to print the Second screen, statistics of the Player
     * @param text, String
     */
    public void printSecondScreen(String text){
        theTextTwo.setText(text);
        mainPanel.add(theTextTwo);
        theFrame.getContentPane().add(mainPanel);
        theFrame.pack();
        theFrame.setVisible(true);

    }

}
