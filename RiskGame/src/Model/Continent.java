package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model.Continent Class
 *
 * @author Vikram
 * @version 1.0.0
 */
public class Continent {
    public int continentId;
    public String continentName;
    public int controlValue;

    public List<String> Countries = new ArrayList<>();
    public List<String> AdjacentContinents = new ArrayList<>();

    /**
     * This is a constructor of Model.Continent Class which sets Model.Continent name and control value
     *
     * @param name,  name of the Model.Continent
     * @param point, control value
     */
    public Continent(String name, int point) {
        this.continentName = name;
        this.controlValue = point;
    }

    /**
     * This is a constructor of Model.Continent Class which sets Model.Continent id, Model.Continent name and control value
     *
     * @param name,  name of the Model.Continent
     * @param point, control value
     * @param id,    id of the Model.Continent
     */
    public Continent(int id, String name, int point) {
        this.continentId = id;
        this.continentName = name;
        this.controlValue = point;
    }

    /**
     * This method Gets Model.Continent name
     *
     * @return continentName String
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * This method sets Model.Continent name
     *
     * @param continentName, name of the Model.Continent
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    /**
     * This method Gets Model.Continent control value
     *
     * @return continentName String
     */
    public int getControlValue() {
        return controlValue;
    }

    /**
     * This method sets Model.Continent control value
     *
     * @param controlValue
     */
    public void setControlValue(int controlValue) {
        this.controlValue = controlValue;
    }

    /**
     * This method Gets Model.Continent name
     *
     * @return continentName String
     */
    public List<String> getCountries() {
        return Countries;
    }

    /**
     * This method sets Countries
     *
     * @param countries, lists the countries
     */
    public void setCountries(List<String> countries) {
        Countries = countries;
    }

    /**
     * This method Gets the list of Adjacent Countries
     *
     * @return AdjacentContinents List<String>
     */
    public List<String> getAdjacentContinents() {
        return AdjacentContinents;
    }

    /**
     * This method sets the list of Adjacent Countries
     *
     * @param adjacentContinents
     */
    public void setAdjacentContinents(List<String> adjacentContinents) {
        AdjacentContinents = adjacentContinents;
    }

    /**
     * This method adds a Model.Country
     *
     * @param countryName, name of the Model.Country
     */
    public void insertCountry(String countryName) {
        this.Countries.add(countryName);
    }

    /**
     * This method removes a Model.Country
     *
     * @param countryName, name of the Model.Country
     */
    public void RemoveCountry(String countryName) {
        this.Countries.remove(new String("countryName"));
    }

}
