
import Model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Vikram
 * @version 1.0.0
 */


public class TestPlayer {
    Player player;
    public List<String> cards = new ArrayList<>();
    public List<String> countriesOwned = new ArrayList<>();

    @Before
    public void BeforeTestCase() {

        player = new Player(1, "Vikram","human");
        Player.setGamePhase("Fortification");
        player.setCards(cards);
        player.setCountriesOwned(countriesOwned);
    }

    @Test
    public void TestCase1() {

        int id = player.getPlayerId();
        assertEquals(1, id);

    }

    @Test
    public void TestCase2() {

        player.setName("Vikram");

    }
    @Test
    public void TestCase3() {

        String name = player.getName();
        assertEquals("Vikram", name);
    }

    @Test
    public void TestCase4() {



        boolean value = player.getShouldGetTheCard();
        String s1 = Boolean.toString(value);

        assertEquals("false", s1);


    }

    @Test
    public void TestCase5() {

        String name = player.getName();
        assertEquals("Vikram", name);

    }

    @Test
    public void TestGamePhase() {

        String val = Player.getGamePhase();
        assertEquals("Fortification", val);

    }

    @Test
    public void TestCards() {
        List<String> Cards = new ArrayList<>();
        Cards = player.getCards();
        assertEquals(Cards, cards);

    }

    @Test
    public void TestCountriesOwned() {

        List<String> CountriesOwned = new ArrayList<>();
        CountriesOwned = player.getCountriesOwned();
        assertEquals(CountriesOwned, countriesOwned);
    }


    @Test
    public void TestPlayerType() {

         String Type= player.getPlayerType();
        assertEquals(Type, "human");
    }

}

