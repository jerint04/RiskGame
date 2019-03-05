package Controller;


import Model.Continent;
import Model.CountryAdjacencyMatrix;
import Model.GameModel;
import Model.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Hemanshu
 * @date 2019-03-05
 */
public class GameController {

    /**
     * This method will load a map
     */
    public static void LoadMap() {
        Scanner sc = new Scanner(System.in);
        List<String> getFileName = new ArrayList<String>();
        //File[] filesName = new File("./assets/maps").listFiles();
        File[] filesName = new File(System.getProperty("user.dir") + "/RiskGame/assets/maps").listFiles();

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
        String currentDirectory = System.getProperty("user.dir");
        // Controller.ReadMap.readMap("./assets/maps/" + mapName + ".map");
        if (ReadMap.readMap(currentDirectory + "/RiskGame/assets/maps/" + mapName + ".map")) {
            ValidateMap.validateMap();
        } else {
            System.out.println("Not able to read map successfully. Please check you Map Format");
        }

    }

    /**
     * This method will perform operation required to start or load a game
     */
    public static void StartOrLoadGame() {
        System.out.println("Select 1 to Start the game and create map, Select 2 to Load the Game :");
        int localvariable;
        Scanner input1 = new Scanner(System.in);
        localvariable = input1.nextInt();
        if (localvariable == 1) {
            CreateMap();
        } else if (localvariable == 2) {
            LoadMap();
        } else {
            System.out.println("Please enter Relevant option");
        }
    }

    /**
     * This method will perform operation to create a map
     */
    public static void CreateMap() {
        boolean exit = true;
        while (exit) {
            System.out.println("1. Create Model.Continent");
            System.out.println("2. Create Model.Country");
            System.out.println("3. Add Neighbour");
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
                case 4:
                    CreateMap.removeCountry(); /*TODO : Test this*/
                    break;
                case 5:
                    CreateMap.removeContinent(); /* TODO : test this*/
                    break;
                case 6:
                    /*TODO Validate the graph ..... Need to test this*/
                    if (ValidateMap.validateMap()) {
                        CountryAdjacencyMatrix.readFromGraph();
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

        for (String countryName : GameModel.countryHashMap.keySet()) {
            int playerId = GameModel.countryHashMap.get(countryName).getPlayerId();
            Player temp = GameModel.playerHashMap.get(playerId);
            temp.countriesOwned.add(countryName);
        }

        for (int p : GameModel.playerHashMap.keySet()) {
            GameModel.playerHashMap.get(p).numberOfInfantry = GameModel.playerHashMap.get(p).numberOfInfantry - GameModel.playerHashMap.get(p).countriesOwned.size();
            System.out.println(GameModel.playerHashMap.get(p).getCountriesOwned() + " size of infantory " + GameModel.playerHashMap.get(p).numberOfInfantry + " country owned" + GameModel.playerHashMap.get(p).countriesOwned.size());
        }


        while (calculateInfantry() != 0) {
            for (int p : GameModel.playerHashMap.keySet()) {
                System.out.println("Name of the players :" + GameModel.playerHashMap.get(p).getName() + " size of remaining infantory :" + GameModel.playerHashMap.get(p).numberOfInfantry + " number of country owned" + GameModel.playerHashMap.get(p).countriesOwned.size());
                // playerHashMap.get(p).numberOfInfantry=playerHashMap.get(p).numberOfInfantry-playerHashMap.get(p).countriesOwned.size();
                int i = 0;
                if (GameModel.playerHashMap.get(p).numberOfInfantry != 0) {
                    for (String countryName : GameModel.playerHashMap.get(p).getCountriesOwned()) {
                        System.out.println(i + ":" + countryName);
                        i = i + 1;
                    }

                    System.out.println("enter the number of armies to be allocated and the serial number of the country");
                    int numOfArmies = sc.nextInt();
                    int countrySerialNum = sc.nextInt();
                    addInfantoryToCountry(GameModel.playerHashMap.get(p).countriesOwned.get(countrySerialNum), p, numOfArmies);
                    GameModel.playerHashMap.get(p).numberOfInfantry = GameModel.playerHashMap.get(p).numberOfInfantry - numOfArmies;
                }
            }
        }
    }

    /**
     * This method calculates infantry
     */
    public static int calculateInfantry() {
        int calculateInfanrty = 0;
        for (int p : GameModel.playerHashMap.keySet()) {
            calculateInfanrty = calculateInfanrty + GameModel.playerHashMap.get(p).numberOfInfantry;
        }
        return calculateInfanrty;
    }

    /**
     * This method adds infantry to the Model.Country
     */
    public static void addInfantoryToCountry(String countryName, int playerId, int infantoryNumber) {
        GameModel.countryHashMap.get(countryName).setPlayerId(playerId);
        GameModel.countryHashMap.get(countryName).setNumberOfSoldiers(infantoryNumber);
    }

    /**
    *Calculating armies according to the risk rule that is  calculating using number of territories occupied
    */
    public static void armyCalculationDuringReinforcement(int playerId) {
        Scanner sc = new Scanner(System.in);
        Player temp = GameModel.playerHashMap.get(playerId);
        GameModel.playerHashMap.get(playerId).numberOfInfantry = +(GameModel.playerHashMap.get(playerId).countriesOwned.size() / 3);
        for (String key : GameModel.continentHashMap.keySet()) {
            Continent tempContinent = GameModel.continentHashMap.get(key);
            if (temp.countriesOwned.containsAll(tempContinent.Countries)) {
                GameModel.playerHashMap.get(playerId).numberOfInfantry = +tempContinent.controlValue;
            }
        }


    }

    public static void armyPlacementDuringReinforcemet(int playerId) {
        Scanner sc=new Scanner(System.in);
        System.out.println(" Infantory for the current players is : " + GameModel.playerHashMap.get(playerId).numberOfInfantry);

        while (GameModel.playerHashMap.get(playerId).numberOfInfantry != 0) {
            int i = 0;
            for (String countryName : GameModel.playerHashMap.get(playerId).getCountriesOwned()) {
                System.out.println(i + ":" + countryName);
                i = i + 1;
            }
            System.out.println("enter the number of armies to be allocated and the serial number of the country");
            int numOfArmies = sc.nextInt();
            int countrySerialNum = sc.nextInt();
            addInfantoryToCountry(GameModel.playerHashMap.get(playerId).countriesOwned.get(countrySerialNum), playerId, numOfArmies);
            GameModel.playerHashMap.get(playerId).numberOfInfantry = GameModel.playerHashMap.get(playerId).numberOfInfantry - numOfArmies;
        }
    }

    /**
     * This method will perform operations to edit a loaded map
     */
    public static void EditMap() {
        boolean exit = true;
        while (exit) {
            System.out.println("1. Create Model.Continent");
            System.out.println("2. Create Model.Country");
            System.out.println("3. Add Neighbour");
            System.out.println("4. Delete Model.Country");
            System.out.println("5. Delete Model.Continent");
            System.out.println("6. Exit");
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
                case 4:
                    CreateMap.removeCountry(); /*TODO : Test this*/
                    break;
                case 5:
                    CreateMap.removeContinent(); /* TODO : test this*/
                    break;
                case 6:
                    /*TODO Validate the graph ..... Need to test this*/
                    if (ValidateMap.validateMap()) {
                        CountryAdjacencyMatrix.readFromGraph();
                        exit = false;
                    } else {
                        System.out.println("Map is invalid , Please correct the Map");
                    }
                    break;
            }

        }
    }

    /**
     * This method is used to intialise the players into the game
     */
    public static void InitialisePlayers() {
        System.out.println("Number of Players who want to play :");
        Scanner input = new Scanner(System.in);
        GameModel.playerNumber = input.nextInt();


        for (int i = 0; i < GameModel.playerNumber; i++) {
            System.out.println("Model.Player " + (i + 1) + " name :");
            String name = input.next();
            Player play = new Player(i, name);
            GameModel.PlayerList.add(play);
            GameModel.playerHashMap.put(i, play);

        }


    }
}
