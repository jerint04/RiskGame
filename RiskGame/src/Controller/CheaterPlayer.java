package Controller;

import Model.*;
import View.PlayerDominationObserverView;

import java.util.ArrayList;
import java.util.List;

import static Model.GameModel.playerHashMap;

/**
 * Cheater Player class
 * @author Hemanshu
 */
public class CheaterPlayer implements Strategy {
    @Override
    /**
     * This method is used to calculate army during reinforcement phase
     * @param playerId, id of the player
     */
    public void armyCalculationDuringReinforcement(int playerId) {
        System.out.println("Cheater Player working fine.....");

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
        playerHashMap.get(playerId).numberOfInfantry = +((armyToAllocate < 3) ? 3 : armyToAllocate);//doubling the army
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
//
        for (String countryName : playerHashMap.get(playerId).getCountriesOwned()) {
            GameModel.countryHashMap.get(countryName).numberOfSoldiers = GameModel.countryHashMap.get(countryName).numberOfSoldiers * 2;

        }
        for (String countryName :playerHashMap.get(playerId).getCountriesOwned()) {

            System.out.println("number of soldier:" + GameModel.countryHashMap.get(countryName).numberOfSoldiers);
        }


    }


    @Override
    /**
     * This method is used to perform exchange Cards for armies
     * @param playerId, id of the player
     */
    public void exchangeCardsForArmies(int PlayerId) {

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
    /**
     * This method is used to perform player attack
     * @param playerId, id of the player
     */
    public void playerAttack(int playerId) {

        List<String> countriesOwnedab = new ArrayList<>();
        countriesOwnedab  = playerHashMap.get(playerId).getCountriesOwned();
        List<String> countriesToAdd = new ArrayList<>();
//        List<String> countryToAdd =
        for (String Country : countriesOwnedab) {
            for (String countryAdjascent : GameModel.countryHashMap.get(Country).adjacentCountries) {

                if (GameModel.countryHashMap.get(countryAdjascent).getPlayerId() != playerId) {
                    int previousPlayerId = GameModel.countryHashMap.get(countryAdjascent).getPlayerId();
                    GameModel.countryHashMap.get(countryAdjascent).setPlayerId(playerId);
                    GameModel.countryHashMap.get(countryAdjascent).numberOfSoldiers = 1;
                    playerHashMap.get(previousPlayerId).countriesOwned.remove(countryAdjascent);
                    countriesToAdd.add(countryAdjascent);
//                    playerHashMap.get(playerId).countriesOwned.add(countryAdjascent);
                }
            }
        }
        playerHashMap.get(playerId).countriesOwned.addAll(countriesToAdd);

        System.out.println("countries owned:" + playerHashMap.get(playerId).getCountriesOwned());
    }


    @Override
    /**
     * This method is used to perform fortification phase
     * @param playerId, id of the player
     * @return true, boolean
     */
    public boolean fortificationPhase(int playerId) {


        Player play = playerHashMap.get(playerId);

        List<String> countryToFortifyList = new ArrayList<>();
        for (String countryOwn : play.countriesOwned) {
            if (GameModel.countryHashMap.get(countryOwn).getNumberOfSoldiers() >= 1) {
                boolean toAdd = false;
                for (String country : GameModel.countryHashMap.get(countryOwn).getAdjacentCountries()) {
                    if (GameModel.countryHashMap.get(country).PlayerId != playerId) {
                        toAdd = true;
                    }
                }
                if (toAdd) {
                    countryToFortifyList.add(countryOwn);
                }


            }


            for (String countryName : countryToFortifyList) {
                GameModel.countryHashMap.get(countryName).numberOfSoldiers = GameModel.countryHashMap.get(countryName).numberOfSoldiers * 2;
            }
        }
        return true;

    }
}