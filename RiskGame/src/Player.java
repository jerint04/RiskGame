import java.util.*;

/**
 * @author Hemanshu
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

    public Player(String name) {
        this.alive = true;
        Name = name;
    }

    public static List<String> getCards() {
        return Cards;
    }

    public static List<String> getCountriesOwned() {
        return countriesOwned;
    }

    public boolean isAlive() {
        return alive;
    }

    public String getName() {
        return Name;
    }

    public void setCards(List<String> cards) {
        Cards = cards;
    }

    public void setCountriesOwned(List<String> countriesOwned) {
        this.countriesOwned = countriesOwned;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setName(String name) {
        Name = name;
    }

    public static void setNumberOfInfantary(int numberOfInfantary) {
        Player.numberOfInfantary = numberOfInfantary;
    }

    public int getNumberOfInfantary() {
        return numberOfInfantary;
    }


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

