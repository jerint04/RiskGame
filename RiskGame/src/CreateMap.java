import java.util.Arrays;
import java.util.*;
/**
 * @author Vikram
 * @date 2019-02-20
 */
public class CreateMap {

    static ArrayList<Continent> ContinentList = new ArrayList<Continent>();
    static ArrayList<Country> CountryList = new ArrayList<Country>();
    static HashMap<Integer, String> countryIdHashMap = new HashMap<>();

    public static void createContinent() {
        System.out.println("Please Enter the Continent Name :");
        Scanner input = new Scanner(System.in);
        String continentName = input.nextLine();
        System.out.println("Please Enter Control Value :");
        int controlValue = input.nextInt();
        ContinentList.add(new Continent(continentName, controlValue));
    }

    public static void createCountry() {
        System.out.println("Please Enter the Country Name :");
        Scanner input = new Scanner(System.in);
        String countryName = input.nextLine();
        System.out.println("Please Enter Continent it Belongs :");
        String continentBelongsTo = input.nextLine();
        int id = Helper.getNewCountryCountId();
        CountryList.add(new Country(id, countryName, continentBelongsTo));
        countryIdHashMap.put(id, countryName);
    }
}


/*
 * Creation of Adjacency Matrix
 * */
class GraphNew {
    static int[][] countryMatrix = new int[100][100];
    static int size;


    public static void addNeighbour() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter x value to Make add neighbour :");
        int x = in.nextInt();
        System.out.println("Enter y value to Make add neighbour :");
        int y = in.nextInt();
        if(x <= Helper.getCountryCountId() && y <= Helper.getCountryCountId()){
            countryMatrix[x][y] = 1;
        } else{
            System.out.println("Enter Correct x and y values !");
        }

    }

    public static void printGraph() {
        if (Helper.getCountryCountId() != 0) {
            for (int i = 0; i <= Helper.getCountryCountId(); i++) {
                for (int j = 0; j <= Helper.getCountryCountId(); j++) {
                    if(i==0 && j==0){
                        System.out.print("Country");
                    }else if (i == 0) {
                        if (j != 0)
                            System.out.print(CreateMap.countryIdHashMap.get(j) + "(" + j + ") ");
                    } else if (j == 0) {
                        if (i != 0)
                            System.out.print(CreateMap.countryIdHashMap.get(i) + "(" + i+ ") ");
                    } else {
                        System.out.print(countryMatrix[i][j]+" ");
                    }
                }
                System.out.println("");
            }
        }else{
            System.out.println("Array is empty !");
        }
    }

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

    public static void createNewGraphWithOldValues() {
        size = Helper.getCountryCountId();


    }

    public void add(int source, int destination) {
        countryMatrix[source][destination] = 1;
        countryMatrix[destination][source] = 1;
    }


    public ArrayList<Integer> findAdjacent(int index) {
        ArrayList<Integer> adjacent = new ArrayList<Integer>();
        for (int i = 1; i < Helper.getCountryCountId(); i++) {
            if (countryMatrix[index][i] == 1)
                adjacent.add(i);
        }
        return adjacent;
    }

    public boolean isConnected(int source, int destination) {
        if (countryMatrix[source][destination] == 1 || countryMatrix[destination][source] == 1)
            return true;
        return false;
    }

    public List<Integer> getNeighbors(int v) {
        int numV = Helper.getCountryCountId();
        if (v >= numV) {
            System.out.println("Can not Iterate through the list");
            ;
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