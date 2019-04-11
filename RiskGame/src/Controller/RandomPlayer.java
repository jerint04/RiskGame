package Controller;

import Model.*;
import View.PlayerDominationObserverView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static Model.GameModel.playerHashMap;

/**
 * Random Player class
 *
 * @author Hemanshu
 */
public class RandomPlayer implements Strategy {

    @Override
    /**
     * This method is used to calculate army during reinforcement phase
     * @param playerId, id of the player
     */
    public void armyCalculationDuringReinforcement(int playerId) {
        System.out.println("Aggressive Player working fine.....");

        Player play = playerHashMap.get(playerId);
        play.GamePhase = "Reinforcement";
        ViewObserver VOb = new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
//        Scanner sc = new Scanner(System.in);
        Player temp = playerHashMap.get(playerId);
        if (GameModel.playerHashMap.get(playerId).getShouldGetTheCard()) {
            System.out.println("Player " + playerId + " has earned the card as he Won a country in previous attack");
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

            System.out.println("You have 3 cards, Exchanging Cards");
            int choice = 1;
            if (choice == 1) {
                exchangeCardsForArmies(playerId);
            }

        } else if (temp.Cards.size() >= 5) {
            System.out.println("You have to exchange cards to proceed");
            exchangeCardsForArmies(playerId);

        }
        Player playerView = new Player();
        PlayerDominationObserverView POb = new PlayerDominationObserverView();
        playerView.addObserver(POb);
        playerView.updatingObserver();
    }

    @Override
    /**
     * This method is used toplace army during reinforcement phase
     * @param playerId, id of the player
     */
    public void armyPlacementDuringReinforcement(int playerId) {
//        Scanner sc = new Scanner(System.in);
        System.out.println(" Infantry for the current players is : " + playerHashMap.get(playerId).numberOfInfantry);
        /* Commenting the loop */
        /*while (playerHashMap.get(playerId).numberOfInfantry != 0) {
            int i = 0;
            int maxArmies = 0;
            int countryIndex = -1;
            for (String countryName : playerHashMap.get(playerId).getCountriesOwned()) {
                System.out.println(i + ":" + countryName + "->" + GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                if (GameModel.countryHashMap.get(countryName).getNumberOfSoldiers() > maxArmies) {
                    maxArmies = GameModel.countryHashMap.get(countryName).getNumberOfSoldiers();
                    countryIndex = i;
                }
                i = i + 1;
            }
*/
        System.out.println("Enter the number of armies to be allocated :");
        int numOfArmies = playerHashMap.get(playerId).numberOfInfantry;
        Random rand = new Random();
        int randomSerialNumber = rand.nextInt(playerHashMap.get(playerId).countriesOwned.size());
        System.out.println("Enter the serial number of the country :");
        int countrySerialNum = randomSerialNumber;
        GameController.addInfantryToCountry(playerHashMap.get(playerId).countriesOwned.get(countrySerialNum), playerId, numOfArmies);
        playerHashMap.get(playerId).numberOfInfantry = playerHashMap.get(playerId).numberOfInfantry - numOfArmies;
    }


    @Override
    /**
     * This method is used to perform exchange Cards for armies
     * @param playerId, id of the player
     */
    public void exchangeCardsForArmies(int PlayerId) {

//        Scanner sc = new Scanner(System.in);
        Player play = playerHashMap.get(PlayerId);
        UpdateFromObserver Ob = new UpdateFromObserver();
        play.addObserver(Ob);
        play.updatingObserver();
        System.out.println("Enter the serial number of the cards to be exchanged(3 cards in a set)");
        int cardIndexFirst = 0;
        int cardIndexsecond = 1;
        int cardIndexThird = 2;
        String cardNameFirst = play.Cards.get(cardIndexFirst);
        String cardNameSecond = play.Cards.get(cardIndexsecond);
        String cardNameThird = play.Cards.get(cardIndexThird);
        if (play.Cards.get(cardIndexFirst) != play.Cards.get(cardIndexsecond) && play.Cards.get(cardIndexsecond) != play.Cards.get(cardIndexThird) && play.Cards.get(cardIndexThird) != play.Cards.get(cardIndexFirst)) {
            play.Cards.remove(cardNameFirst);
            play.Cards.remove(cardNameSecond);
            play.Cards.remove(cardNameThird);
            play.addObserver(Ob);
            play.setArmiesInExcahngeOfcards(play.turn * 5);
            play.numberOfInfantry += play.armiesInExcahngeOfcards;
            play.turn++;
            play.updatingObserver();


        } else if (play.Cards.get(cardIndexFirst).equals(play.Cards.get(cardIndexsecond)) && play.Cards.get(cardIndexFirst).equals(play.Cards.get(cardIndexThird))) {
            play.Cards.remove(cardNameFirst);
            play.Cards.remove(cardNameSecond);
            play.Cards.remove(cardNameThird);
            play.addObserver(Ob);
            play.setArmiesInExcahngeOfcards(play.turn * 5);
            play.numberOfInfantry += play.armiesInExcahngeOfcards;
            play.turn++;
            play.updatingObserver();
        }
    }


    @Override
    /**
     * This method is used to perform player attack
     * @param playerId, id of the player
     */
    public void playerAttack(int playerId) {
        Player playerView = playerHashMap.get(playerId);
        playerView.GamePhase = "Attack Phase";
        ViewObserver VOb = new ViewObserver();
        playerView.addObserver(VOb);
        playerView.updatingObserver();
        String infoOnActions = "";
        Random rand = new Random();

        /*Finding the Country with the random country to attack with*/
        List<String> countriesTemp = new ArrayList<>();
        HashMap<String, Integer> allCountriesAttackTo = new HashMap<String, Integer>();
        List<String> countriesThatCanAttack = new ArrayList<>();
        for (String allCountries : playerView.getCountriesOwned()) {
            boolean addCountryToAttack = false;
            if (GameModel.countryHashMap.get(allCountries).getNumberOfSoldiers() > 1) {
                countriesTemp = GameModel.countryHashMap.get(allCountries).getAdjacentCountries();
                for (String countryName : countriesTemp) {
                    if (GameModel.countryHashMap.get(countryName).PlayerId != playerId) {
                        addCountryToAttack = true;
                    }
                }
                if (addCountryToAttack) {
                    countriesThatCanAttack.add(allCountries);
                }
            }
        }

        if (countriesThatCanAttack.size() != 0) {
            for (String from : countriesThatCanAttack) {
                if (GameModel.countryHashMap.get(from).getNumberOfSoldiers() > 1) {
                    int fromArmy = GameModel.countryHashMap.get(from).getNumberOfSoldiers() - 1;

                    List<String> countriesAttack = new ArrayList<>();
                    countriesTemp = GameModel.countryHashMap.get(from).getAdjacentCountries();
                    for (String countryName : countriesTemp) {
                        if (GameModel.countryHashMap.get(countryName).PlayerId != playerId) {
                            countriesAttack.add(countryName);
                        }
                    }

                    if (countriesAttack.size() != 0) {
                        int[] remainingArmy = new int[2];
                        int a = rand.nextInt(countriesAttack.size());
                        String to = countriesAttack.get(a);
                        int toArmy = GameModel.countryHashMap.get(to).numberOfSoldiers;
                        remainingArmy = AttackPhase.autoRollDice(from, fromArmy, to, toArmy);
                        if (remainingArmy[0] == 0) {
                            System.out.println("Attacking Country " + from + " lost all its armies");
                            GameModel.countryHashMap.get(from).setNumberOfSoldiers(1);
                            GameModel.countryHashMap.get(to).setNumberOfSoldiers(remainingArmy[1]);
                        } else {
                            System.out.println("Attacking Country " + from + " won " + to + " country");
                            System.out.println("Remaining Armies :" + remainingArmy[0]);
                            int leave = 1;
                            GameModel.countryHashMap.get(to).setNumberOfSoldiers(leave);
                            GameModel.countryHashMap.get(from).setNumberOfSoldiers((GameModel.countryHashMap.get(from).numberOfSoldiers - fromArmy) + (remainingArmy[0] - leave));
//                        Updating modals for players
                            int defeatedPlayerId = GameModel.countryHashMap.get(to).getPlayerId();
                            int wonPlayerId = playerId;
                            Player defeated = playerHashMap.get(defeatedPlayerId);
                            defeated.countriesOwned.remove(to);
                            Player won = playerHashMap.get(wonPlayerId);
                            won.countriesOwned.add(to);
                            GameModel.countryHashMap.get(to).setPlayerId(wonPlayerId);
                            playerHashMap.get(wonPlayerId).setShouldGetTheCard(true);
                        }
                    }
                }
            }
        }


    }

    @Override
    /**
     * This method is used to perform fortification phase
     * @param playerId, id of the player
     * @return true, boolean
     */
    public boolean fortificationPhase(int playerId) {
        /*TODO : Test this  Function*/
        Player play = playerHashMap.get(playerId);
        play.GamePhase = "Fortification";
        ViewObserver VOb = new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
        Random rand = new Random();

        List<String> countryToFortifyList = new ArrayList<>();
        for (String countryOwn : play.countriesOwned) {
            if (GameModel.countryHashMap.get(countryOwn).getNumberOfSoldiers() > 4) {
                countryToFortifyList.add(countryOwn);
            }
        }

        if (countryToFortifyList.size() > 0 && play.countriesOwned.size() > 1) {
            int toInd = rand.nextInt(play.getCountriesOwned().size());
            String fortifyCountry = play.getCountriesOwned().get(toInd);
            int fromInd = rand.nextInt(countryToFortifyList.size());
            String parentCountry = countryToFortifyList.get(fromInd);

            int transferFrom = GameModel.countryHashMap.get(parentCountry).numberOfSoldiers;
            GameModel.countryHashMap.get(fortifyCountry).numberOfSoldiers += (int) (transferFrom / 2);
            GameModel.countryHashMap.get(parentCountry).numberOfSoldiers = GameModel.countryHashMap.get(parentCountry).numberOfSoldiers - (int) (transferFrom / 2);

        }
        return true;
    }


}
