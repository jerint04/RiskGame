package Controller;

import Model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Map Class
 *
 * @author Jerin
 * @version 1.0.0
 * @date 22-February-2019
 */
public class ReadMap {

  /*  public static void main(String[] args) {
        readMap("./assets/maps/Asia.map");
        validateMap();
    }*/

    /**
     * This function loads the Map into the memory
     *
     * @param getFileName,
     */
    public static boolean readMap(String getFileName) {
        try {
            boolean readContinentsFromFile = false;
            boolean readCountriesFromFile = false;
            String filePath = getFileName;
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine;
            int continentID = Helper.getContinentCountId();
            int countryID = Helper.getCountryCountId();

            while ((readLine = bufferedReader.readLine()) != null) {
                if (readLine.trim().length() == 0)
                    continue;
                else if ((readLine.trim()).equals("[Continents]")) {
                    readContinentsFromFile = true;
                    continue;
                } else if ((readLine.trim()).equals("[Territories]")) {
                    readContinentsFromFile = false;
                    readCountriesFromFile = true;
                    continue;
                }
                if (readContinentsFromFile) {
                    String[] parsedContinentArray = readLine.split("=");
                    Continent continent = new Continent(Helper.getContinentCountId(), parsedContinentArray[0],
                            Integer.parseInt(parsedContinentArray[1]));
                    GameModel.ContinentList.add(parsedContinentArray[0]);
                    GameModel.continentHashMap.put(parsedContinentArray[0], continent);
                } else if (readCountriesFromFile) {
                    String[] parsedCountryList = readLine.split(",");
                    String continentName = parsedCountryList[3];
                    Country country = new Country(Helper.getNewCountryCountId(), parsedCountryList[0], continentName);
                    country.setxCoordinate(Integer.parseInt(parsedCountryList[1]));
                    country.setyCoordinate(Integer.parseInt(parsedCountryList[2]));
                    int p = 0;
                    // p is initialized to get neighbouring countries
                    for (String neighborCountry : parsedCountryList) {
                        if (p > 3) {
                            country.addAdjacentCountry(neighborCountry);
                        }
                        p++;
                    }
                    GameModel.continentHashMap.get(parsedCountryList[3]).insertCountry(parsedCountryList[0]);
                    GameModel.CountryList.add(parsedCountryList[0]);
                    GameModel.countryIdHashMap.put(Helper.getCountryCountId(), parsedCountryList[0]);
                    GameModel.countryHashMap.put(parsedCountryList[0], country);
                }
            }
            bufferedReader.close();

            GameModel.assigningCountries();

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        CountryAdjacencyMatrix.initializeCountryMatrix();
        boolean adjacencyMatrixCreation = createAdjacentMatrix();
        if (adjacencyMatrixCreation)
            CountryAdjacencyMatrix.printGraph();
        return true;
    }

    /**
     * This function creates adjacent Countries
     */
    public static boolean createAdjacentMatrix() {
        try {
            for (String a : GameModel.CountryList) {
                Country xTemp = GameModel.countryHashMap.get(a);
                Object xo = getKeyFromValue(GameModel.countryIdHashMap, xTemp.getCountryName());
                int x = (Integer) xo;
                for (String adjacentCountryName : xTemp.getAdjacentCountries()) {
                    Country yTemp = GameModel.countryHashMap.get(adjacentCountryName);
                    Object yo = getKeyFromValue(GameModel.countryIdHashMap, yTemp.getCountryName());
                    int y = (Integer) yo;
                    System.out.println(y + " " + x);
                    CountryAdjacencyMatrix.countryMatrix[y][x] = 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Map is Invalid, Please correct the Map file");
            System.out.println(e);
            return false;
        }
        System.out.println("Adjacency Matrix Created Successfully");
        return true;
    }

    /**
     * This function gets the country value
     *
     * @param hm,
     * @param value ,
     */
    public static Object getKeyFromValue(Map hm, String value) {
        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
            if (pair.getValue().equals(value)) {
                return pair.getKey();
            }
        }
        return -1;
    }

    /**
     * This function is used to validate the map
     *
     * @return false ,returns a boolean value
     */
    public static boolean validateMap() {
        boolean emptyContinent = false;
        boolean aloneCountry = false;

        /*To check if the Model.Continent is Empty. Model.Continent should have at least a single country*/
        for (String name : GameModel.ContinentList) {
            Continent currentContinent = GameModel.continentHashMap.get(name);
            if (currentContinent.getCountries().isEmpty() == true) {
                emptyContinent = true;
                System.out.println("Model.Continent " + name + " has no countries in it.");
                return false;
            }
        }

        /*To check if the country is isolated or not */
        for (String countryName : GameModel.countryHashMap.keySet()) {
            Country everyCountry = GameModel.countryHashMap.get(countryName);
            if (everyCountry.getAdjacentCountries().isEmpty() == true) {
                aloneCountry = true;
                System.out.println("Model.Country " + countryName + " has no adjacent countries in it.");
                return false;
            }
        }


        if (!aloneCountry) {
            Set tempSet = new HashSet();
            for (String continentName : GameModel.continentHashMap.keySet()) {
                List<String> countries = GameModel.continentHashMap.get(continentName).getCountries();
                for (String country : countries) {
                    if (!tempSet.add(country)) {
                        System.out.println("Model.Country " + country + " present in 2 continents !");
                        return false;
                    }
                }
            }
        }
        System.out.println("Is Map Connected :" + ReadMap.checkIsMapConnected());
        return true;
    }

    /**
     * This function checks the whether the map is connected or not
     *
     * @return isConnected , returns a boolean value
     */
    public static boolean checkIsMapConnected() {
        boolean isConnected = true;
        Set<String> keyNames = GameModel.countryHashMap.keySet();
        Country firstCountryInTheList = GameModel.countryHashMap.get(keyNames.toArray()[0]);
        dfsUsingStack(firstCountryInTheList);
        for (String countryName : keyNames) {
            if (GameModel.countryHashMap.get(countryName).isVisited())
                GameModel.countryHashMap.get(countryName).setVisited(false);
            else {
                isConnected = false;
                break;
            }
        }
        if (!isConnected) {
            System.out.println("Map is not connected !");
        }
        return isConnected;
    }

    /**
     * This function performs dfs using Stack
     *
     * @param countryModel, Model.Country type reference
     */
    private static void dfsUsingStack(Country countryModel) {
        Stack<Country> stack = new Stack<Country>();
        stack.add(countryModel);
        countryModel.setVisited(true);
        while (!stack.isEmpty()) {
            Country element = stack.pop();
//			System.out.println("DFS CountryName: " + element.getCountryName() + " ");
            List<String> neighbourNames = element.getAdjacentCountries();
            List<Country> neighbours = new ArrayList<>();
            for (String neighbourName : neighbourNames) {
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
    }

}
