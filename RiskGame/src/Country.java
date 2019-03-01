import java.util.ArrayList;
import java.util.List;

/**
 * @author Hemanshu
 * @version 1.0.0
 * @date 2019-02-19
 */
public class Country {
    int id;
    String countryName;
    String ParentContinent;
    List<String> adjacentCountries =  new ArrayList<>();
    int numberOfSoldiers=0;
    int xCoordinate;
    int yCoordinate;
    Integer PlayerId;

    boolean isVisited;

    /**
     * This is a Constructor for Country class which sets countryName, id and continentName
     *
     * @param id ,id of the country
     * @param CountryName,name of the country
     * @param ContinentName, name of the continent
     *
     */
    public Country(int id, String CountryName, String ContinentName)
    {
        this.id = id;
        this.countryName = CountryName;
        this.ParentContinent = ContinentName;
        this.isVisited = false;
    }

    public Country(int id, String CountryName) {
        this.id = id;
        this.countryName = CountryName;
        this.isVisited = false;
    }

    public Integer getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(Integer playerId) {
        PlayerId = playerId;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getParentContinent() {
        return ParentContinent;
    }

    public void setParentContinent(String parentContinent) {
        ParentContinent = parentContinent;
    }

    public List<String> getAdjacentCountries() {
        return adjacentCountries;
    }

    public void setAdjacentCountries(List<String> adjacentCountries) {
        this.adjacentCountries = adjacentCountries;
    }

    public int getNumberOfSoldiers() {
        return numberOfSoldiers;
    }

    public void setNumberOfSoldiers(int numberOfSoldiers) {
        this.numberOfSoldiers = numberOfSoldiers;
    }

    public void addAdjacentCountry(String name) {
        this.adjacentCountries.add(name);
    }

    public void removeAdjacentCountry(String name) {
        this.adjacentCountries.remove(new String(name));
    }
}
