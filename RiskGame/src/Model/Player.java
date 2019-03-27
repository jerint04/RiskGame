package Model;

import Controller.AttackPhase;
import Controller.GameController;
import View.PlayerDominationObserverView;

import java.util.*;
import java.util.Observable;

import static Model.GameModel.playerHashMap;

/**
 * Model.Player Class
 *
 * @author Hemanshu
 * @version 1.0.0
 */
public class Player extends Observable {

    public HashMap<Integer, String> cardsOwned = new HashMap<>();
    public List<String> Cards = new ArrayList<>();
    public List<String> countriesOwned = new ArrayList<>();
    public String continentsOwned = "";
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
    public String infoAboutAction = "";


    /**
     * This method is gets the card
     *
     * @return shouldGetTheCard, boolean
     */
    public boolean getShouldGetTheCard() {
        return shouldGetTheCard;
    }

    /**
     * This method is sets the card
     *
     * @param shouldGetTheCard, sets the card
     */
    public void setShouldGetTheCard(boolean shouldGetTheCard) {
        this.shouldGetTheCard = shouldGetTheCard;
    }

    /**
     * This method is used to get the GamePhase
     *
     * @return GamePhase, String
     */
    public static String getGamePhase() {
        return GamePhase;
    }

    /**
     * This method is used to set the Game Phase
     *
     * @param gamePhase, String
     */
    public static void setGamePhase(String gamePhase) {
        GamePhase = gamePhase;
    }

    /**
     * This method is used to get the armies in exchange of Cards
     *
     * @return armiesInExcahngeOfcards , int
     */
    public int getArmiesInExcahngeOfcards() {
        return armiesInExcahngeOfcards;
    }

    /**
     * This method is used to set the armies in exchange of Cards
     *
     * @param armiesInExcahngeOfcards, int
     */
    public void setArmiesInExcahngeOfcards(int armiesInExcahngeOfcards) {
        this.armiesInExcahngeOfcards = armiesInExcahngeOfcards;
    }


    /**
     * This is a constructor of Model.Player Class which sets player Name
     */
    private List<Observer> observers;

    public Player() {
        observers = new ArrayList<Observer>();
    }


    /**
     * This is a constructor of Model.Player Class which sets player Name
     *
     * @param name, name of the player
     */
    public Player(String name) {
        this.alive = true;
        Name = name;
    }


    /**
     * This method is used to get the cards owned
     *
     * @return cardsOwned, HashMap
     */
    public HashMap<Integer, String> getCardsOwned() {
        return cardsOwned;
    }

    /**
     * This method is used to set the cards owned
     *
     * @param cardsOwned, HashMap
     */
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
     * @param numberOfInfantry, number of the Infantary of the player
     */
    public void setNumberOfInfantry(int numberOfInfantry) {

        this.numberOfInfantry = numberOfInfantry;
    }

    /**
     * This method is used to get the number of infantary of the player
     *
     * @return numberOfInfantry,
     * returning the number of the Infantary of the player
     */
    public int getNumberOfInfantry() {

        return numberOfInfantry;
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
     * @param playerId, Id of the player
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

    /**
     * This method is used to update observers by notifying them the changes happened
     */
    public void updatingObserver() {
        System.out.println(" updating observer!!!");
        setChanged();
        notifyObservers(this);

    }

    /**
     * Calculating armies according to the risk rule that is  calculating using number of territories occupied
     *
     * @param playerId, id of the player
     */
    public static void armyCalculationDuringReinforcement(int playerId) {

        Player play = playerHashMap.get(playerId);
        play.GamePhase = "Reinforcement";
        ViewObserver VOb = new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
        Scanner sc = new Scanner(System.in);
        Player temp = playerHashMap.get(playerId);
        if (GameModel.playerHashMap.get(playerId).getShouldGetTheCard()) {
            System.out.println("Player " + playerId + " has earned the card as he Won a country in previous attack");
            GameController.earnRiskCards(playerId);
            GameController.earnRiskCards(playerId);
            GameController.earnRiskCards(playerId);
            GameModel.playerHashMap.get(playerId).setShouldGetTheCard(false);
        }
        int armyToAllocate = playerHashMap.get(playerId).countriesOwned.size() / 3;
        playerHashMap.get(playerId).numberOfInfantry = +((armyToAllocate < 3) ? 3 : armyToAllocate);
        for (String key : GameModel.continentHashMap.keySet()) {
            Continent tempContinent = GameModel.continentHashMap.get(key);
            if (temp.countriesOwned.containsAll(tempContinent.Countries)) {
                playerHashMap.get(playerId).numberOfInfantry = +tempContinent.controlValue;
            }
        }

        if (temp.Cards.size() == 3) {

            System.out.println("You have 3 cards, press 1 to exchange cards for armies");
            int choice = sc.nextInt();
            if (choice == 1) {
                GameController.exchangeCardsForArmies(playerId);
            }

        } else if (temp.Cards.size() >= 5) {
            System.out.println("You have to exchange cards to proceed");
            GameController.exchangeCardsForArmies(playerId);

        }
        Player playerView = new Player();
        PlayerDominationObserverView POb = new PlayerDominationObserverView();
        playerView.addObserver(POb);
        playerView.updatingObserver();
    }

    /**
     * Placing the armies during reinforcement
     *
     * @param playerId, id of the player
     */
    public static void armyPlacementDuringReinforcement(int playerId) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Infantry for the current players is : " + playerHashMap.get(playerId).numberOfInfantry);

        while (playerHashMap.get(playerId).numberOfInfantry != 0) {
            int i = 0;
            for (String countryName : playerHashMap.get(playerId).getCountriesOwned()) {
                System.out.println(i + ":" + countryName + "->" + GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                i = i + 1;
            }
            System.out.println("Enter the number of armies to be allocated :");
            int numOfArmies = sc.nextInt();
            System.out.println("Enter the serial number of the country :");
            int countrySerialNum = sc.nextInt();
            GameController.addInfantryToCountry(playerHashMap.get(playerId).countriesOwned.get(countrySerialNum), playerId, numOfArmies);
            playerHashMap.get(playerId).numberOfInfantry = playerHashMap.get(playerId).numberOfInfantry - numOfArmies;
        }
    }

    /**
     * This method indicates the turn of the Player to attack
     *
     * @param playerId, int
     *                  id of the player
     */
    public static void playerAttackTurn(int playerId) {
        Player playerView = playerHashMap.get(playerId);
        playerView.GamePhase = "Attack Phase";
        ViewObserver VOb = new ViewObserver();
        playerView.addObserver(VOb);
        playerView.updatingObserver();
        String infoOnActions = "";
        int playGame = 1;
        while (playGame != -1) {
            List<String> countriesOwned = playerHashMap.get(playerId).getCountriesOwned();
            HashMap<String, Integer> countriesThatCanAttack = new HashMap<String, Integer>();
            List<String> countriesTemp = new ArrayList<>();
            boolean addCountryToOwn = false;
            HashMap<String, Integer> allCountriesAttackTo = new HashMap<String, Integer>();
            HashMap<String, Integer> countriesAttackTo = new HashMap<String, Integer>();
            for (String Country : countriesOwned) {
                addCountryToOwn = false;
                if (GameModel.countryHashMap.get(Country).getNumberOfSoldiers() > 1) {
                    countriesTemp = GameModel.countryHashMap.get(Country).getAdjacentCountries();
                    for (String countryName : countriesTemp) {
                        if (GameModel.countryHashMap.get(countryName).PlayerId != playerId) {
                            addCountryToOwn = true;
                            allCountriesAttackTo.put(countryName, GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                        }
                    }
                }
                if (addCountryToOwn) {
                    countriesThatCanAttack.put(Country, GameModel.countryHashMap.get(Country).getNumberOfSoldiers());
                }
            }

            System.out.println("Counties That Can Attack");
            for (String country : countriesThatCanAttack.keySet()) {
                System.out.println(country + "->" + countriesThatCanAttack.get(country));
            }
            if (countriesThatCanAttack.size() != 0) {
                System.out.println("Enter the Full Name of The country :");
                Scanner sc = new Scanner(System.in);
                String from = sc.nextLine();
                countriesAttackTo = new HashMap<>();
                countriesTemp = GameModel.countryHashMap.get(from).getAdjacentCountries();
                for (String countryName : countriesTemp) {
                    if (GameModel.countryHashMap.get(countryName).PlayerId != playerId) {
                        countriesAttackTo.put(countryName, GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                    }
                }
                System.out.println("Counties Attack to");
                for (String country : countriesAttackTo.keySet()) {
                    System.out.println(country + "->" + allCountriesAttackTo.get(country));
                }
                /*if this list is empty go to next Stage and give correct error*/

                System.out.println("Enter the Full Name of The country :");
                String to = sc.nextLine();
                System.out.println("Number of Army to use (Leave Atleast One Army behind):");
                int fromArmy = sc.nextInt();
                infoOnActions = "Player " + playerHashMap.get(playerId).getName() + " attacks using country " + from + " to " + to + " using " + fromArmy + " armies !";
                int toArmy = countriesAttackTo.get(to);
                playerView.infoAboutAction = infoOnActions;
                playerView.addObserver(VOb);
                playerView.updatingObserver();
                System.out.println("1.To roll Dice one after the other ");
                System.out.println("2.To Auto Roll and get the output");
                System.out.println("Enter Digit :");
                int type = sc.nextInt();
                int[] remainingArmy = new int[2];
                if (type == 1) {
                    remainingArmy = AttackPhase.rollDice(from, fromArmy, to, toArmy);
                } else {
                    remainingArmy = AttackPhase.autoRollDice(from, fromArmy, to, toArmy);
                }
                int x = GameModel.countryHashMap.get(from).getNumberOfSoldiers();
                if (remainingArmy[0] == 0) {
                    System.out.println("Attacking Country " + from + " lost all its armies");
                    GameModel.countryHashMap.get(from).setNumberOfSoldiers(x - fromArmy);
                    GameModel.countryHashMap.get(to).setNumberOfSoldiers(remainingArmy[1]);
                } else {
                    System.out.println("Attacking Country " + from + " won " + to + " country");
                    System.out.println("Remaining Armies :" + remainingArmy[0]);
                    System.out.println("How many armies do you want to leave in " + to + ":");
                    int leave = sc.nextInt();
                    GameModel.countryHashMap.get(to).setNumberOfSoldiers(leave);
                    GameModel.countryHashMap.get(from).setNumberOfSoldiers((x - fromArmy) + (remainingArmy[0] - leave));
                    /*Updating modals for players */
                    int defeatedPlayerId = GameModel.countryHashMap.get(to).getPlayerId();
                    int wonPlayerId = playerId;
                    Player defeated = playerHashMap.get(defeatedPlayerId);
                    defeated.countriesOwned.remove(to);
                    Player won = playerHashMap.get(wonPlayerId);
                    won.countriesOwned.add(to);
                    GameModel.countryHashMap.get(to).setPlayerId(wonPlayerId);
                    playerHashMap.get(wonPlayerId).setShouldGetTheCard(true);
                }

                System.out.println("Do you want too attack (Enter -1 to discontinue):");
                playGame = sc.nextInt();
            } else {
                System.out.println("You do not have any country which is eligible to attack ! Going to next step..");
            }
        }

    }


    /**
     * The method is used to perform fortification Phase
     *
     * @param playerId, id of the player
     */
    public static boolean fortificationPhase(int playerId) {
        Player play = playerHashMap.get(playerId);
        play.GamePhase = "Fortification";
        ViewObserver VOb = new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
        for (String countryName : GameModel.countryHashMap.keySet()) {
            if (GameModel.countryHashMap.get(countryName).getPlayerId() == playerId) {
                System.out.println(countryName + "->" + GameModel.countryHashMap.get(countryName).numberOfSoldiers);
            }
        }
        System.out.println("Press -1 to skip the step (or any other number to continue):");
        Scanner a = new Scanner(System.in);
        int escape = a.nextInt();
        boolean temp = true;
        if (escape != (-1)) {
            while (temp) {
                System.out.println("Enter Country Name to move from:");
                a.nextLine();
                String countryFrom = a.nextLine();
                String countryTo;
                List<String> toCountriesList = new ArrayList<>();
                toCountriesList = GameController.dfsToFindNeighbouringCountryForPlayer(GameModel.countryHashMap.get(countryFrom), playerHashMap.get(playerId));
                if (toCountriesList.size() != 1) {
                    System.out.println("Number of army to move:");
                    int armyToMove = a.nextInt();
                    if (armyToMove > 0 && armyToMove < GameModel.countryHashMap.get(countryFrom).numberOfSoldiers) {
                        for (String cont : toCountriesList) {
                            System.out.println(cont);
                        }
                        System.out.println("Write Country Name  :");
                        Scanner b = new Scanner(System.in);
                        countryTo = b.nextLine();
                        GameModel.countryHashMap.get(countryFrom).numberOfSoldiers -= armyToMove;
                        GameModel.countryHashMap.get(countryTo).numberOfSoldiers += armyToMove;
                        break;
                    }
                } else {
                    System.out.println("The Countries do not have any Adjacent country, Please Select other Country :");
                }
            }
            System.out.println("Updated List After Fortification");
            for (String countryName : GameModel.countryHashMap.keySet()) {
                if (GameModel.countryHashMap.get(countryName).getPlayerId() == playerId) {
                    System.out.println(countryName + "->" + GameModel.countryHashMap.get(countryName).numberOfSoldiers);
                }
            }
        }
        return true;
    }


}
