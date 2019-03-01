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
    List<String> adjacentCountries = new ArrayList<>();
    int numberOfSoldiers;
    int xCoordinate;
    int yCoordinate;
    Integer PlayerId;
    boolean isVisited;

    /**
     * This is a Constructor for Country class which sets countryName, id and continentName
     *
     * @param id               ,id of the country
     * @param CountryName,name of the country
     * @param ContinentName,   name of the continent
     */
    public Country(int id, String CountryName, String ContinentName) {
        this.id = id;
        this.countryName = CountryName;
        this.ParentContinent = ContinentName;
    }

    /**
     * This is a Constructor for Country class which sets countryName and id
     *
     * @param id               ,id of the country
     * @param CountryName,name of the country
     */
    public Country(int id, String CountryName) {
        this.id = id;
        this.countryName = CountryName;
    }

    /**
     * This method gets x-coordinate
     *
     * @return xCoordinate int
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * This method sets x-coordinate
     *
     * @param xCoordinate
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * This method gets y-coordinate
     *
     * @return yCoordinate int
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * This method sets y-coordinate
     *
     * @param yCoordinate
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * This method gets Country id
     *
     * @return id, int
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets Country id
     *
     * @param id, int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method gets Country name
     *
     * @return countryName, String
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * This method sets Country name
     *
     * @param countryName, name of the Country
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * This method gets ParentContinent
     *
     * @return ParentContinent, String
     */
    public String getParentContinent() {
        return ParentContinent;
    }

    /**
     * This method sets ParentContinent
     *
     * @param parentContinent
     */
    public void setParentContinent(String parentContinent) {
        ParentContinent = parentContinent;
    }

    /**
     * This method gets Adjacent Countries
     *
     * @return adjacentCountries,
     * returns the list of adjacent countries
     */
    public List<String> getAdjacentCountries() {
        return adjacentCountries;
    }

    /**
     * This method sets Adjacent Countries
     *
     * @param adjacentCountries, adds the list of adjacent countries
     */
    public void setAdjacentCountries(List<String> adjacentCountries) {
        this.adjacentCountries = adjacentCountries;
    }

    /**
     * This method gets the number of soldiers
     *
     * @return numberOfSoldiers int
     */
    public int getNumberOfSoldiers() {
        return numberOfSoldiers;
    }

    /**
     * This method sets the number of soldiers
     *
     * @param numberOfSoldiers, the total number of soldiers
     */
    public void setNumberOfSoldiers(int numberOfSoldiers) {
        this.numberOfSoldiers = numberOfSoldiers;
    }

    /**
     * This method adds adjacent country
     *
     * @param name, name of the adjacent country
     */
    public void addAdjacentCountry(String name) {
        this.adjacentCountries.add(name);
    }

    /**
     * This method removes adjacent country
     *
     * @param name, name of the adjacent country
     */
    public void removeAdjacentCountry(String name) {
        this.adjacentCountries.remove(new String(name));
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Integer getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(Integer playerId) {
        PlayerId = playerId;
    }

}
