package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * Model.Player Class
 *
 * @author Hemanshu
 * @version 1.0.0
 */
public class Player extends Observable {

    public  HashMap<Integer, String> cardsOwned=new HashMap<>();
    public  List<String> Cards = new ArrayList<>();
    public  List<String> countriesOwned = new ArrayList<>();
    public String continentsOwned="";
    public boolean alive;
    public String Name;
    public int playerId;
    public int numberOfInfantry;
    public static int numberOfPlayers = GameModel.PlayerList.size();
    public int turn = 1;
    public int armiesInExcahngeOfcards = 0;
    public boolean cardBooleanValue;
    public static String GamePhase = "";
    public boolean shouldGetTheCard = false;

    public boolean getShouldGetTheCard() {
        return shouldGetTheCard;
    }

    public void setShouldGetTheCard(boolean shouldGetTheCard) {
        this.shouldGetTheCard = shouldGetTheCard;
    }

    public static String getGamePhase() {
        return GamePhase;
    }

    public static void setGamePhase(String gamePhase) {
        GamePhase = gamePhase;
    }


    public int getArmiesInExcahngeOfcards() {
        return armiesInExcahngeOfcards;
    }

    public void setArmiesInExcahngeOfcards(int armiesInExcahngeOfcards) {
        this.armiesInExcahngeOfcards = armiesInExcahngeOfcards;
    }


    /**
     * This is a constructor of Model.Player Class which sets player Name
     *
     * @param name, name of the player
     */

    private List<Observer> observers;

    public Player() {
        observers = new ArrayList<Observer>();
    }

    public Player(String name) {
        this.alive = true;
        Name = name;
    }

    public HashMap<Integer, String> getCardsOwned() {
        return cardsOwned;
    }

    public void setCardsOwned(HashMap<Integer, String> cardsOwned) {
        this.cardsOwned = cardsOwned;
    }

    /**
     * This is a constructor of Model.Player Class which sets player Id and player Name
     *
     * @param name,     name of the player
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
    public List<String> getCards() {
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
     * This method will return if a Model.Player is alive or not
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
    public  String getName() {

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
     * @param numberOfInfantry, number of the Infantary of the player
     */
    public void setNumberOfInfantry(int numberOfInfantry) {

        this.numberOfInfantry = numberOfInfantry;
    }

    /**
     * This method is used to get the number of infantary of the player
     *
     * @return numberOfInfantry,
     *                  returning the number of the Infantary of the player
     */
    public int getNumberOfInfantry() {

        return numberOfInfantry;
    }

    /**
     * This method is used to get player id
     *
     * @return playerId int
     */
    public  int getPlayerId() {
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
     * @param numberOfPlayers, the number of players
     */
    public static void setNumberOfPlayers(int numberOfPlayers) {
        Player.numberOfPlayers = numberOfPlayers;
    }

    public   void updatingObserver(){
        System.out.println(" updating observer!!!");
        setChanged();
    notifyObservers(this);

    }




}
