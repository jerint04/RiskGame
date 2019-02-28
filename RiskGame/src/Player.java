import java.util.*;

/**
 * Player Class
 * @author Hemanshu
 * @version 1.0.0
 * @date 2019-02-12
 */
public class Player {
    static List<String> Cards = new ArrayList<>();
    static List<String> countriesOwned = new ArrayList<>();
    boolean alive;


    String Name;
    public static int numberOfInfantary;
    public static int numberOfPlayers = GameDriver.PlayerList.size();
    public static HashMap<String, HashMap<String, Integer>> countryAssignedToPlayers = new HashMap<>();
    public static HashMap<String, Integer> countryAssigning = new HashMap<>();

    /**
     * This is a constructor of Player Class which sets player Name
     *
     * @param name,
     *           name of the player
     */
    public Player(String name) {
        this.alive = true;
        Name = name;
    }

    /**
     * Get player cards
     *
     * @return Cards,list of cards of player
     */
    public static List<String> getCards() {
        return Cards;
    }

    /**
     * Get Countries owned
     *
     * @return countriesOwned,list of Countries owned by the player
     */
    public static List<String> getCountriesOwned() {

        return countriesOwned;
    }

    /**
     * This method will return if a Player is alive or not
     *
     * @return true, if player is alive
     */
    public boolean isAlive() {

        return alive;
    }

    /**
     * This method return the name of the player
     *
     * @return name String
     */
    public String getName() {

        return Name;
    }

    /**
     * Add card to player
     *
     * @param cards,
     *            type of card
     */
    public void setCards(List<String> cards) {
        Cards = cards;
    }

    /**
     * This method is used to set Countries owned by the player
     *
     * @param countriesOwned,
     *
     */
    public void setCountriesOwned(List<String> countriesOwned) {

        this.countriesOwned = countriesOwned;
    }

    /**
     * This method is used to set status of the player
     *
     * @param alive,
     *           if player is alive else false
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * This method is used to set name of the player
     *
     * @param name,
     *           name of the player
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * This method is used to set the number of infantary of the player
     *
     * @param numberOfInfantary,
     *           number of the Infantary of the player
     */
    public static void setNumberOfInfantary(int numberOfInfantary) {

        Player.numberOfInfantary = numberOfInfantary;
    }

    /**
     * This method is used to get the number of infantary of the player
     *
     * @return numberOfInfantary,
     *           returning the number of the Infantary of the player
     */
    public int getNumberOfInfantary() {

        return numberOfInfantary;
    }

    /**
     * Assigns the current country to player
     */
    public static void assigningCountries() {
        Random numberGenerator = new Random();

        for (int j = 0; j < CreateMap.countryIdHashMap.size(); j++) {
            for (int i = 0; i < GameDriver.PlayerList.size(); i++) {

                int number = numberGenerator.nextInt((CreateMap.countryIdHashMap.size() - 1) + 1) + 1;

                String countryName = CreateMap.countryIdHashMap.get(number);
                String playerName = GameDriver.PlayerList.get(i).getName();
                if (CreateMap.countryHashMap.get(countryName).getPlayerId() == null) {
                    CreateMap.countryHashMap.get(countryName).setPlayerId(playerName);
                }
            }
        }

        for (int j = 1; j <= CreateMap.countryIdHashMap.size(); j++) {

            String countryName = CreateMap.countryIdHashMap.get(j);

            System.out.println("player details" + CreateMap.countryHashMap.get(countryName).getPlayerId() + " country name " + countryName);
        }

    }

    /**
     * This method is used to intialise infantary of the player
     */
    public static void initialisationInfantory() {
        if (GameDriver.PlayerList.size() == 3) {
            for (int i = 0; i < GameDriver.PlayerList.size(); i++) {
                GameDriver.PlayerList.get(i).setNumberOfInfantary(35);
            }

        } else if (GameDriver.PlayerList.size() == 4) {
            for (int i = 0; i < GameDriver.PlayerList.size(); i++) {
                GameDriver.PlayerList.get(i).setNumberOfInfantary(30);
            }
        } else if (GameDriver.PlayerList.size() == 5) {
            for (int i = 0; i < GameDriver.PlayerList.size(); i++) {
                GameDriver.PlayerList.get(i).setNumberOfInfantary(25);
            }

        } else if (GameDriver.PlayerList.size() == 6) {
            for (int i = 0; i < GameDriver.PlayerList.size(); i++) {
                GameDriver.PlayerList.get(i).setNumberOfInfantary(20);
            }

        }
    }

}

