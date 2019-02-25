/**
 * @author Hemanshu
 * @date 2019-02-22
 */
public class Helper {
    static int countryCountId = 0;


    public static int getNewCountryCountId() {
        countryCountId = countryCountId +1;
        return countryCountId;
    }

    public static int getCountryCountId() {
        return countryCountId;
    }

    public static void setCountryCountId(int countryCountId) {
        Helper.countryCountId = countryCountId;
    }
}
