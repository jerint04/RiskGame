import java.util.*;

/**
 * @author Hemanshu
 * @date 2019-02-12
 */
public class Player {
    List<String> Cards = new ArrayList<>();
    List<String> countriesOwned = new ArrayList<>();
    boolean alive;
    String Name;
    public static int numberOfPlayers = GameDriver.PlayerList.size();
    public static HashMap<String, HashMap<String, Integer>> countryAssignedToPlayers = new HashMap<>();
    public static HashMap<String, Integer> countryAssigning = new HashMap<>();

    public Player(String name) {
        this.alive = true;
        Name = name;
    }

    public List<String> getCards() {
        return Cards;
    }

    public List<String> getCountriesOwned() {
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
}
