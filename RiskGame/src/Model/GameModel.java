package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
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

}
