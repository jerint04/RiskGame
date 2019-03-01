import java.util.*;

/**
 * Player Class
 *
 * @author Hemanshu
 * @version 1.0.0
 * @date 2019-02-12
 */
public class Player {
    
    static List<String> Cards = new ArrayList<>();
    List<String> countriesOwned = new ArrayList<>();
    boolean alive;
    String Name;
    int playerId;
    int numberOfInfantary;
    public static int numberOfPlayers = GameDriver.PlayerList.size();
    public static HashMap<String, List<String>> countryAssignedToPlayers = new HashMap<>();
//    public HashMap<String, Integer> countryAssigning = new HashMap<>();

    /**
     * This is a constructor of Player Class which sets player Name
     *
     * @param name, name of the player
     */
    public Player(String name) {
        this.alive = true;
        Name = name;
    }

    /**
     * This is a constructor of Player Class which sets player Id and player Name
     *
     * @param name, name of the player
     * @param playerId, id of the player
     */
    public Player(int playerId, String name) {
        this.playerId = playerId;
        this.alive = true;
        Name = name;
    }

    /**
     * Get player cards
     *
     * @return Cards, list of cards of player
     */
    public static List<String> getCards() {
        return Cards;
    }

    /**
     * Get Countries owned
     *
     * @return countriesOwned, list of Countries owned by the player
     */
    public List<String> getCountriesOwned() {

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
     * @param cards, type of card
     */
    public void setCards(List<String> cards) {
        Cards = cards;
    }

    /**
     * This method is used to set Countries owned by the player
     *
     * @param countriesOwned,
     */
    public void setCountriesOwned(List<String> countriesOwned) {

        this.countriesOwned = countriesOwned;
    }

    /**
     * This method is used to set status of the player
     *
     * @param alive, if player is alive else false
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * This method is used to set name of the player
     *
     * @param name, name of the player
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * This method is used to set the number of infantary of the player
     *
     * @param numberOfInfantary, number of the Infantary of the player
     */
    public void setNumberOfInfantary(int numberOfInfantary) {

        this.numberOfInfantary = numberOfInfantary;
    }

    /**
     * This method is used to get the number of infantary of the player
     *
     * @return numberOfInfantary,
     *                  returning the number of the Infantary of the player
     */
    public int getNumberOfInfantary() {

        return numberOfInfantary;
    }

    /**
     * This method is used to get player id
     *
     * @return playerId int
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * This method is used to set player id
     *
     * @param playerId,
     *          Id of the player
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * This method is used to get number of players
     *
     * @return numberOfPlayers int
     */
    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * This method is used to set number of players
     *
     * @param numberOfPlayers
     */
    public static void setNumberOfPlayers(int numberOfPlayers) {
        Player.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Assigns the current country to player
     */
    public static void assigningCountries() {
        
        int numberofInfantary;
        HashMap<String, List<String>> countryAssignedToPlayers = new HashMap<>();
        List<String> nameOfCountry[] = new ArrayList[GameDriver.PlayerList.size()];

        Random numberGenerator = new Random();

        for (int j = 0; j < CreateMap.countryIdHashMap.size(); j++) {
            for (int i = 0; i < GameDriver.PlayerList.size(); i++) {

                int number = numberGenerator.nextInt((CreateMap.countryIdHashMap.size() - 1) + 1) + 1;

                String countryName = CreateMap.countryIdHashMap.get(number);
                int playerId = GameDriver.PlayerList.get(i).getPlayerId();
                if (CreateMap.countryHashMap.get(countryName).getPlayerId() == null) {
                    int count = 0;
                    count++;
                    CreateMap.countryHashMap.get(countryName).setPlayerId(playerId);
                    CreateMap.countryHashMap.get(countryName).setNumberOfSoldiers(count);

                    //nameOfCountry.add(countryName);
                    //GameDriver.PlayerList.get(i).setCountriesOwned(nameOfCountry);
                    // countryAssignedToPlayers.put(playerName,nameOfCountry);
                     /*numberofInfantary = GameDriver.PlayerList.get(i).getNumberOfInfantary();
                    numberofInfantary=numberofInfantary-count;
                    GameDriver.PlayerList.get(i).setNumberOfInfantary(numberofInfantary );
*/

                }
            }
        }


        for (int j = 1; j <= CreateMap.countryIdHashMap.size(); j++) {

            String countryName = CreateMap.countryIdHashMap.get(j);

            System.out.println("player details :" + j + CreateMap.countryHashMap.get(countryName).getPlayerId() + " country name :" + countryName + " army count :" + CreateMap.countryHashMap.get(countryName).getNumberOfSoldiers());
        }
        /*for (int i = 0; i < GameDriver.PlayerList.size(); i++) {
            GameDriver.PlayerList.get(i).getName();
            //countryAssignedToPlayers.get(GameDriver.PlayerList.get(i).getName());
            //System.out.println(" name :"+GameDriver.PlayerList.get(i).getName() + "number of infantary "+ countryAssignedToPlayers.get(GameDriver.PlayerList.get(i).getName()));
            System.out.println("Country assigned to players :" + GameDriver.PlayerList.get(i).getCountriesOwned());
        }*/
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

        for (int i = 0; i < GameDriver.PlayerList.size(); i++) {

        }

    }
}
