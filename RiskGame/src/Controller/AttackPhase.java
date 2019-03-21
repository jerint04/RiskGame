package Controller;
import Model.GameModel;
import Model.Player;
import Model.ViewObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Model.GameModel.playerHashMap;

/**
 * @author Hemanshu
 * @date 2019-03-19
 */
public class AttackPhase {

    public static void PlayerAttackTurn(int playerId){
        Player play=playerHashMap.get(playerId);
        play.GamePhase="Attack Phase";
        ViewObserver VOb=new ViewObserver();
        play.addObserver(VOb);
        play.updatingObserver();
        List<String> countriesOwned = GameModel.playerHashMap.get(playerId).getCountriesOwned();
//        List<String> countriesAllowedToAttack= new ArrayList<String>(){};
//        List<String> countriesToAttack = new ArrayList<>();
        HashMap<String,Integer> countriesAllowedToAttack = new HashMap<String, Integer>();
        List<String> countriesTemp = new ArrayList<>();
        boolean addCountryToOwn = false;
        HashMap<String,Integer> countriesToAttack = new HashMap<String, Integer>();
        for(String Country : countriesOwned){
            addCountryToOwn = false;
            if(GameModel.countryHashMap.get(Country).getNumberOfSoldiers()>1){
                countriesTemp = GameModel.countryHashMap.get(Country).getAdjacentCountries();
                for(String countryName : countriesTemp){
                    if(GameModel.countryHashMap.get(countryName).PlayerId != playerId){
                        addCountryToOwn = true;
                        countriesToAttack.put(countryName , GameModel.countryHashMap.get(countryName).getNumberOfSoldiers());
                    }
                }
            }
            if(addCountryToOwn){
                countriesAllowedToAttack.put(Country, GameModel.countryHashMap.get(Country).getNumberOfSoldiers());
            }
        }

        System.out.println("Counties That Can Attack");
        for(String country : countriesAllowedToAttack.keySet() ){
            System.out.println(country+"->"+countriesAllowedToAttack.get(country));
        }

        System.out.println("Counties Attack to");
        for(String country : countriesToAttack.keySet() ){
            System.out.println(country+"->"+countriesToAttack.get(country));
        }




        /*for (String countryName : GameModel.countryHashMap.keySet()) {
            if (GameModel.countryHashMap.get(countryName).getPlayerId() == playerId) {
                System.out.println(countryName + "->" + GameModel.countryHashMap.get(countryName).numberOfSoldiers);
            }
        }*/
    }
}
