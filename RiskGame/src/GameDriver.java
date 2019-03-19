import Controller.GameController;
import Model.GameModel;

import static Controller.ValidateMap.validationOfPlayersAndCountiesNumber;

/**
 * GameDriver Class
 *
 * @author Hemanshu
 * @version 1.0.0
 * Includes main() method of the program
 */

public class GameDriver {


    /**
     * This is the main() method of the program
     * Entry point of the Execution of the whole program
     * @param args, String array
     */
    public static void main(String[] args) {
        boolean playGame = true;
        GameController.StartOrLoadGame();
        GameController.initialisePlayers();
        if (validationOfPlayersAndCountiesNumber()) {
            GameController.initialisationInfantry();
            GameController.assigningCountries();
            GameController.assigningCountriesToPlayers();
            System.out.println("---------- Game Play Starts -------------");
            while (playGame) {
                for (int playerId : GameModel.playerHashMap.keySet()) {
                    System.out.println(" --------------  Player " +GameModel.playerHashMap.get(playerId).getName()+"'s Turn ----------");
                    System.out.println("-------- Reinforcement Phase --------------");
                    GameController.armyCalculationDuringReinforcement(playerId);
                    GameController.armyPlacementDuringReinforcement(playerId);
                    System.out.println("-------- Fortification Phase --------------");
                    GameController.fortificationPhase(playerId);
                }
            }
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
