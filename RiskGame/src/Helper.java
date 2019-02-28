/**
 * Helper Class
 * @author Hemanshu
 * @version 1.0.0
 * @date 2019-02-22
 */
public class Helper {
    static int countryCountId = 0; // The count starts from 1 and not from 0.. Please pay attention to this and remember
    static  int continentCountId = 0; // The count starts from 1 and not from 0.. Please pay attention to this and remember

    /**
     * This function returns the continent ID of the continent.
     *
     * @return continentCountId
     */
    public static int getContinentCountId() {

        return continentCountId;
    }

    /**
     * This function sets the continent CountID of the continent.
     *
     * @param  continentCountId,
     *                  countId of the Continent
     */
    public static void setContinentCountId(int continentCountId) {
        Helper.continentCountId = continentCountId;
    }

    /**
     * This function returns the new continent CountID
     *
     * @return  continentCountId,
     *                  countId of the Continent
     */
    public static int getNewContinentCountId() {
        continentCountId = continentCountId +1;
        return continentCountId;
    }

    /**
     * This function returns the CountID of the Country
     *
     * @return  countryCountId,
     *                  countId of the Country
     */
    public static int getCountryCountId() {
        return countryCountId;
    }

    /**
     * This function sets the CountID of the Country
     *
     * @param countryCountId,
     *                  countId of the Country
     */
    public static void setCountryCountId(int countryCountId) {
        Helper.countryCountId = countryCountId;
    }

    /**
     * This function returns the new CountID of the Country
     *
     * @return  countryCountId,
     *                  countId of the Country
     */
    public static int getNewCountryCountId() {
        countryCountId = countryCountId +1;
        return countryCountId;
    }

}
