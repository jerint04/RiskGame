import java.util.Arrays;
import java.util.*;

/**
 * CreateMap Class
 *
 * @author Vikram
 * @version 1.0.0
 * @date 2019-02-20
 */
public class CreateMap {

    static ArrayList<String> ContinentList = new ArrayList<String>();
    static HashMap<String, Continent> continentHashMap = new HashMap<>();
    static HashMap<Integer, String> continentIdHashMap = new HashMap<>(); //THis is redundant we might not use it. just created for timebeing

    static ArrayList<String> CountryList = new ArrayList<String>(); // This is redundant we might not use it. just created for timebeing
    static HashMap<Integer, String> countryIdHashMap = new HashMap<>();
    static HashMap<String, Country> countryHashMap = new HashMap<>();

    /**
     * This method creates Continent
     */
    public static void createContinent() {
        System.out.println("Please Enter the Continent Name :");
        Scanner input = new Scanner(System.in);
        String continentName = input.nextLine();
        System.out.println("Please Enter Control Value :");
        int controlValue = input.nextInt();
        Continent temp = new Continent(continentName, controlValue);
        ContinentList.add(continentName);
        continentHashMap.put(continentName, temp);
    }

    /**
     * This method deletes the country
     */
    /*todo : test this function thoroughly &&&   THROw THE ERRORS FOR THIS ONE AND return THE BOOLEAN to know if successfully  deleted or not*/
    public static void removeCountry() {
        System.out.println("Select the Country number to remove from the list:");
        for (int key : CreateMap.countryIdHashMap.keySet()) {
            System.out.println(key + " " + CreateMap.countryIdHashMap.get(key));
        }
        Scanner input = new Scanner(System.in);
        int countryKey = input.nextInt();
        String countryNameToDelete = CreateMap.countryIdHashMap.get(countryKey);
        Country toDelete = CreateMap.countryHashMap.get(countryNameToDelete);
        String continentBelongingTo = toDelete.getParentContinent();
        CreateMap.countryHashMap.remove(countryNameToDelete);
        CreateMap.countryIdHashMap.remove(countryKey);
        /*Removing the country from the continent*/
        CreateMap.ContinentList.remove(countryNameToDelete); /*todo : this line seems to be wrong*/
        toDelete = null;
        CreateMap.continentHashMap.get(continentBelongingTo).Countries.remove(countryNameToDelete);
        // Removing the country from the adjacent countries list
        for (String key : CreateMap.countryHashMap.keySet()) {
            if (CreateMap.countryHashMap.get(key).getAdjacentCountries().contains(countryNameToDelete)) {
                CreateMap.countryHashMap.get(key).getAdjacentCountries().remove(countryNameToDelete);
            }
        }
    }


    /**
     * This method deletes the Continent
     */
    public static void removeContinent() {
        System.out.println("Write the name of the Continent you want to delete:");
        for (String key : CreateMap.ContinentList) {
            System.out.println(key);
        }
        Scanner input = new Scanner(System.in);
        String continent = input.nextLine();
        if (CreateMap.continentHashMap.containsKey(continent)) {
            if (CreateMap.continentHashMap.get(continent).Countries.isEmpty()) {
                CreateMap.continentHashMap.remove(continent);
                CreateMap.ContinentList.remove(continent);
                System.out.println("Successfully Deleted Continent :" + continent);
            } else {
                System.out.println("Can not delete the Continent, need to delete the countries in the Continent first");
                for (String countries : CreateMap.continentHashMap.get(continent).getCountries()) {
                    System.out.println(countries);
                }
            }
        } else {
            System.out.println("Please enter correct Continent");
        }
    }

    /**
     * This method creates Country
     */
    public static void createCountry() {
        System.out.println("Please Enter the Country Name :");
        Scanner input = new Scanner(System.in);
        String countryName = input.nextLine();
        System.out.println("Please Enter Continent it Belongs :");
        String continentBelongsTo = input.nextLine();
        int id = Helper.getNewCountryCountId();
        Country temp = new Country(id, countryName, continentBelongsTo);
        CountryList.add(countryName);
        countryIdHashMap.put(id, countryName);
        countryHashMap.put(countryName, temp);
        addCountriesInContinent(countryName, continentBelongsTo);
    }

    /**
     * This method adds adjacent neighbour Countries using x,y coordinates
     *
     * @param i, x-coordinate
     * @param j, y-coordinate
     */
    public static void addAdjacentNeighbourCountriesUsingCoordinates(int i, int j) {
        String countryXname = countryIdHashMap.get(i);
        String countryYname = countryIdHashMap.get(j);
        Country countryX = countryHashMap.get(countryXname);
//        Country countryY = countryHashMap.get(countryYname);
        countryX.addAdjacentCountry(countryYname);
//        countryY.addAdjacentCountry(countryXname);
    }

    /**
     * This method adds adjacent neighbour Countries using their names
     *
     * @param Source, String
     * @param Destination, String
     */
    public static void addAdjacentNeighbourCountriesUsingNames(String Source, String Destination) {
//        String countryXname = countryIdHashMap.get(i);
//        String countryYname =  countryIdHashMap.get(j);
        Country countryX = countryHashMap.get(Source);
//        Country countryY = countryHashMap.get(Destination);
        countryX.addAdjacentCountry(Destination);
//        countryY.addAdjacentCountry(countryXname);
    }

    /**
     * This method adds Countries in Continent
     *
     * @param countryName, name of the Country
     * @param Continent, name of the Continent to be added
     */
    public static void addCountriesInContinent(String countryName, String Continent) {
        Continent temp = continentHashMap.get(Continent);
        temp.insertCountry(countryName);
    }
}


/**
 * GraphName Class
 * Creation of Adjacency Matrix
 *
 * */
class GraphNew {
    static int maxSize = 100;
    static int[][] countryMatrix = new int[maxSize][maxSize];
    static int size;

    /**
     * This method initializes Country Matrix
     */
    public static void initializeCountryMatrix() {
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                countryMatrix[i][j] = 0;
            }
        }
    }

    /**
     * This method adds Neighbour Country
     */
    public static void addNeighbour() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter x value to Make add neighbour :");
        int x = in.nextInt();
        System.out.println("Enter y value to Make add neighbour :");
        int y = in.nextInt();
        if (x <= Helper.getCountryCountId() && y <= Helper.getCountryCountId()) {
            countryMatrix[x][y] = 1;
        } else {
            System.out.println("Enter Correct x and y values !");
        }

    }

    /**
     * This method is used to display the Graph
     */
    public static void printGraph() {
        if (Helper.getCountryCountId() != 0) {
            for (int i = 0; i <= Helper.getCountryCountId(); i++) {
                for (int j = 0; j <= Helper.getCountryCountId(); j++) {
                    if (i == 0 && j == 0) {
                        System.out.print("Country");
                    } else if (i == 0) {
                        if (j != 0)
                            System.out.print(CreateMap.countryIdHashMap.get(j) + "(" + j + ") ");
                    } else if (j == 0) {
                        if (i != 0)
                            System.out.print(CreateMap.countryIdHashMap.get(i) + "(" + i + ") ");
                    } else {
                        System.out.print(countryMatrix[j][i] + " ");
                    }
                }
                System.out.println("");
            }
        } else {
            System.out.println("Array is empty !");
        }
    }

    /**
     * This method is used to read from Graph
     */
    public static void readFromGraph() {
        if (Helper.getCountryCountId() != 0) {
            for (int i = 0; i <= Helper.getCountryCountId(); i++) {
                for (int j = 0; j <= Helper.getCountryCountId(); j++) {
                    if (i != 0 || j != 0) {
                        if (countryMatrix[j][i] == 1)
                            CreateMap.addAdjacentNeighbourCountriesUsingCoordinates(j, i);
                    }
                }
                CreateMapFile.createFile();
            }
        } else {
            System.out.println("Array is empty !");
        }
    }

    /**
     * Constructor of GraphNew Class, used to initialize countryMatrix, 2-D array
     */
    GraphNew() {
        /*this.size = Helper.getCountryCountId();
        if(this.size == -1)
            this.size = 0;
        countryMatrix = new int[size][size];*/
        for (int[] single : countryMatrix)
            Arrays.fill(single, 0);
    }

//    GraphNew(int size){
//        this.size = size;
//        countryMatrix = new int[size][size];
//        for(int[] single : countryMatrix)
//            Arrays.fill(single, 0);
//    }

    /**
     * This method is used to create new graph with the existing co-ordinates
     */
    public static void createNewGraphWithOldValues() {
        size = Helper.getCountryCountId();


    }

    /**
     * This method is used to add source and destination values to Country Matrix
     * @param source, int
     * @param destination, int
     */
    public void add(int source, int destination) {
        countryMatrix[source][destination] = 1;
        countryMatrix[destination][source] = 1;
    }

    /**
     * This method is used to find adjacent country
     * @param index, int
     * @return adjacent, returns list of adjacent country index
     */
    public ArrayList<Integer> findAdjacent(int index) {
        ArrayList<Integer> adjacent = new ArrayList<Integer>();
        for (int i = 1; i < Helper.getCountryCountId(); i++) {
            if (countryMatrix[index][i] == 1)
                adjacent.add(i);
        }
        return adjacent;
    }

    /**
     * This method is used to check whether the country is connected or not
     * @param source, int
     * @param destination, int
     * @return false, boolean
     */
    public boolean isConnected(int source, int destination) {
        if (countryMatrix[source][destination] == 1 || countryMatrix[destination][source] == 1)
            return true;
        return false;
    }

    /**
     * This method is used to get the neighbors of the Country
     * @param v, id of the Country
     * @return neighbours, lists the neighboring countries
     */
    public List<Integer> getNeighbors(int v) {
        int numV = Helper.getCountryCountId();
        if (v >= numV) {
            System.out.println("Can not Iterate through the list");
        }
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < numV; i++) {
            if (countryMatrix[v][i] != 0) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }


}