import Controller.GameController;
import Model.GameModel;

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
        /*TODO : validate player info*/

//      TODO : Test this part
        GameModel.initialisationInfantry();
        GameModel.assigningCountries();
        GameController.assigningCountriesToPlayers();

        /*TODO in loop (don't know the exit condition right now)  REINFORCEMENT PART*/
        /*start the loop*/
        /*armyCalculationDuringReinforcement(p1);
        armyPlacementDuringReinforcemet(p1);

        fortificationPhase();*/
//        end the loop

    }
}
