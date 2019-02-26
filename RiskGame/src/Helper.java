/**
 * @author Hemanshu
 * @date 2019-02-22
 */
public class Helper {
    static int countryCountId = 0; // The count starts from 1 and not from 0.. Please pay attention to this and remember
    static  int continentCountId = 0; // The count starts from 1 and not from 0.. Please pay attention to this and remember

    public static int getNewCountryCountId() {
        countryCountId = countryCountId +1;
        return countryCountId;
    }

    public static int getNewContinentCountId() {
        continentCountId = continentCountId +1;
        return continentCountId;
    }

    public static int getContinentCountId() {
        return continentCountId;
    }

    public static void setContinentCountId(int continentCountId) {
        Helper.continentCountId = continentCountId;
    }

    public static int getCountryCountId() {
        return countryCountId;
    }

    public static void setCountryCountId(int countryCountId) {
        Helper.countryCountId = countryCountId;
    }
}
