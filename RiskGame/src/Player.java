import java.util.*;

/**
 * @author Hemanshu
 * @date 2019-02-12 
 */
public class Player 
{
    List<String> Cards= new ArrayList<>();
    List<String> countriesOwned= new ArrayList<>();
    boolean alive;
    String Name;

    public Player( String name) {
        this.alive = true;
        Name = name;
    }

    public List<String> getCards() {
        return Cards;
    }

    public List<String> getCountriesOwned() {
        return countriesOwned;
    }

    public boolean isAlive() {
        return alive;
    }

    public String getName() {
        return Name;
    }

    public void setCards(List<String> cards) {
        Cards = cards;
    }

    public void setCountriesOwned(List<String> countriesOwned) {
        this.countriesOwned = countriesOwned;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setName(String name) {
        Name = name;
    }
}
