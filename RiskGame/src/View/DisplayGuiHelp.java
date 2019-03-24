package View;

import javax.swing.*;
import java.util.ArrayList;
/**
 * DisplayGuiHelp class
 *
 * @author Jerin
 * @version 1.0.0
 */
public class DisplayGuiHelp {

    /**
     * constructor of the DisplayGuiHelp object that has the list passed to it on creation
     * @param text, String
     */
    public DisplayGuiHelp(String text)
    {
        final JFrame theFrame = new JFrame();
        theFrame.setTitle("Stack exchange help");
        theFrame.setSize(500, 500);
        theFrame.setLocation(550, 400);

        JPanel mainPanel = new JPanel();

        JTextArea theText = new JTextArea(5,25); //create the text area



            theText.append(text + "\n"); //append the contents of the array list to the text area


        mainPanel.removeAll();
        mainPanel.updateUI();
        mainPanel.add(theText); //add the text area to the panel

        theFrame.getContentPane().add(mainPanel); //add the panel to the frame
        theFrame.pack();
        theFrame.setVisible(true);

    }
}
