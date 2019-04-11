import Controller.GameController;
import Controller.ReadMap;
import Controller.ValidateMap;
import Model.GameModel;
import Model.Player;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static Controller.AttackPhase.autoRollDice;
import static Model.GameModel.playerHashMap;
import static org.junit.Assert.assertEquals;


public class TestAttackPhase {
    static int[] val;


    @BeforeClass
    public static void BeforeClass() {
        ReadMap.readMap("./assets/maps/updated.map");
        boolean value = ValidateMap.validateMap();
        //String s1 = Boolean.toString(value);

        Player play = new Player(0, "Vikram","human");
        GameModel.PlayerList.add(play);
        playerHashMap.put(0, play);
        Player play1 = new Player(1, "Jerin","human");
        GameModel.PlayerList.add(play1);
        playerHashMap.put(1, play1);
        Player play2 = new Player(2, "Hemanshu","human");
        GameModel.PlayerList.add(play2);
        playerHashMap.put(2, play2);
        GameModel.PlayerList.get(0).setNumberOfInfantry(35);
        GameModel.PlayerList.get(1).setNumberOfInfantry(35);
        GameModel.PlayerList.get(2).setNumberOfInfantry(35);
        GameModel.countryHashMap.get("ind").setPlayerId(0);
        GameModel.countryHashMap.get("ind").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("pak").setPlayerId(1);
        GameModel.countryHashMap.get("pak").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("ban").setPlayerId(2);
        GameModel.countryHashMap.get("ban").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("uk").setPlayerId(0);
        GameModel.countryHashMap.get("uk").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("china").setPlayerId(1);
        GameModel.countryHashMap.get("china").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("fran").setPlayerId(0);
        GameModel.countryHashMap.get("fran").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("rus").setPlayerId(1);
        GameModel.countryHashMap.get("rus").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("nep").setPlayerId(0);
        GameModel.countryHashMap.get("nep").setNumberOfSoldiers(1);
        GameModel.countryHashMap.get("ger").setPlayerId(2);
        GameModel.countryHashMap.get("ger").setNumberOfSoldiers(1);
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
        //AttackPhase.playerAttackTurnHumanPlayer(0);
        val = autoRollDice("ind", 40, "pak", 2);

    }

    /*
     * This method runs after all test cases were ran
     */
    @AfterClass
    public static void AfterClass() {
        System.out.println("Left TestAttackPhase  Class");
    }

    @Test
    public void TestCase1() {
        assertEquals(2, val.length);


    }

    @Test
    public void TestCase2() {
        assertEquals(0, val[1]);
    }
}


