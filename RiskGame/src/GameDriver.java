import Controller.AggressivePlayer;
import Controller.Context;
import Controller.GameController;
import Controller.HumanPlayer;
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

                    if( GameModel.playerHashMap.get(playerId).getPlayerType().equals("human")){
                        Context context = new Context(new HumanPlayer());
                        context.executeArmyCalculation(playerId);
                    }else if(GameModel.playerHashMap.get(playerId).getPlayerType().equals("aggressive")){
                        Context context = new Context(new AggressivePlayer());
                        context.executeArmyCalculation(playerId);
                    }
//                    System.out.println(" --------------  Player " + GameModel.playerHashMap.get(playerId).getName() + "'s Turn ----------");
//                    System.out.println("-------- Reinforcement Phase --------------");
//                    Player.armyCalculationDuringReinforcementHumanPlayer(playerId);
//                    Player.armyPlacementDuringReinforcementHumanPlayer(playerId);
//                    System.out.println("-------- Attack Phase --------------");
//                    Player.playerAttackTurnHumanPlayer(playerId);
//                    System.out.println("-------- Fortification Phase --------------");
//                    Player.fortificationPhaseHumanPlayer(playerId);
                }
            }
        } else {
            System.out.println("Number of Countries are less than required number to start the game(number of player* 2)");
        }


    }
}
