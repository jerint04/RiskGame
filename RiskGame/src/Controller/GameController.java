package Controller;

import Model.*;
import View.DisplayGuiHelp;

import java.io.File;
import java.util.*;

import static Controller.CreateMapFile.createFile;
import static Model.GameModel.playerHashMap;

/**
 * GameController class
 *
 * @author Hemanshu
 * @version 1.0.0
 */
public class GameController  {
    public  static DisplayGuiHelp gui1 = new DisplayGuiHelp();

    /**
     * This method will load a map
     */
    public static void LoadMap() {
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
     * Calculating armies according to the risk rule that is  calculating using number of territories occupied
     *
     * @param playerId, id of the player
     */
    public static void armyCalculationDuringReinforcement(int playerId) {

        Player play=playerHashMap.get(playerId);
        play.GamePhase="Reinforcement";
        ViewObserver VOb=new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
        Scanner sc = new Scanner(System.in);
        Player temp = playerHashMap.get(playerId);
        if (GameModel.playerHashMap.get(playerId).getShouldGetTheCard()) {
            GameController.earnRiskCards(playerId);
            GameModel.playerHashMap.get(playerId).setShouldGetTheCard(false);
        }
        int armyToAllocate = playerHashMap.get(playerId).countriesOwned.size() / 3;
        playerHashMap.get(playerId).numberOfInfantry = +((armyToAllocate < 3) ? 3 : armyToAllocate);
        for (String key : GameModel.continentHashMap.keySet()) {
            Continent tempContinent = GameModel.continentHashMap.get(key);
            if (temp.countriesOwned.containsAll(tempContinent.Countries)) {
                playerHashMap.get(playerId).numberOfInfantry = +tempContinent.controlValue;
            }
        }

        if (temp.Cards.size() == 3) {

            System.out.println("You have 3 cards, press 1 to exchange cards for armies");
            int choice = sc.nextInt();
            if (choice == 1) {
                exchangeCardsForArmies(playerId);
            }

        } else if (temp.Cards.size() >= 5) {
            System.out.println("You have to exchange cards to proceed");
            exchangeCardsForArmies(playerId);

        }
        Player playerView=new Player();
        PlayerDominationObserverView POb=new PlayerDominationObserverView();
        playerView.addObserver(POb);
        playerView.updatingObserver();
    }

    /**
     * Placing the armies during reinforcement
     *
     * @param playerId, id of the player
     */
    public static void armyPlacementDuringReinforcement(int playerId) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Infantry for the current players is : " + playerHashMap.get(playerId).numberOfInfantry);

        while (playerHashMap.get(playerId).numberOfInfantry != 0) {
            int i = 0;
            for (String countryName : playerHashMap.get(playerId).getCountriesOwned()) {
                System.out.println(i + ":" + countryName + "->" + GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                i = i + 1;
            }
            System.out.println("enter the number of armies to be allocated and the serial number of the country");
            int numOfArmies = sc.nextInt();
            int countrySerialNum = sc.nextInt();
            addInfantryToCountry(playerHashMap.get(playerId).countriesOwned.get(countrySerialNum), playerId, numOfArmies);
            playerHashMap.get(playerId).numberOfInfantry = playerHashMap.get(playerId).numberOfInfantry - numOfArmies;
        }
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
                    CreateMap.removeCountry(); *//*TODO : Test this*//*
                    break;
                case 5:
                    CreateMap.removeContinent(); *//* TODO : test this*//*
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
        System.out.println("Number of Players who want to play (3 - 6 players allowed):");
        Scanner input = new Scanner(System.in);
        boolean a = true;
        while (a) {
            int x = input.nextInt();
            if (x > 2 && x < 7) {
                GameModel.playerNumber = x;
                for (int i = 0; i < GameModel.playerNumber; i++) {
                    System.out.println("Player " + (i + 1) + " name :");
                    String name = input.next();
                    Player play = new Player(i, name);
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
        Player play=new Player();
        play.GamePhase="Initialisation";
        ViewObserver VOb=new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
        if (GameModel.PlayerList.size() == 3) {
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
     * The method is used to perform fortification Phase
     * @param playerId, id of the player
     */
    public static void fortificationPhase(int playerId) {
        Player play=playerHashMap.get(playerId);
        play.GamePhase="Fortification";
        ViewObserver VOb=new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
        for (String countryName : GameModel.countryHashMap.keySet()) {
            if (GameModel.countryHashMap.get(countryName).getPlayerId() == playerId) {
                System.out.println(countryName + "->" + GameModel.countryHashMap.get(countryName).numberOfSoldiers);
            }
        }
        System.out.println("Press -1 to skip the step (or any other number to continue):");
        Scanner a = new Scanner(System.in);
        int escape = a.nextInt();
        boolean temp = true;
        if (escape != (-1)) {
            while (temp) {
                System.out.println("Enter Country Name to move from or enter (-1) to exit:");
                String countryFrom = a.nextLine();
                String countryTo;
                List<String> toCountriesList = new ArrayList<>();
                toCountriesList = dfsToFindNeighbouringCountryForPlayer(GameModel.countryHashMap.get(countryFrom), playerHashMap.get(playerId));
                if (toCountriesList.size() != 1) {
                    System.out.println("Number of army to move:");
                    int armyToMove = a.nextInt();
                    if (armyToMove > 0 && armyToMove < GameModel.countryHashMap.get(countryFrom).numberOfSoldiers) {
                        for (String cont : toCountriesList) {
                            System.out.println(cont);
                        }
                        System.out.println("Write Country Name  :");
                        Scanner b = new Scanner(System.in);
                        countryTo = b.nextLine();
                        GameModel.countryHashMap.get(countryFrom).numberOfSoldiers -= armyToMove;
                        GameModel.countryHashMap.get(countryTo).numberOfSoldiers += armyToMove;
                        break;
                    }
                } else {
                    System.out.println("The Countries do not have any Adjacent country, Please Select other Country :");
                }
            }
            System.out.println("Updated List After Fortification");
            for (String countryName : GameModel.countryHashMap.keySet()) {
                if (GameModel.countryHashMap.get(countryName).getPlayerId() == playerId) {
                    System.out.println(countryName + "->" + GameModel.countryHashMap.get(countryName).numberOfSoldiers);
                }
            }
        }
    }

    /**
     * This method performs dfs to find neighbouring Country of the player
     * @param countryModel, Country
     * @param p, Player
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
     * @param PlayerId, id of the player
     */
    public static void exchangeCardsForArmies(int PlayerId) {

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
     * @param strMessageOne, String
     */
    public static void MssageOnGUI(String strMessageOne){
        gui1.printScreen(strMessageOne);
    }

    /**
     * This method indicates the message on GUI through Observer
     * @param strMessageOne, String
     */
    public static void MssageOnGUIThroughObsever(String strMessageOne){
        gui1.printSecondScreen(strMessageOne);
    }


}