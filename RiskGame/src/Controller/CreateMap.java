package Controller;

import Model.Continent;
import Model.Country;
import Model.GameModel;
import Model.Helper;

import java.util.*;

/**
 * Controller.CreateMap Class
 *
 * @author Vikram
 * @version 1.0.0
 * @date 2019-02-20
 */
public class CreateMap {

    /**
     * This method creates Model.Continent
     */
    public static void createContinent() {
        System.out.println("Please Enter the Model.Continent Name :");
        Scanner input = new Scanner(System.in);
        String continentName = input.nextLine();
        System.out.println("Please Enter Control Value :");
        int controlValue = input.nextInt();
        Continent temp = new Continent(continentName, controlValue);
        GameModel.ContinentList.add(continentName);
        GameModel.continentHashMap.put(continentName, temp);
    }

    /**
     * This method deletes the country
     */
    /*todo : test this function thoroughly &&&   THROw THE ERRORS FOR THIS ONE AND return THE BOOLEAN to know if successfully  deleted or not*/
    public static void removeCountry() {
        System.out.println("Select the Model.Country number to remove from the list:");
        for (int key : GameModel.countryIdHashMap.keySet()) {
            System.out.println(key + " " + GameModel.countryIdHashMap.get(key));
        }
        Scanner input = new Scanner(System.in);
        int countryKey = input.nextInt();
        String countryNameToDelete = GameModel.countryIdHashMap.get(countryKey);
        Country toDelete = GameModel.countryHashMap.get(countryNameToDelete);
        String continentBelongingTo = toDelete.getParentContinent();
        GameModel.countryHashMap.remove(countryNameToDelete);
        GameModel.countryIdHashMap.remove(countryKey);
        /*Removing the country from the continent*/
        GameModel.ContinentList.remove(countryNameToDelete); /*todo : this line seems to be wrong*/
        toDelete = null;
        GameModel.continentHashMap.get(continentBelongingTo).Countries.remove(countryNameToDelete);
        // Removing the country from the adjacent countries list
        for (String key : GameModel.countryHashMap.keySet()) {
            if (GameModel.countryHashMap.get(key).getAdjacentCountries().contains(countryNameToDelete)) {
                GameModel.countryHashMap.get(key).getAdjacentCountries().remove(countryNameToDelete);
            }
        }
    }


    /**
     * This method deletes the Model.Continent
     */
    public static void removeContinent() {
        System.out.println("Write the name of the Model.Continent you want to delete:");
        for (String key : GameModel.ContinentList) {
            System.out.println(key);
        }
        Scanner input = new Scanner(System.in);
        String continent = input.nextLine();
        if (GameModel.continentHashMap.containsKey(continent)) {
            if (GameModel.continentHashMap.get(continent).Countries.isEmpty()) {
                GameModel.continentHashMap.remove(continent);
                GameModel.ContinentList.remove(continent);
                System.out.println("Successfully Deleted Model.Continent :" + continent);
            } else {
                System.out.println("Can not delete the Model.Continent, need to delete the countries in the Model.Continent first");
                for (String countries : GameModel.continentHashMap.get(continent).getCountries()) {
                    System.out.println(countries);
                }
            }
        } else {
            System.out.println("Please enter correct Model.Continent");
        }
    }

    /**
     * This method creates Model.Country
     */
    public static void createCountry() {
        System.out.println("Please Enter the Model.Country Name :");
        Scanner input = new Scanner(System.in);
        String countryName = input.nextLine();
        System.out.println("Please Enter Model.Continent it Belongs :");
        String continentBelongsTo = input.nextLine();
        int id = Helper.getNewCountryCountId();
        Country temp = new Country(id, countryName, continentBelongsTo);
        GameModel.CountryList.add(countryName);
        GameModel.countryIdHashMap.put(id, countryName);
        GameModel.countryHashMap.put(countryName, temp);
        addCountriesInContinent(countryName, continentBelongsTo);
    }

    /**
     * This method adds adjacent neighbour Countries using x,y coordinates
     *
     * @param i, x-coordinate
     * @param j, y-coordinate
     */
    public static void addAdjacentNeighbourCountriesUsingCoordinates(int i, int j) {
        String countryXname = GameModel.countryIdHashMap.get(i);
        String countryYname = GameModel.countryIdHashMap.get(j);
        Country countryX = GameModel.countryHashMap.get(countryXname);
//        Model.Country countryY = countryHashMap.get(countryYname);
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
        Country countryX = GameModel.countryHashMap.get(Source);
//        Model.Country countryY = countryHashMap.get(Destination);
        countryX.addAdjacentCountry(Destination);
//        countryY.addAdjacentCountry(countryXname);
    }

    /**
     * This method adds Countries in Model.Continent
     *
     * @param countryName, name of the Model.Country
     * @param Continent, name of the Model.Continent to be added
     */
    public static void addCountriesInContinent(String countryName, String Continent) {
        Model.Continent temp = GameModel.continentHashMap.get(Continent);
        temp.insertCountry(countryName);
    }
}


