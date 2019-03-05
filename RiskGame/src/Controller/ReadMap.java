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

}
