package Controller;

import Model.Continent;
import Model.Country;
import Model.GameModel;
import Model.Player;

import java.util.*;

/**
 * @author Hemanshu
 * @date 2019-03-05
 */
public class ValidateMap {

    /*TODO : a function that checks at least all continent have minimum 2 countries in it*/


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
                System.out.println("Continent " + name + " has no countries in it.");
                return false;
            }
        }

        /*To check if the country is isolated or not */
        for (String countryName : GameModel.countryHashMap.keySet()) {
            Country everyCountry = GameModel.countryHashMap.get(countryName);
            if (everyCountry.getAdjacentCountries().isEmpty() == true) {
                aloneCountry = true;
                System.out.println("Country " + countryName + " has no adjacent countries in it.");
                return false;
            }
        }


        if (!aloneCountry) {
            Set tempSet = new HashSet();
            for (String continentName : GameModel.continentHashMap.keySet()) {
                List<String> countries = GameModel.continentHashMap.get(continentName).getCountries();
                for (String country : countries) {
                    if (!tempSet.add(country)) {
                        System.out.println("Country " + country + " present in 2 continents !");
                        return false;
                    }
                }
            }
        }
        System.out.println("Is Map Connected :" + checkIsMapConnected());
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

    public static boolean validationOfPlayersAndCountiesNumber() {
        boolean countryValidation = false;
        if (GameModel.PlayerList.size() == 3 && GameModel.CountryList.size() >= 6) {
            countryValidation = true;
        } else if (GameModel.PlayerList.size() == 4 && GameModel.CountryList.size() >= 8) {
            countryValidation = true;

        } else if (GameModel.PlayerList.size() == 5 && GameModel.CountryList.size() >= 10) {
            countryValidation = true;


        } else if (GameModel.PlayerList.size() == 6 && GameModel.CountryList.size() >= 12) {
            countryValidation = true;
        }

        return countryValidation;
    }



}
