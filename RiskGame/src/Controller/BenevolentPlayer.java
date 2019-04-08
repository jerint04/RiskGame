package Controller;

import Model.*;
import View.PlayerDominationObserverView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Model.GameModel.playerHashMap;

/**
 * @author Hemanshu
 * @date 2019-04-08
 */
public class BenevolentPlayer implements Strategy {


    @Override
    public void armyCalculationDuringReinforcement(int playerId) {
        System.out.println("Benevolent Player working fine.....");

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
    public void armyPlacementDuringReinforcement(int playerId) {
//        Scanner sc = new Scanner(System.in);
        System.out.println(" Infantry for the current players is : " + playerHashMap.get(playerId).numberOfInfantry);

        while (playerHashMap.get(playerId).numberOfInfantry != 0) {
            int i = 0;
            int minArmies = 50;
            int countryIndex = -1;
            for (String countryName : playerHashMap.get(playerId).getCountriesOwned()) {
                System.out.println(i + ":" + countryName + "->" + GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                if (GameModel.countryHashMap.get(countryName).getNumberOfSoldiers() < minArmies) {
                    minArmies = GameModel.countryHashMap.get(countryName).getNumberOfSoldiers();
                    countryIndex = i;
                }
                i = i + 1;
            }

            System.out.println("Enter the number of armies to be allocated :");
            int numOfArmies = playerHashMap.get(playerId).numberOfInfantry;
            System.out.println("Enter the serial number of the country :");
            int countrySerialNum = countryIndex;
            GameController.addInfantryToCountry(playerHashMap.get(playerId).countriesOwned.get(countrySerialNum), playerId, numOfArmies);
            playerHashMap.get(playerId).numberOfInfantry = playerHashMap.get(playerId).numberOfInfantry - numOfArmies;
        }
    }


    @Override
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


            // notifyObservers();
        } else if (play.Cards.get(cardIndexFirst).equals(play.Cards.get(cardIndexsecond)) && play.Cards.get(cardIndexFirst).equals(play.Cards.get(cardIndexThird))) {
            play.Cards.remove(cardNameFirst);
            play.Cards.remove(cardNameSecond);
            play.Cards.remove(cardNameThird);
            play.addObserver(Ob);
            play.setArmiesInExcahngeOfcards(play.turn * 5);
            play.numberOfInfantry += play.armiesInExcahngeOfcards;
            play.turn++;
            play.updatingObserver();
            //notifyObservers();
        }
    }


    @Override
    public void playerAttack(int playerId) {
        Player playerView = playerHashMap.get(playerId);
        playerView.GamePhase = "Attack Phase";
        ViewObserver VOb = new ViewObserver();
        playerView.addObserver(VOb);
        playerView.updatingObserver();
        String infoOnActions = "";

        infoOnActions = "Skipping Attack Phase";
        playerView.infoAboutAction = infoOnActions;
        playerView.addObserver(VOb);
        playerView.updatingObserver();


    }


    @Override
    public boolean fortificationPhase(int playerId) {
        /*TODO : Test this  Function*/
        Player play = playerHashMap.get(playerId);
        play.GamePhase = "Fortification";
        ViewObserver VOb = new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();

        List<String> countryToFortifyList = new ArrayList<>();

        int minArmies = 50;
        String countryWithMinimumArmy = "";
        for (String countryName : play.getCountriesOwned()) {
            if (GameModel.countryHashMap.get(countryName).getNumberOfSoldiers() < minArmies) {
                minArmies = GameModel.countryHashMap.get(countryName).getNumberOfSoldiers();
                countryWithMinimumArmy = countryName;
            }
        }

        List<String> countriesOwned = playerHashMap.get(playerId).getCountriesOwned();
        int maxArmy = 0;
        String countryWithMaxArmy = "";
        for (String Country : countriesOwned) {
            if (GameModel.countryHashMap.get(Country).getNumberOfSoldiers() > maxArmy) {
                maxArmy = GameModel.countryHashMap.get(Country).getNumberOfSoldiers();
                countryWithMaxArmy = Country;
            }
        }

        int transferFrom = GameModel.countryHashMap.get(countryWithMaxArmy).numberOfSoldiers;
        GameModel.countryHashMap.get(countryWithMinimumArmy).numberOfSoldiers += (int)(transferFrom/2);
        GameModel.countryHashMap.get(countryWithMaxArmy).numberOfSoldiers = GameModel.countryHashMap.get(countryWithMaxArmy).numberOfSoldiers - (int)(transferFrom/2);
        return true;
    }

}
