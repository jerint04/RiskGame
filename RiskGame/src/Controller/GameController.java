package Controller;

import Model.*;

import java.io.File;
import java.util.*;

import static Controller.CreateMapFile.createFile;
import static Model.GameModel.playerHashMap;

/**
 * GameController class
 * @author Hemanshu
 * @version 1.0.0
 */
public class GameController {

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
     * @param countryName, name of the country
     * @param infantryNumber , infantry number
     * @param playerId, player Id
     */
    public static void addInfantryToCountry(String countryName, int playerId, int infantryNumber) {
        GameModel.countryHashMap.get(countryName).setPlayerId(playerId);
        int newVal = GameModel.countryHashMap.get(countryName).getNumberOfSoldiers() + infantryNumber;
        GameModel.countryHashMap.get(countryName).setNumberOfSoldiers(newVal);
    }

    /**
     * Calculating armies according to the risk rule that is  calculating using number of territories occupied
     */
    public static void armyCalculationDuringReinforcement(int playerId) {
        Player temp = playerHashMap.get(playerId);
        int armyToAllocate = playerHashMap.get(playerId).countriesOwned.size() / 3;
        playerHashMap.get(playerId).numberOfInfantry = +((armyToAllocate < 3) ? 3 : armyToAllocate);
        for (String key : GameModel.continentHashMap.keySet()) {
            Continent tempContinent = GameModel.continentHashMap.get(key);
            if (temp.countriesOwned.containsAll(tempContinent.Countries)) {
                playerHashMap.get(playerId).numberOfInfantry = +tempContinent.controlValue;
            }
        }
    }

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
    public static void EditMap() {
        boolean exit = true;
        while (exit) {
            System.out.println("1. Create Continent");
            System.out.println("2. Create Country");
            System.out.println("3. Add Neighbour");
            System.out.println("4. Delete Country");
            System.out.println("5. Delete Continent");
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
     * This method is used to initialise the players into the game
     */
    public static void InitialisePlayers() {
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


    public static void fortificationPhase(int playerId) {
        for (String countryName : GameModel.countryHashMap.keySet()) {
            if (GameModel.countryHashMap.get(countryName).getPlayerId() == playerId) {
                System.out.println(countryName + "->" + GameModel.countryHashMap.get(countryName).numberOfSoldiers);
            }
        }
        System.out.println("Move Army from the available countries or type(skip) to skip this step:");
        Scanner a = new Scanner(System.in);
        boolean temp = true;

        while (temp) {
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
                    System.out.println("Country to Move to From Above List :");
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


}
