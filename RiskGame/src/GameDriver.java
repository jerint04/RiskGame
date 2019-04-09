import Controller.*;
import Model.GameModel;
import Model.Helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Controller.GameController.*;
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

                    if (GameModel.playerHashMap.get(playerId).getPlayerType().equals("human")) {
                        Context context = new Context(new HumanPlayer());
                        context.executeArmyCalculation(playerId);
                        context.executeArmyPlacement(playerId);
                        context.executePlayerAttack(playerId);
                        context.executeFortificationPhase(playerId);

                    } else if (GameModel.playerHashMap.get(playerId).getPlayerType().equals("cheater")) {
                        Context context = new Context(new CheaterPlayer());
                        context.executeArmyCalculation(playerId);
                        context.executeArmyPlacement(playerId);
                        context.executePlayerAttack(playerId);
                        context.executeFortificationPhase(playerId);

                    } else if (GameModel.playerHashMap.get(playerId).getPlayerType().equals("aggressive")) {
                        Context context = new Context(new AggressivePlayer());
                        context.executeArmyCalculation(playerId);
                        context.executeArmyPlacement(playerId);
                        context.executePlayerAttack(playerId);
                        context.executeFortificationPhase(playerId);

                    } else if (GameModel.playerHashMap.get(playerId).getPlayerType().equals("benevolent")) {
                        Context context = new Context(new BenevolentPlayer());
                        context.executeArmyCalculation(playerId);
                        context.executeArmyPlacement(playerId);
                        context.executePlayerAttack(playerId);
                        context.executeFortificationPhase(playerId);

                    } else if (GameModel.playerHashMap.get(playerId).getPlayerType().equals("random")) {
                        Context context = new Context(new RandomPlayer());
                        context.executeArmyCalculation(playerId);
                        context.executeArmyPlacement(playerId);
                        context.executePlayerAttack(playerId);
                        context.executeFortificationPhase(playerId);

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


    public static void tournamentDriver() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of Maps to use : 1 to 5 ");
        int mapsCount = sc.nextInt();
        List<String> getFileName = new ArrayList<String>();
        File[] filesName = new File(Helper.pathName).listFiles();
        System.out.println(filesName.length);
        for (File getFilename : filesName) {
            if (getFilename.isFile()) {
                getFileName.add(getFilename.getName());
            }
        }
        for (int i = 0; i < getFileName.size(); i++) {
            System.out.println(getFileName.get(i));
        }
        System.out.println("Enter the map name you want to load (Only name, without extension) :");
//        String[] mapName = new String[mapsCount];
//        for (int i = 0; i < mapsCount; i++) {
//            mapName[i] = sc.nextLine();
//        }
        startOrLoadGame();
        System.out.println("Number of Players to play (2 to 4) : ");
        initialisePlayers();
        System.out.println("Number of games on each map :");
        int numberOfGames = sc.nextInt();
        System.out.println("Turns Every Player get before Draw :(10 to 50) :");
        int turns = sc.nextInt();




    }

}
