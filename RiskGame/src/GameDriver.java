import Controller.AttackPhase;
import Controller.GameController;
import Model.GameModel;
import Model.TryFrame;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.ValidateMap.validationOfPlayersAndCountiesNumber;

/**
 * GameDriver Class
 *
 * @author Hemanshu
 * @version 1.0.0
 * Includes main() method of the program
 */

public class GameDriver {


    private JButton loadGameButton;
    private JButton startGameButton;

    public GameDriver() {
/*        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.StartOrLoadGame();

            }
            /*
            private void initComponents() {
                                                /*  scrollPane1 = new JScrollPane();
                                                  resultTextField = new JTextPane();
                                                  startButton = new JButton();
                                                  stopButton = new JButton();
            }
        }*/

    }

    /**
     * This is the main() method of the program
     * Entry point of the Execution of the whole program
     * @param args, String array
     */


    public static void main(String[] args) {

//        JFrame frame = new JFrame("GameDriver");
    //    frame.setContentPane(new GameDriver().loadGameButton);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //      frame.pack();
    //    frame.setVisible(true);

        TryFrame tryFrame=new TryFrame();
        tryFrame.loadPanel1();
/*
        boolean playGame = true;
    //    GameController.StartOrLoadGame();
        GameController.InitialisePlayers();
        if (validationOfPlayersAndCountiesNumber()) {
            GameController.initialisationInfantry();
            GameController.assigningCountries();
            GameController.assigningCountriesToPlayers();
            System.out.println("---------- Game Play Starts -------------");
            while (playGame) {
                for (int playerId : GameModel.playerHashMap.keySet()) {
                    System.out.println(" --------------  Player " + GameModel.playerHashMap.get(playerId).getName() + "'s Turn ----------");
                    //System.out.println("-------- Reinforcement Phase --------------");
                    GameController.armyCalculationDuringReinforcement(playerId);
                    GameController.armyPlacementDuringReinforcement(playerId);
                   // System.out.println("-------- Attack Phase --------------");
                    AttackPhase.PlayerAttackTurn(playerId);
                   // System.out.println("-------- Fortification Phase --------------");
                    GameController.fortificationPhase(playerId);

                }
            }
            /*GameController.earnRiskCards(0);
            GameController.earnRiskCards(0);
            GameController.earnRiskCards(0);
            GameController.earnRiskCards(0);
            GameController.exchangeCardsForArmies(0);
*/
        } else {
            System.out.println("Number of Countries are less than required number to start the game(number of player* 2)");
        }

        /*start the loop*/
//        GameController.armyCalculationDuringReinforcement(p1);
/*        armyPlacementDuringReinforcement(p1);

        fortificationPhase();*/
//        end the loop


    }

}
