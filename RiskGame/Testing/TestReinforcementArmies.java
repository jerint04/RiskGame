import Controller.GameController;
import Controller.ReadMap;
import Controller.ValidateMap;
import Model.GameModel;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

import static Model.GameModel.playerHashMap;
import static org.junit.Assert.assertEquals;

/**
 * @author Hemanshu
 * @version 1.0.0
 */
public class TestReinforcementArmies {
    @Before
    public  void BeforeClass()

    {
        ReadMap.readMap("E:/APP/RiskGame/RiskGame/assets/maps/Asia.map");
        boolean value = ValidateMap.validateMap();
        //String s1 = Boolean.toString(value);

        Player play = new Player(0, "Vikram");
        GameModel.PlayerList.add(play);
        playerHashMap.put(0, play);
        Player play1 = new Player(1, "Jerin");
        GameModel.PlayerList.add(play1);
        playerHashMap.put(0, play1);
        Player play2 = new Player(2, "Hemanshu");
        GameModel.PlayerList.add(play2);
        playerHashMap.put(0, play2);
        GameModel.PlayerList.get(0).setNumberOfInfantry(35);
        GameModel.PlayerList.get(1).setNumberOfInfantry(35);
        GameModel.PlayerList.get(2).setNumberOfInfantry(35);
        GameModel.countryHashMap.get("in").setPlayerId(0);
        GameModel.countryHashMap.get("in").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("pak").setPlayerId(1);
        GameModel.countryHashMap.get("pak").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("ban").setPlayerId(2);
        GameModel.countryHashMap.get("ban").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("uk").setPlayerId(0);
        GameModel.countryHashMap.get("uk").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("china").setPlayerId(1);
        GameModel.countryHashMap.get("china").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("pol").setPlayerId(2);
        GameModel.countryHashMap.get("pol").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("fr").setPlayerId(0);
        GameModel.countryHashMap.get("fr").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("rus").setPlayerId(1);
        GameModel.countryHashMap.get("rus").setNumberOfSoldiers(1);
        for (String countryName : GameModel.countryHashMap.keySet()) {
            int playerId = GameModel.countryHashMap.get(countryName).getPlayerId();
            Player temp = playerHashMap.get(playerId);
            temp.countriesOwned.add(countryName);
        }

        for (int p : playerHashMap.keySet()) {
            playerHashMap.get(p).numberOfInfantry = playerHashMap.get(p).numberOfInfantry - playerHashMap.get(p).countriesOwned.size();
            System.out.println("Countries owned by the player -->" + playerHashMap.get(p).getCountriesOwned() + " Size of infantry after allocating -->" + playerHashMap.get(p).numberOfInfantry + " Total country owned by the player -->" + playerHashMap.get(p).countriesOwned.size());
        }
        GameController.addInfantryToCountry(playerHashMap.get(0).countriesOwned.get(0), 0, 32);
        playerHashMap.get(0).numberOfInfantry = playerHashMap.get(0).numberOfInfantry - 32;
        GameController.addInfantryToCountry(playerHashMap.get(1).countriesOwned.get(1), 0, 32);
        playerHashMap.get(1).numberOfInfantry = playerHashMap.get(1).numberOfInfantry - 32;
        GameController.addInfantryToCountry(playerHashMap.get(2).countriesOwned.get(1), 0, 33);
        playerHashMap.get(2).numberOfInfantry = playerHashMap.get(2).numberOfInfantry - 33;
    }
@Test
    public static void TestCase(){

        assertEquals(0,playerHashMap.get(2).numberOfInfantry);
}


    }
