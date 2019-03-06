import Controller.GameController;
import Model.GameModel;

import static Controller.ValidateMap.validationOfPlayersAndCountiesNumber;

/**
 * GameDriver Class
 *
 * @author Hemanshu
 * @version 1.0.0
 * @date 2019-02-22
 * Includes main() method of the program
 */

public class GameDriver {


    /**
     * This is the main() method of the program
     * Entry point of the Execution of the whole program
     */
    public static void main(String[] args) {
        GameController.StartOrLoadGame();
        GameController.InitialisePlayers();
        if(validationOfPlayersAndCountiesNumber()) {
            GameController.initialisationInfantry();
            GameController.assigningCountries();
            GameController.assigningCountriesToPlayers();
            for (int playerId : GameModel.countryIdHashMap.keySet()) {
                GameController.armyCalculationDuringReinforcement(playerId);
            }
        }else{
            System.out.println("Number of Countries are less than required number to start the game(number of player* 2)");
        }

        /*start the loop*/
//        GameController.armyCalculationDuringReinforcement(p1);
/*        armyPlacementDuringReinforcemet(p1);

        fortificationPhase();*/
//        end the loop

    }
}
