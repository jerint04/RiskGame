package Model;

import Controller.GameController;

import java.util.Observable;
import java.util.Observer;

public class PlayerDominationObserverView implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        int countrySize=0;
        String dominationString="";

        //float percentageOfMap;
        String continentsNameOwned=null;
        for (int playerId : GameModel.playerHashMap.keySet()) {
            countrySize+=GameModel.playerHashMap.get(playerId).countriesOwned.size();
        }

        for (int playerId : GameModel.playerHashMap.keySet()) {
            for (String continentName : GameModel.continentHashMap.keySet()) {
            if(GameModel.playerHashMap.get(playerId).countriesOwned.containsAll(GameModel.continentHashMap.get(continentName).Countries)){

                continentsNameOwned+=continentName;
            }

            }
        }
        for (int playerId : GameModel.playerHashMap.keySet()) {
            int countryOwnedByPlayers=GameModel.playerHashMap.get(playerId).countriesOwned.size();
            double percentageOfMap=((double) (countryOwnedByPlayers)/countrySize)*100;
            System.out.println("Player Domination View");
            System.out.println(" Player Name is :"+GameModel.playerHashMap.get(playerId).getName() +" pecenatge of map controlled by the player :"+percentageOfMap+" total number of armies :"+ GameModel.playerHashMap.get(playerId).getNumberOfInfantry()+" continents owned by the player are "+continentsNameOwned);
             dominationString=dominationString+" Player Name is :"+GameModel.playerHashMap.get(playerId).getName() +" pecenatge of map controlled by the player is :"+Math.round(percentageOfMap)+" total number of armies owned by player is:"+ GameModel.playerHashMap.get(playerId).getNumberOfInfantry()+" continents owned by the player are "+continentsNameOwned+ "\n";


        }
        GameController.MssageOnGUIThroughObsever(dominationString);


    }
}
