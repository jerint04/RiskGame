package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Game Model class
 *
 * @author Hemanshu
 * @version 1.0.0
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

    public static boolean draw = false;
    public static String winner = "";
    public static String mapname="";

    /**
     * This method is used to re-initialize variables
     */
    public static void reInitializeVariables(){
        ContinentList = new ArrayList<>();
        continentHashMap = new HashMap<>();
        CountryList = new ArrayList<>();
        countryIdHashMap = new HashMap<>();
        countryHashMap = new HashMap<>();
        CountryList.clear();
        ContinentList.clear();
        continentHashMap.clear();
        countryIdHashMap.clear();
        countryHashMap.clear();
        Helper.countryCountId = 0; // The count starts from 1 and not from 0.. Please pay attention to this and remember
        Helper.continentCountId = 0;
    }
}
