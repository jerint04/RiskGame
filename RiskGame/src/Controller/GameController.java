package Controller;

import Model.*;
import View.DisplayGuiHelp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static Controller.CreateMapFile.createFile;
import static Model.GameModel.*;

/**
 * GameController class
 *
 * @author Hemanshu
 * @version 1.0.0
 */
public class GameController {
    public static DisplayGuiHelp gui1 = new DisplayGuiHelp();


    /**
     * This method is to run the Tournament
     * It accepts all the parameters required to run the tournament
     *
     * @return Array of string which contains the results of all the matches played
     */
    public static String[] tournamentGame(int mapsCount, String[] mapName, int numberOfGames, int numberOfPlayers, String[] playerName, String[] playerType, int maxTurns) {
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
                    winnerRecord[s] = "Map : " + map + " Game Count:" + game + " Result: Winner is :" + GameModel.winner;
                    System.out.println("We have a winner");
                } else {
                    winnerRecord[s] = "Map : " + map + " Game Count:" + game + " Result: Its a Draw";
                    System.out.println("Its a draw");
                }
                s++;
            }
        }
        return winnerRecord;
    }


    public static void normalGame() {
        Scanner input = new Scanner(System.in);
        while (checkWinner()) {
            for (int playerId : GameModel.playerHashMap.keySet()) {

                System.out.println("Do you want to save the game :(yes to save)");
                String saveGame = input.nextLine();
                if (saveGame.equals("yes")) {

                    StringBuffer fileData = new StringBuffer();
                    fileData.append("[Map]\r\n");
                    fileData.append(mapname + "%");
                    fileData.append(playerId + "\r\n");


                    fileData.append("[PlayerInformation]\r\n");
                    for (int playerIdNumber : GameModel.playerHashMap.keySet()) {
                        Player playerData = GameModel.playerHashMap.get(playerIdNumber);
//                        fileData.append("player**" + playerData.playerId + "%");

                        fileData.append(playerData.getPlayerId() + "%");
                        fileData.append(playerData.getName() + "%");
                        fileData.append(playerData.playerType + "%");
                        fileData.append(playerData.alive + "%");
                        for (String names : playerData.countriesOwned) {
                            fileData.append(names + ",");
                        }
                        fileData.append("%");
                        fileData.append(playerData.numberOfInfantry + "%");
                        fileData.append(playerData.cardBooleanValue + "%");
                        fileData.append(playerData.turn + "%");
                        fileData.append(playerData.armiesInExcahngeOfcards + "%");
                        fileData.append(playerData.getShouldGetTheCard() + "\r\n");
//                        fileData.append(playerData.infoAboutAction+);


//                        fileData.append("continentsOwned=" + playerData.co + "%");
//                        fileData.append("GamePhase=" +  + "%");
                    }

                    fileData.append("[CountriesInformation]\r\n");
                    for (String country : countryHashMap.keySet()) {
                        fileData.append(country + "=" + countryHashMap.get(country).getPlayerId() + "=" + countryHashMap.get(country).numberOfSoldiers + "%");
                    }


                    Path path = Paths.get("./assets/saved");
                    BufferedWriter writer = null;
                    try {
                        Scanner sc = new Scanner(System.in);
                        //Delete temporary file
                        String currentDirectory = System.getProperty("user.dir");
                        Path tempFilePath = Paths.get("./assets/saved/SavedFile.map");
                        Files.deleteIfExists(tempFilePath);
                        writer = Files.newBufferedWriter(tempFilePath, StandardCharsets.UTF_8);
                        writer.write(new String(fileData));
                        writer.close();
                    } catch (Exception e) {
                        System.out.println("Exception :" + e);
                    }


                }
                updatePlayerModalForWinner();
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
            }
        }
    }


    /**
     * This method is used to check the winner of the game
     *
     * @return true, boolean type
     */
    public static boolean checkWinner() {
        int numberOfPlayers = playerHashMap.keySet().size();
        int[] playersInGame = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playersInGame[i] = 1;
        }
        for (int each : playerHashMap.keySet()) {
            if (playerHashMap.get(each).countriesOwned.size() == 0) {
                playersInGame[each] = 0;
                playerHashMap.get(each).alive = false;
            }
        }
        int total = 0;
        int winner = -1;
        for (int i = 0; i < numberOfPlayers; i++) {
            total = total + playersInGame[i];
            if (playersInGame[i] == 1) {
                winner = i;
            }
        }
        if (total > 1) {
            return true;
        } else if (total == 1) {
            System.out.println("Winner Winner Chicken Dinner !! Player " + playerHashMap.get(winner).getName() + " has won all the countries !");
            return false;
        }
        return true;
    }

    /**
     * This method is used to check the maximum turns of the player or winner of the game
     *
     * @param turn    , interger type
     * @param maxTurn , integer type
     * @return true/false, boolean type
     */
    public static boolean checkMaxTurnsOrDeclareWinner(int turn, int maxTurn) {
        if (turn < maxTurn) {
            int numberOfPlayers = playerHashMap.keySet().size();
            int[] playersInGame = new int[numberOfPlayers];
            for (int i = 0; i < numberOfPlayers; i++) {
                playersInGame[i] = 1;
            }
            for (int each : playerHashMap.keySet()) {
                if (playerHashMap.get(each).countriesOwned.size() == 0) {
                    playersInGame[each] = 0;
                    playerHashMap.get(each).alive = false;
                }
            }
            int total = 0;
            int winner = -1;
            for (int i = 0; i < numberOfPlayers; i++) {
                total = total + playersInGame[i];
                if (playersInGame[i] == 1) {
                    winner = i;
                }
            }
            if (total > 1) {
                return true;
            } else if (total == 1) {
                System.out.println("Winner Winner Chicken Dinner !! Player " + playerHashMap.get(winner).getName() + " has won all the countries !");
                GameModel.winner = playerHashMap.get(winner).playerType;
                return false;
            }
            return true;
        } else {
            GameModel.draw = true;
            return false;
        }

    }

    /**
     * Updates the Modal of the player after every turn
     * Those player who are out of the game will be marked as false
     */
    public static void updatePlayerModalForWinner() {
        for (int each : playerHashMap.keySet()) {
            if (playerHashMap.get(each).countriesOwned.size() == 0) {
                playerHashMap.get(each).alive = false;
            }
        }
    }

    /**
     * This method will load a map
     */
    public static void loadMap() {
        Scanner sc = new Scanner(System.in);
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
        String mapName = sc.nextLine();
        mapname = mapName;
        if (ReadMap.readMap(Helper.pathName + "/" + mapName + ".map")) {
            ValidateMap.validateMap();
        } else {
            System.out.println("Not able to read map successfully. Please check you Map Format");
        }
        System.out.println("Do you want to edit the map : Enter 1 to edit the map");
        int a = sc.nextInt();
        if (a == 1) {
            editMap();
        }
    }

    /**
     * This method is used to load tournament map
     *
     * @param mapName , name of the map
     */
    public static void tournamentLoadMap(String mapName) {
        GameModel.reInitializeVariables();
        CountryAdjacencyMatrix.initializeCountryMatrix();
        if (ReadMap.readMap(Helper.pathName + "/" + mapName + ".map")) {
            ValidateMap.validateMap();
        } else {
            System.out.println("Not able to read map " + mapName + " successfully. Please check you Map Format");
        }
    }


    /**
     * This method will perform operation required to start or load a game
     */
    public static void startOrLoadGame() {
        System.out.println("Select 1 to Start the game and create map, Select 2 to Load the Game :");
        int localvariable;
        Scanner input1 = new Scanner(System.in);
        localvariable = input1.nextInt();
        if (localvariable == 1) {
            createMap();
        } else if (localvariable == 2) {
            loadMap();
        } else {
            System.out.println("Please enter Relevant option");
        }
    }

    /**
     * This method will perform operation to create a map
     */
    public static void createMap() {
        boolean exit = true;
        while (exit) {
            System.out.println("1. Create Continent");
            System.out.println("2. Create Country");
            System.out.println("3 .Enter multiple countries in single line(using comma separated)");
            System.out.println("4. Add Neighbour");
            System.out.println("5. Exit");
            System.out.println("Enter the task you want to perform :");
            Scanner in = new Scanner(System.in);
            int val = in.nextInt();
            switch (val) {
                case 1:
                    CreateMap.createContinent();
                    break;
                case 2:
                    CreateMap.createCountry();
                    break;
                case 3:
                    CreateMap.multipleCountryInput();
                    break;
                case 4:
                    CountryAdjacencyMatrix.printGraph();
                    System.out.println("Enter -1 to exit any time");
                    CountryAdjacencyMatrix.addNeighbour();
                    CountryAdjacencyMatrix.printGraph();
                    break;
                case 5:
                    CountryAdjacencyMatrix.readFromGraph();
                    if (ValidateMap.validateMap()) {
                        createFile();
                        exit = false;
                    } else {
                        System.out.println("Map is invalid , Please correct the Map");
                    }
                    break;
            }
        }
    }

    /**
     * This method assigns Countries to the players
     */
    public static void assigningCountriesToPlayers() {
        Scanner sc = new Scanner(System.in);

//      Assigning All the Countries to corresponding Players
        for (String countryName : GameModel.countryHashMap.keySet()) {
            int playerId = GameModel.countryHashMap.get(countryName).getPlayerId();
            Player temp = playerHashMap.get(playerId);
            temp.countriesOwned.add(countryName);
        }

        for (int p : playerHashMap.keySet()) {
            playerHashMap.get(p).numberOfInfantry = playerHashMap.get(p).numberOfInfantry - playerHashMap.get(p).countriesOwned.size();
            System.out.println("Countries owned by the player -->" + playerHashMap.get(p).getCountriesOwned() + " Size of infantry after allocating -->" + playerHashMap.get(p).numberOfInfantry + " Total country owned by the player -->" + playerHashMap.get(p).countriesOwned.size());
        }

        while (calculateInfantry() != 0) {
            for (int p : playerHashMap.keySet()) {
                if (playerHashMap.get(p).playerType.equals("human")) {

                    System.out.println("Name of the players :" + playerHashMap.get(p).getName() + " size of remaining infantry :" + playerHashMap.get(p).numberOfInfantry + " number of country owned" + playerHashMap.get(p).countriesOwned.size());
                    int i = 0;
                    if (playerHashMap.get(p).numberOfInfantry != 0) {
                        for (String countryName : playerHashMap.get(p).getCountriesOwned()) {
                            System.out.println(i + ":" + countryName + "->" + GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                            i = i + 1;
                        }

                        System.out.println("Enter the number of armies to be allocated :");
                        int numOfArmies = sc.nextInt();
                        System.out.println("Enter the serial number of the country");
                        int countrySerialNum = sc.nextInt();
                        addInfantryToCountry(playerHashMap.get(p).countriesOwned.get(countrySerialNum), p, numOfArmies);
                        playerHashMap.get(p).numberOfInfantry = playerHashMap.get(p).numberOfInfantry - numOfArmies;
                    }
                } else {
                    assigningCountriesToPlayersAutoCustom(p);
                }
            }
        }
    }

    /**
     * This method is used to auto assign countries to Players
     *
     * @param playerId, id of the player
     */
    public static void assigningCountriesToPlayersAutoCustom(int playerId) {


        while (playerHashMap.get(playerId).numberOfInfantry != 0) {
            Random rand = new Random();
            System.out.println("player id: " + playerId + " number of infantry: " + playerHashMap.get(playerId).numberOfInfantry);

//                    System.out.println("Enter the number of armies to be allocated :");
            int numOfArmies = rand.nextInt(playerHashMap.get(playerId).numberOfInfantry) + 1;
//                    System.out.println("Enter the serial number of the country");
            int countrySerialNum = rand.nextInt(playerHashMap.get(playerId).getCountriesOwned().size());
            addInfantryToCountry(playerHashMap.get(playerId).countriesOwned.get(countrySerialNum), playerId, numOfArmies);
            playerHashMap.get(playerId).numberOfInfantry = playerHashMap.get(playerId).numberOfInfantry - numOfArmies;
        }

    }


    /**
     * TODO : Test this function
     * This method Auto assigns Countries to the players
     */
    public static void assigningCountriesToPlayersAuto() {
//        Scanner sc = new Scanner(System.in);

//      Assigning All the Countries to corresponding Players
        for (String countryName : GameModel.countryHashMap.keySet()) {
            int playerId = GameModel.countryHashMap.get(countryName).getPlayerId();
            Player temp = playerHashMap.get(playerId);
            temp.countriesOwned.add(countryName);
        }

        for (int p : playerHashMap.keySet()) {
            playerHashMap.get(p).numberOfInfantry = playerHashMap.get(p).numberOfInfantry - playerHashMap.get(p).countriesOwned.size();
            System.out.println("Countries owned by the player -->" + playerHashMap.get(p).getCountriesOwned() + " Size of infantry after allocating -->" + playerHashMap.get(p).numberOfInfantry + " Total country owned by the player -->" + playerHashMap.get(p).countriesOwned.size());
        }


        while (calculateInfantry() != 0) {
            for (int p : playerHashMap.keySet()) {
                System.out.println("Name of the players :" + playerHashMap.get(p).getName() + " size of remaining infantry :" + playerHashMap.get(p).numberOfInfantry + " number of country owned" + playerHashMap.get(p).countriesOwned.size());
                int i = 0;
                if (playerHashMap.get(p).numberOfInfantry != 0) {
                    for (String countryName : playerHashMap.get(p).getCountriesOwned()) {
                        System.out.println(i + ":" + countryName + "->" + GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                        i = i + 1;
                    }
                    Random rand = new Random();


//                    System.out.println("Enter the number of armies to be allocated :");
                    int numOfArmies = rand.nextInt(playerHashMap.get(p).numberOfInfantry) + 1;
//                    System.out.println("Enter the serial number of the country");
                    int countrySerialNum = rand.nextInt(playerHashMap.get(p).getCountriesOwned().size());
                    addInfantryToCountry(playerHashMap.get(p).countriesOwned.get(countrySerialNum), p, numOfArmies);
                    playerHashMap.get(p).numberOfInfantry = playerHashMap.get(p).numberOfInfantry - numOfArmies;
                }
            }
        }
    }


    /**
     * This method calculates infantry
     *
     * @return calculateInfanrty, int
     */
    public static int calculateInfantry() {
        int calculateInfanrty = 0;
        for (int p : playerHashMap.keySet()) {
            calculateInfanrty = calculateInfanrty + playerHashMap.get(p).numberOfInfantry;
        }
        return calculateInfanrty;
    }

    /**
     * This method adds infantry to the Model.Country
     *
     * @param countryName,   name of the country
     * @param infantryNumber , infantry number
     * @param playerId,      player Id
     */
    public static void addInfantryToCountry(String countryName, int playerId, int infantryNumber) {
        GameModel.countryHashMap.get(countryName).setPlayerId(playerId);
        int newVal = GameModel.countryHashMap.get(countryName).getNumberOfSoldiers() + infantryNumber;
        GameModel.countryHashMap.get(countryName).setNumberOfSoldiers(newVal);
    }

    /**
     * This method will perform operations to edit a loaded map
     */
    public static void editMap() {
        boolean exit = true;
        while (exit) {
            System.out.println("1. Create Continent");
            System.out.println("2. Create Country");
            System.out.println("3. Add Neighbour");
//            System.out.println("4. Delete Country");
//            System.out.println("5. Delete Continent");
            System.out.println("4. Exit");
            System.out.println("Enter the task you want to perform :");
            Scanner in = new Scanner(System.in);
            int val = in.nextInt();
            switch (val) {
                case 1:
                    CreateMap.createContinent();
                    break;
                case 2:
                    CreateMap.createCountry();
                    break;
                case 3:
                    CountryAdjacencyMatrix.printGraph();
                    CountryAdjacencyMatrix.addNeighbour();
                    CountryAdjacencyMatrix.printGraph();
                    break;
               /* case 4:
                    createMap.removeCountry(); *//*TODO : Test this*//*
                    break;
                case 5:
                    createMap.removeContinent(); *//* TODO : test this*//*
                    break;*/
                case 4:
                    CountryAdjacencyMatrix.readFromGraph();
                    if (ValidateMap.validateMap()) {
                        createFile();
                        exit = false;
                    } else {
                        System.out.println("Map is invalid , Please correct the Map");
                    }
                    break;
            }

        }
    }

    /**
     * This method is used to initialise the players into the game
     */
    public static void initialisePlayers() {
        System.out.println("Number of Players who want to play (2 - 6 players allowed):");
        Scanner input = new Scanner(System.in);
        boolean a = true;
        while (a) {
            int x = input.nextInt();
            if (x > 1 && x < 7) {
                GameModel.playerNumber = x;
                for (int i = 0; i < GameModel.playerNumber; i++) {
                    System.out.println("Player " + (i + 1) + " name :");
                    String name = input.next();
                    System.out.println("Player " + (i + 1) + " type (human) and (aggressive) or cheater");
                    String type = input.next();
                    Player play = new Player(i, name, type);
                    GameModel.PlayerList.add(play);
                    playerHashMap.put(i, play);
                }
                break;
            } else {
                System.out.println("Please enter Valid number of players !");
            }
        }
    }

    /**
     * This method is used to initialise player for Tournament
     *
     * @param number,    integer
     * @param nameArray  , String array
     * @param typeArray, String array
     */
    public static void initialisePlayerForTournament(int number, String[] nameArray, String[] typeArray) {
        GameModel.PlayerList = new ArrayList<Player>();
        playerHashMap = new HashMap<>();
        for (int i = 0; i < number; i++) {
            Player play = new Player(i, nameArray[i], typeArray[i]);
            GameModel.PlayerList.add(play);
            playerHashMap.put(i, play);
        }
    }

    /**
     * Assigns the current country to player
     */
    public static void assigningCountries() {
        Random numberGenerator = new Random();
        for (int countryId : GameModel.countryIdHashMap.keySet()) {
            //for (int i = 0; i < GameModel.PlayerList.size(); i++) {
            int playerId = numberGenerator.nextInt(GameModel.PlayerList.size());
            String countryName = GameModel.countryIdHashMap.get(countryId);
            if (GameModel.countryHashMap.get(countryName).getPlayerId() == null) {
                GameModel.countryHashMap.get(countryName).setPlayerId(playerId);
                GameModel.countryHashMap.get(countryName).setNumberOfSoldiers(1);
            }
        }
        for (int j : GameModel.countryIdHashMap.keySet()) {
            String countryName = GameModel.countryIdHashMap.get(j);
            System.out.println(j + ":" + " Player id -->" + GameModel.countryHashMap.get(countryName).getPlayerId() + " Country name owned by the player is  -->" + countryName + " Initial army allocated in the country -->" + GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
        }
    }

    /**
     * This method is used to intialise infantary of the player
     */
    public static void initialisationInfantry() {
        Player play = new Player();
        play.GamePhase = "Initialisation";
        ViewObserver VOb = new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
        if (GameModel.PlayerList.size() == 2) {
            for (int i = 0; i < GameModel.PlayerList.size(); i++) {
                GameModel.PlayerList.get(i).setNumberOfInfantry(40);
            }

        } else if (GameModel.PlayerList.size() == 3) {
            for (int i = 0; i < GameModel.PlayerList.size(); i++) {
                GameModel.PlayerList.get(i).setNumberOfInfantry(35);
            }

        } else if (GameModel.PlayerList.size() == 4) {
            for (int i = 0; i < GameModel.PlayerList.size(); i++) {
                GameModel.PlayerList.get(i).setNumberOfInfantry(30);
            }
        } else if (GameModel.PlayerList.size() == 5) {
            for (int i = 0; i < GameModel.PlayerList.size(); i++) {
                GameModel.PlayerList.get(i).setNumberOfInfantry(25);
            }

        } else if (GameModel.PlayerList.size() == 6) {
            for (int i = 0; i < GameModel.PlayerList.size(); i++) {
                GameModel.PlayerList.get(i).setNumberOfInfantry(20);
            }
        }
    }

    /**
     * This method performs dfs to find neighbouring Country of the player
     *
     * @param countryModel, Country
     * @param p,            Player
     * @return adjacentCountry, List type
     */
    public static List<String> dfsToFindNeighbouringCountryForPlayer(Country countryModel, Player p) {
        List<String> adjacentCountry = new ArrayList<>();
        Stack<Country> stack = new Stack<Country>();
        stack.add(countryModel);
        countryModel.setVisited(true);
        while (!stack.isEmpty()) {
            Country element = stack.pop();
            List<String> neighbourNames = element.getAdjacentCountries();
            List<Country> neighbours = new ArrayList<>();
            for (String neighbourName : neighbourNames) {
                if (GameModel.countryHashMap.get(neighbourName).PlayerId == p.playerId)
                    neighbours.add((GameModel.countryHashMap.get(neighbourName)));
            }
            for (int i = 0; i < neighbours.size(); i++) {
                Country n = neighbours.get(i);
                if (n != null && !n.isVisited()) {
                    stack.add(n);
                    n.setVisited(true);
                }
            }
        }

        for (String countryName : GameModel.countryHashMap.keySet()) {
            if (GameModel.countryHashMap.get(countryName).isVisited()) {
                GameModel.countryHashMap.get(countryName).setVisited(false);
                adjacentCountry.add(countryName);
            }
        }
        return adjacentCountry;
    }

    /**
     * This method show how the cards are earned
     *
     * @param PlayerId, id of the player
     */
    public static void earnRiskCards(int PlayerId) {
        Player play = playerHashMap.get(PlayerId);
        String[] RiskCards = {"infantry", "calvary", "artillery"};
        int RiskCardId;
        RiskCardId = play.Cards.size() % 3;
        play.Cards.add(RiskCards[RiskCardId]);

    }


    /**
     * This method show how the cards are exchanged for armies
     *
     * @param PlayerId, id of the player
     */
    public static void exchangeCardsForArmiesHumanPlayer(int PlayerId) {

        Scanner sc = new Scanner(System.in);
        Player play = playerHashMap.get(PlayerId);
        UpdateFromObserver Ob = new UpdateFromObserver();
        play.addObserver(Ob);
        play.updatingObserver();
        System.out.println("Enter the serial number of the cards to be exchanged(3 cards in a set)");
        int cardIndexFirst = sc.nextInt();
        int cardIndexsecond = sc.nextInt();
        int cardIndexThird = sc.nextInt();
        String cardNameFirst = play.Cards.get(cardIndexFirst);
        String cardNameSecond = play.Cards.get(cardIndexsecond);
        String cardNameThird = play.Cards.get(cardIndexThird);
        if (play.Cards.get(cardIndexFirst) != play.Cards.get(cardIndexsecond) && play.Cards.get(cardIndexsecond) != play.Cards.get(cardIndexThird) && play.Cards.get(cardIndexThird) != play.Cards.get(cardIndexFirst)) {
            play.Cards.remove(cardNameFirst);
            play.Cards.remove(cardNameSecond);
            play.Cards.remove(cardNameThird);
            play.addObserver(Ob);
            play.setArmiesInExcahngeOfcards(play.turn * 5);
            play.numberOfInfantry += play.armiesInExcahngeOfcards;
            play.turn++;
            play.updatingObserver();


            // notifyObservers();
        } else if (play.Cards.get(cardIndexFirst).equals(play.Cards.get(cardIndexsecond)) && play.Cards.get(cardIndexFirst).equals(play.Cards.get(cardIndexThird))) {
            play.Cards.remove(cardNameFirst);
            play.Cards.remove(cardNameSecond);
            play.Cards.remove(cardNameThird);
            play.addObserver(Ob);
            play.setArmiesInExcahngeOfcards(play.turn * 5);
            play.numberOfInfantry += play.armiesInExcahngeOfcards;
            play.turn++;
            play.updatingObserver();
            //notifyObservers();
        }
    }


    /**
     * This method shows the message on GUI
     *
     * @param strMessageOne, String
     */
    public static void mssageOnGUI(String strMessageOne) {
        gui1.printScreen(strMessageOne);
    }

    /**
     * This method indicates the message on GUI through Observer
     *
     * @param strMessageOne, String
     */
    public static void mssageOnGUIThroughObsever(String strMessageOne) {
        gui1.printSecondScreen(strMessageOne);
    }


    public static void loadSavedGame() {
        try {
            boolean readMapName = false;
            boolean readPlayerInfo = false;
            boolean readCountryInfo = false;
            String filePath = "./assets/saved/SavedFile.map";
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine;

            while ((readLine = bufferedReader.readLine()) != null) {
                if (readLine.trim().length() == 0)
                    continue;
                else if ((readLine.trim()).equals("[Map]")) {
                    readMapName = true;
                    readPlayerInfo = false;
                    readCountryInfo = false;
                    continue;
                } else if ((readLine.trim()).equals("[PlayerInformation]")) {
                    readMapName = false;
                    readPlayerInfo = true;
                    readCountryInfo = false;
                    continue;
                } else if ((readLine.trim()).equals("[CountriesInformation]")) {
                    readMapName = false;
                    readPlayerInfo = false;
                    readCountryInfo = true;
                    continue;
                }
                if (readMapName) {
                    String[] info = readLine.split("%");
                    String mapName = info[0];
                    String playerTurn = info[1];
                    tournamentLoadMap(mapName);
                } else if (readPlayerInfo) {
                    String[] parsedPlayerInfo = readLine.split("%");
                    Player a = new Player();
                    a.playerId = Integer.parseInt(parsedPlayerInfo[0]);
                    a.setName(parsedPlayerInfo[1]);
                    a.playerType = parsedPlayerInfo[2];
                    a.alive = (parsedPlayerInfo[3].equals("true") ? true : false);
                    String countriesOwned = parsedPlayerInfo[4];
                    a.numberOfInfantry = Integer.parseInt(parsedPlayerInfo[5]);
                    a.cardBooleanValue = (parsedPlayerInfo[6].equals("true") ? true : false);
                    a.turn = Integer.parseInt(parsedPlayerInfo[7]);
                    a.armiesInExcahngeOfcards = Integer.parseInt(parsedPlayerInfo[8]);
                    a.setShouldGetTheCard(parsedPlayerInfo[9].equals("true") ? true : false);
//                    a.infoAboutAction = parsedPlayerInfo[10];
                    for(String val : countriesOwned.split(",")){
                        if (val.trim().length() != 0) {
                            a.countriesOwned.add(val);
                        }
                    }
                    playerHashMap.put(a.playerId,a);
                } else if (readCountryInfo) {
                    String[] parsedPlayerInfo = readLine.split("%");
                    for (int i = 0; i < parsedPlayerInfo.length; i++) {
                        if (parsedPlayerInfo[i].trim().length() != 0) {
                            String[] countryDetail = parsedPlayerInfo[i].split("=");
                            Country val = countryHashMap.get(countryDetail[0]);
                            val.setPlayerId(Integer.parseInt(countryDetail[1]));
                            val.setNumberOfSoldiers(Integer.parseInt(countryDetail[2]));
                        }
                    }
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}