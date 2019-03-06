package Controller;

import Model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
        boolean didContinentsRead = false;
        boolean didCountriesRead = false;
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
                    didContinentsRead = true;
                    continue;
                } else if ((readLine.trim()).equals("[Territories]")) {
                    readContinentsFromFile = false;
                    didCountriesRead  = true;
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

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        if(didContinentsRead && didCountriesRead) {
            CountryAdjacencyMatrix.initializeCountryMatrix();
            boolean adjacencyMatrixCreation = CountryAdjacencyMatrix.createAdjacentMatrix();
            if (adjacencyMatrixCreation)
                CountryAdjacencyMatrix.printGraph();
        }else{
            if(didContinentsRead)
                System.out.println("Error in Map File Read: Did not Parse Continents List");
            if(didCountriesRead)
                System.out.println("Error in Map File Read: Did not Parse Countries List");
            return false;
        }
        return true;
    }

}
