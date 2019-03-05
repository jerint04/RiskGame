package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author Hemanshu
 * @date 2019-03-05
 */
public class GameModel {

    public static ArrayList<String> ContinentList = new ArrayList<String>();
    public static HashMap<String, Continent> continentHashMap = new HashMap<>();
    public static HashMap<Integer, String> continentIdHashMap = new HashMap<>(); //THis is redundant we might not use it. just created for timebeing

    public static ArrayList<String> CountryList = new ArrayList<String>(); // This is redundant we might not use it. just created for timebeing
    public static HashMap<Integer, String> countryIdHashMap = new HashMap<>();
    public static HashMap<String, Country> countryHashMap = new HashMap<>();


    public static HashMap<Integer, Player> playerHashMap = new HashMap<>();
    public static int playerNumber;
    public static ArrayList<Player> PlayerList = new ArrayList<Player>();

    /**
     * Assigns the current country to player
     */
    public static void assigningCountries() {

        int numberofInfantary;
        HashMap<String, List<String>> countryAssignedToPlayers = new HashMap<>();
        List<String> nameOfCountry[] = new ArrayList[PlayerList.size()];

        Random numberGenerator = new Random();

        for (int j = 0; j < countryIdHashMap.size(); j++) {
            for (int i = 0; i < PlayerList.size(); i++) {

                int number = numberGenerator.nextInt((countryIdHashMap.size() - 1) + 1) + 1;

                String countryName = countryIdHashMap.get(number);
                int playerId = PlayerList.get(i).getPlayerId();
                if (countryHashMap.get(countryName).getPlayerId() == null) {
                    int count = 0;
                    count++;
                    countryHashMap.get(countryName).setPlayerId(playerId);
                    countryHashMap.get(countryName).setNumberOfSoldiers(count);
                    //addInfantoryToCountry(countryName,playerId,count);

                }
            }
        }


        for (int j = 1; j <= countryIdHashMap.size(); j++) {

            String countryName = countryIdHashMap.get(j);

            System.out.println("player details :" + j + countryHashMap.get(countryName).getPlayerId() + " country name :" + countryName + " army count :" + countryHashMap.get(countryName).getNumberOfSoldiers());
        }
        /*for (int i = 0; i < GameDriver.PlayerList.size(); i++) {
            GameDriver.PlayerList.get(i).getName();
            //countryAssignedToPlayers.get(GameDriver.PlayerList.get(i).getName());
            //System.out.println(" name :"+GameDriver.PlayerList.get(i).getName() + "number of infantary "+ countryAssignedToPlayers.get(GameDriver.PlayerList.get(i).getName()));
            System.out.println("Model.Country assigned to players :" + GameDriver.PlayerList.get(i).getCountriesOwned());
        }*/
    }

    /**
     * This method is used to intialise infantary of the player
     */
    public static void initialisationInfantry() {
        if (PlayerList.size() == 3) {
            for (int i = 0; i < PlayerList.size(); i++) {
                PlayerList.get(i).setNumberOfInfantary(35);
            }

        } else if (PlayerList.size() == 4) {
            for (int i = 0; i < PlayerList.size(); i++) {
                PlayerList.get(i).setNumberOfInfantary(30);
            }
        } else if (PlayerList.size() == 5) {
            for (int i = 0; i < PlayerList.size(); i++) {
                PlayerList.get(i).setNumberOfInfantary(25);
            }

        } else if (PlayerList.size() == 6) {
            for (int i = 0; i < PlayerList.size(); i++) {
                PlayerList.get(i).setNumberOfInfantary(20);
            }

        }

        for (int i = 0; i < PlayerList.size(); i++) {

        }

    }
}
