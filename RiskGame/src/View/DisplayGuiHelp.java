package View;

import javax.swing.*;

public class DisplayGuiHelp extends JFrame {
     JFrame theFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    JTextArea theText = new JTextArea(5,25); //create the text area
    JTextArea theTextTwo = new JTextArea(5,25); //create the text area
    public DisplayGuiHelp(){
        theFrame.setTitle("Risk Game");
        theFrame.setSize(500, 1000);
        theFrame.setLocation(550, 400);
    }


public void printScreen(String text){
   ;
    //theText.append(text + "\n"); //append the contents of the array list to the text area
    theText.setText(text);
     mainPanel.add(theText);
    theFrame.getContentPane().add(mainPanel); //add the panel to the frame
    theFrame.pack();
    theFrame.setVisible(true);

}

public void printSecondScreen(String text){
    theTextTwo.setText(text);
    mainPanel.add(theTextTwo);//add the text area to the panel
    theFrame.getContentPane().add(mainPanel); //add the panel to the frame
    theFrame.pack();
    theFrame.setVisible(true);

}

}
