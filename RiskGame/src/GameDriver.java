import java.io.File;
import java.util.*;

/**
 * GameDriver Class
 *
 * @author Hemanshu
 * @version 1.0.0
 * @date 2019-02-22
 * Includes main() method of the program
 */

public class GameDriver {
    static int playerNumber;
    static ArrayList<Player> PlayerList = new ArrayList<Player>();
    static HashMap<Integer, Player> playerHashMap = new HashMap<>();


    /**
     * This method is used to intialise the players into the game
     */
    public static void InitialisePlayers() {
        System.out.println("Number of Players who want to play :");
        Scanner input = new Scanner(System.in);
        playerNumber = input.nextInt();


        for (int i = 0; i < playerNumber; i++) {
            System.out.println("Player " + (i + 1) + " name :");
            String name = input.next();
            Player play = new Player(i, name);
            PlayerList.add(play);
            playerHashMap.put(i, play);

        }


    }

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
        // ReadMap.readMap("./assets/maps/" + mapName + ".map");
        ReadMap.readMap(currentDirectory + "/RiskGame/assets/maps/" + mapName + ".map");

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
                    GraphNew.printGraph();
                    GraphNew.addNeighbour();
                    GraphNew.printGraph();
                    break;
                case 4:
                    GraphNew.readFromGraph();
                    exit = false;
                    break;
            }

        }
    }

    /**
     * This method assigns Countries to the players
     */
    public static void assigningCountriesToPlayers() {
        Scanner sc = new Scanner(System.in);

        for (String countryName : CreateMap.countryHashMap.keySet()) {
            int playerId = CreateMap.countryHashMap.get(countryName).getPlayerId();
            Player temp = playerHashMap.get(playerId);
            temp.countriesOwned.add(countryName);
        }

        for (int p : playerHashMap.keySet()) {
            playerHashMap.get(p).numberOfInfantary = playerHashMap.get(p).numberOfInfantary - playerHashMap.get(p).countriesOwned.size();
            System.out.println(playerHashMap.get(p).getCountriesOwned() + " size of infantory " + playerHashMap.get(p).numberOfInfantary + " country owned" + playerHashMap.get(p).countriesOwned.size());
        }


        while (calculateInfantry() != 0) {
            for (int p : playerHashMap.keySet()) {
                System.out.println("Name of the players :" + playerHashMap.get(p).getName() + " size of remaining infantory :" + playerHashMap.get(p).numberOfInfantary + " number of country owned" + playerHashMap.get(p).countriesOwned.size());
                // playerHashMap.get(p).numberOfInfantary=playerHashMap.get(p).numberOfInfantary-playerHashMap.get(p).countriesOwned.size();
                int i = 0;
                if (playerHashMap.get(p).numberOfInfantary != 0) {
                    for (String countryName : playerHashMap.get(p).getCountriesOwned()) {
                        System.out.println(i + ":" + countryName);
                        i = i + 1;
                    }

                    System.out.println("enter the number of armies to be allocated and the serial number of the country");
                    int numOfArmies = sc.nextInt();
                    int countrySerialNum = sc.nextInt();
                    addInfantoryToCountry(playerHashMap.get(p).countriesOwned.get(countrySerialNum), p, numOfArmies);
                    playerHashMap.get(p).numberOfInfantary = playerHashMap.get(p).numberOfInfantary - numOfArmies;
                }
            }
        }
    }

    /**
     * This method calculates infantry
     */
    public static int calculateInfantry() {
        int calculateInfanrty = 0;
        for (int p : playerHashMap.keySet()) {
            calculateInfanrty = calculateInfanrty + playerHashMap.get(p).numberOfInfantary;
        }
        return calculateInfanrty;
    }

    /**
     * This method adds infantry to the Country
     */
    public static void addInfantoryToCountry(String countryName, int playerId, int infantoryNumber) {
        CreateMap.countryHashMap.get(countryName).setPlayerId(playerId);
        CreateMap.countryHashMap.get(countryName).setNumberOfSoldiers(infantoryNumber);
    }

    /**
     * This method performs reinforcement Phase
     */
    public static void reinforcementPhase() {

        for (int k : playerHashMap.keySet()) {

            playerHashMap.get(k).numberOfInfantary = (playerHashMap.get(k).countriesOwned.size() / 3);
        }

    }

    /*
    Calculating armies according to the risk rule that is  calculating using number of territories occupied

     */
    public static void armyCalculationDuringReinforcement(int playerId) {
        Scanner sc = new Scanner(System.in);
        Player temp = playerHashMap.get(playerId);
        playerHashMap.get(playerId).numberOfInfantary = +(playerHashMap.get(playerId).countriesOwned.size() / 3);
        for (String key : CreateMap.continentHashMap.keySet()) {
            Continent tempContinent = CreateMap.continentHashMap.get(key);
            if (temp.countriesOwned.containsAll(tempContinent.Countries)) {
                playerHashMap.get(playerId).numberOfInfantary = +tempContinent.controlValue;
            }
        }


    }

    public static void armyPlacementDuringReinforcemet(int playerId) {
        Scanner sc=new Scanner(System.in);
        System.out.println(" Infantory for the current players is : " + playerHashMap.get(playerId).numberOfInfantary);

        while (playerHashMap.get(playerId).numberOfInfantary != 0) {
            int i = 0;
            for (String countryName : playerHashMap.get(playerId).getCountriesOwned()) {
                System.out.println(i + ":" + countryName);
                i = i + 1;
            }
            System.out.println("enter the number of armies to be allocated and the serial number of the country");
            int numOfArmies = sc.nextInt();
            int countrySerialNum = sc.nextInt();
            addInfantoryToCountry(playerHashMap.get(playerId).countriesOwned.get(countrySerialNum), playerId, numOfArmies);
            playerHashMap.get(playerId).numberOfInfantary = playerHashMap.get(playerId).numberOfInfantary - numOfArmies;
        }
    }

    /**
     * This is the main() method of the program
     * Entry point of the Execution of the whole program
     */

    public static void main(String[] args) {
        InitialisePlayers();
        StartOrLoadGame();
        Player.initialisationInfantory();
        Player.assigningCountries();
        assigningCountriesToPlayers();
    }
}
