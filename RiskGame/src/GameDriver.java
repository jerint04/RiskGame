import Controller.GameController;
import Model.GameModel;
import Model.Player;

import static Controller.GameController.checkWinner;
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
     *
     * @param args, String array
     */
    public static void main(String[] args) {
        boolean playGame = true;
        GameController.startOrLoadGame();
        GameController.initialisePlayers();
        if (validationOfPlayersAndCountiesNumber()) {
            GameController.initialisationInfantry();
            GameController.assigningCountries();
            GameController.assigningCountriesToPlayers();
            System.out.println("---------- Game Play Starts -------------");
            while (checkWinner()) {
                for (int playerId : GameModel.playerHashMap.keySet()) {
                    System.out.println(" --------------  Player " + GameModel.playerHashMap.get(playerId).getName() + "'s Turn ----------");
                    System.out.println("-------- Reinforcement Phase --------------");
                    Player.armyCalculationDuringReinforcement(playerId);
                    Player.armyPlacementDuringReinforcement(playerId);
                    System.out.println("-------- Attack Phase --------------");
                    Player.playerAttackTurn(playerId);
                    System.out.println("-------- Fortification Phase --------------");
                    Player.fortificationPhase(playerId);
                }
            }
        } else {
            System.out.println("Number of Countries are less than required number to start the game(number of player* 2)");
        }


    }
}
