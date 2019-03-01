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
    public static Scanner sc = new Scanner(System.in);


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

    public static void LoadMap() {
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


    public static void assigningCountriesToPlayers() {

        for (String countryName : CreateMap.countryHashMap.keySet()) {
            int playerId = CreateMap.countryHashMap.get(countryName).getPlayerId();
            Player temp = playerHashMap.get(playerId);
            temp.countriesOwned.add(countryName);
        }

        for (int p : playerHashMap.keySet()) {
            playerHashMap.get(p).numberOfInfantary=playerHashMap.get(p).numberOfInfantary-playerHashMap.get(p).countriesOwned.size();
            System.out.println(playerHashMap.get(p).getCountriesOwned() + " size of infantory "+playerHashMap.get(p).numberOfInfantary+ " country owned" +playerHashMap.get(p).countriesOwned.size());
        }

        for(int p : playerHashMap.keySet()){
            while(playerHashMap.get(p).numberOfInfantary != 0 ){
                int size= playerHashMap.get(p).countriesOwned.size();
                Random numberGenerator = new Random();
                int number = numberGenerator.nextInt((size));
                String countryNameToIncrementArmy = playerHashMap.get(p).countriesOwned.get(number);
                CreateMap.countryHashMap.get(countryNameToIncrementArmy).numberOfSoldiers++;
                playerHashMap.get(p).numberOfInfantary--;
            }

        }

        for (int p : playerHashMap.keySet()) {
            playerHashMap.get(p).numberOfInfantary=playerHashMap.get(p).numberOfInfantary-playerHashMap.get(p).countriesOwned.size();
            System.out.println(playerHashMap.get(p).getCountriesOwned() + " size of infantory "+playerHashMap.get(p).numberOfInfantary+ " country owned" +playerHashMap.get(p).countriesOwned.size());
        }

        for (int j = 1; j <= CreateMap.countryIdHashMap.size(); j++) {

            String countryName = CreateMap.countryIdHashMap.get(j);

            System.out.println("player details :" + j + CreateMap.countryHashMap.get(countryName).getPlayerId() + " country name :" + countryName + " army count :" + CreateMap.countryHashMap.get(countryName).getNumberOfSoldiers());
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
