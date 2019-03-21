package Model;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TryFrame extends JFrame implements ActionListener {
    JPanel panel;
    JButton jButton1,jButton2;

    public void loadPanel1(){

        panel=new JPanel(new GridLayout(2,1));
        panel.setSize(new Dimension(400,400));
        panel.setBackground(Color.orange);

        jButton1=new JButton("Start Game");
        jButton1.setSize(100,100);
        jButton1.setForeground(Color.blue);

        jButton2=new JButton("Load Game");
        jButton2.setForeground(Color.blue);

        jButton1.addActionListener(this);
        jButton2.addActionListener(this);

        panel.add(jButton1);
        panel.add(jButton2);
        add(panel);
        setSize(new Dimension(400,400));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
 //  if(   e.getActionCommand()=="button"||e.getActionCommand().equals("button")){
       if(e.getActionCommand()=="Start Game" || e.getActionCommand().equals("Start Game"))
       {

           dispose();
         //  GameController.StartOrLoadGame();
           Frame2 frame2 = new Frame2();
           frame2.loadFrame2();
       }
       else if(e.getActionCommand()=="Load Game" || e.getActionCommand().equals("Load Game"))
       {
           GameController.LoadMap();
       }
       else
       {
           System.out.println("fail");
       }


      // GameController.StartOrLoadGame();
   //}
   //

    }
}
