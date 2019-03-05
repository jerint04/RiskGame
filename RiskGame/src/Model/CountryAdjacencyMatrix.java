package Model;
import Controller.CreateMap;
import Model.GameModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Controller.CreateMapFile.createFile;

/**
 * @author Hemanshu
 * GraphName Class
 * Creation of Adjacency Matrix
 *
 * */
public class CountryAdjacencyMatrix {
    public static int maxSize = 100;
    public static int[][] countryMatrix = new int[maxSize][maxSize];
    public static int size;

    /**
     * This method initializes Model.Country Matrix
     */
    public static void initializeCountryMatrix() {
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                countryMatrix[i][j] = 0;
            }
        }
    }

    /**
     * This method adds Neighbour Model.Country
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
                        System.out.print("Model.Country");
                    } else if (i == 0) {
                        if (j != 0)
                            System.out.print(GameModel.countryIdHashMap.get(j) + "(" + j + ") ");
                    } else if (j == 0) {
                        if (i != 0)
                            System.out.print(GameModel.countryIdHashMap.get(i) + "(" + i + ") ");
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
                createFile();
            }
        } else {
            System.out.println("Array is empty !");
        }
    }

    /**
     * Constructor of Model.CountryAdjacencyMatrix Class, used to initialize countryMatrix, 2-D array
     */
    CountryAdjacencyMatrix() {
        /*this.size = Model.Helper.getCountryCountId();
        if(this.size == -1)
            this.size = 0;
        countryMatrix = new int[size][size];*/
        for (int[] single : countryMatrix)
            Arrays.fill(single, 0);
    }


    /**
     * This method is used to create new graph with the existing co-ordinates
     */
    public static void createNewGraphWithOldValues() {
        size = Helper.getCountryCountId();


    }

    /**
     * This method is used to add source and destination values to Model.Country Matrix
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
     * This method is used to get the neighbors of the Model.Country
     * @param v, id of the Model.Country
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