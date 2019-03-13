package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model.Country Class
 * 
 * @author Hemanshu
 * @version 1.0.0
 */
public class Country {
    public int id;
    public String countryName;
    public String ParentContinent;
    public List<String> adjacentCountries = new ArrayList<>();
    public int numberOfSoldiers;
    public int xCoordinate;
    public int yCoordinate;
    public Integer PlayerId;
    public boolean isVisited;

    /**
     * This is a Constructor for Model.Country class which sets countryName, id and continentName
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
     * This is a Constructor for Model.Country class which sets countryName and id
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
     * @param xCoordinate, x-co-ordinate of a Country
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * This method gets y-coordinate
     *
     * @return yCoordinate int, y-co-ordinate of a Country
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * This method sets y-coordinate
     *
     * @param yCoordinate, y-co-ordinate of a Country
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * This method gets Model.Country id
     *
     * @return id, int
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets Model.Country id
     *
     * @param id, int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method gets Model.Country name
     *
     * @return countryName, String
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * This method sets Model.Country name
     *
     * @param countryName, name of the Model.Country
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
     * @param parentContinent,  parent continent
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

    /**
     * This method returns whether the country is visited or not
     *
     * @return isVisited, returns a boolean value
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * This method sets the status of the country visited
     *
     * @param visited, assigns a boolean value
     */
    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    /**
     * This method gets the player Id
     *
     * @return PlayerId Integer
     */
    public Integer getPlayerId() {
        return PlayerId;
    }

    /**
     * This method sets the player Id
     *
     * @param playerId, id of the Model.Player
     */
    public void setPlayerId(Integer playerId) {
        PlayerId = playerId;
    }

}
