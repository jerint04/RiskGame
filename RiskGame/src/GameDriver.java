import Controller.*;
import Model.GameModel;
import Model.Helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Controller.GameController.*;
import static Controller.ValidateMap.validationOfPlayersAndCountiesNumber;
import static Model.GameModel.draw;

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
        Scanner a = new Scanner(System.in);
        System.out.println("Enter 1 for Tournament and 2 for Human match");
        int mode = a.nextInt();
        if(mode == 1){
            tournamentDriver();
        }else {
            GameController.startOrLoadGame();
            GameController.initialisePlayers();
            if (validationOfPlayersAndCountiesNumber()) {
                GameController.initialisationInfantry();
                GameController.assigningCountries();
                GameController.assigningCountriesToPlayers();
                System.out.println("---------- Game Play Starts -------------");
                while (checkWinner()) {
                    for (int playerId : GameModel.playerHashMap.keySet()) {
//                   /*Todo : this alive this*/
                        if (GameModel.playerHashMap.get(playerId).alive) {
                            if (GameModel.playerHashMap.get(playerId).getPlayerType().equals("human")) {
                                Context context = new Context(new HumanPlayer());
                                System.out.println(" --------------  Player " + GameModel.playerHashMap.get(playerId).getName() + "'s Turn ----------");
                                System.out.println("-------- Reinforcement Phase --------------");
                                context.executeArmyCalculation(playerId);
                                context.executeArmyPlacement(playerId);
                                System.out.println("-------- Attack Phase --------------");
                                context.executePlayerAttack(playerId);
                                System.out.println("-------- Fortification Phase --------------");
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


    public static void tournamentDriver() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of Maps to use : 1 to 5 ");
        int mapsCount = sc.nextInt();
        sc.nextLine();
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
        String[] mapName = new String[mapsCount];
        for (int i = 0; i < mapsCount; i++) {
            mapName[i] = sc.nextLine();
        }
//        startOrLoadGame();
        System.out.println("Number of Players to play (2 to 4) : ");
        int numberOfPlayers = sc.nextInt();
        sc.nextLine();
        String[] playerName = new String[numberOfPlayers];
        String[] playerType = new String[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player Name :");
            playerName[i] = sc.nextLine();
            System.out.println("Player Type :");
            playerType[i] = sc.nextLine();
        }
        initialisePlayerForTournament(numberOfPlayers, playerName, playerType);
        System.out.println("Number of games on each map :");
        int numberOfGames = sc.nextInt();
        System.out.println("Turns Every Player get before Draw :(10 to 50) :");
        int maxTurns = sc.nextInt();
        String[] winnerRecord = new String[mapsCount * numberOfGames];
        int s = 0;
        for (String map : mapName) {
            for (int game = 0; game < numberOfGames; game++) {
                tournamentLoadMap(map);
                initialisePlayerForTournament(numberOfPlayers, playerName, playerType);
                GameController.initialisationInfantry();
                GameController.assigningCountries();
                assigningCountriesToPlayersAuto();
                int turn = 0;
                draw = false;
                GameModel.winner = "";
                /*Logic for Turns and winner*/
                while (checkMaxTurnsOrDeclareWinner(turn, maxTurns)) {
                    for (int playerId : GameModel.playerHashMap.keySet()) {
                        updatePlayerModalForWinner();
                        if (GameModel.playerHashMap.get(playerId).alive) {
                            if (GameModel.playerHashMap.get(playerId).getPlayerType().equals("cheater")) {
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
                        }
                    }
                    turn++;
                }

                if (!draw) {
                    winnerRecord[s] = "Map : " + map + " Game Count:" + game + " Winner is :" + GameModel.winner;
                    System.out.println("We have a winner"); /*TODO push this winner*/
                } else {
                    winnerRecord[s] = "Map : " + map + " Game Count:" + game + " Winner is : Draw";
                    System.out.println("Its a draw"); /*TODO Push this draw*/
                }
                s++;
            }
        }

        System.out.println("Summary of Tournament");
        for(int i=0; i<s;i++){
            System.out.println(winnerRecord[i]);
        }

    }
}
