import java.util.ArrayList;
import java.util.List;

public class Country {
    int id;
    String countryName;
    String ParentContinent;
    List<String> adjacentCountries =  new ArrayList<>();
    int numberOfSoldiers;

    public Country(int id, String name){
        this.id = id;
        this.countryName = name;
    }

    public void addAdjacentCountry(String name){
        this.adjacentCountries.add(name);
    }

    public void removeAdjacentCountry(String name){
        this.adjacentCountries.remove(new String(name));
    }
}
