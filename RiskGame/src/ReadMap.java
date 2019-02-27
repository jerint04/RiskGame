import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Jerin
 * @date 22-February-2019
 */
public class ReadMap {

/*    public static void main(String[] args) {
        readMap();
        System.out.println("Countinent List : " + CreateMap.ContinentList );
        System.out.println("Country List : "  + CreateMap.countryIdHashMap );
    }*/

    public static void readMap(String getFileName) {
        try {
            boolean readContinentsFromFile = false;
            boolean readCountriesFromFile = false;
            String filePath = getFileName;
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine;
            int continentID = Helper.getContinentCountId();
            int countryID = Helper.getCountryCountId();

            while ((readLine = bufferedReader.readLine()) != null) {
                if (readLine.trim().length() == 0)
                    continue;
                else if ((readLine.trim()).equals("[Continents]")) {
                    readContinentsFromFile = true;
                    continue;
                } else if ((readLine.trim()).equals("[Territories]")) {
                    readContinentsFromFile = false;
                    readCountriesFromFile = true;
                    continue;
                }
                if (readContinentsFromFile) {
                    String[] parsedContinentArray = readLine.split("=");
                    Continent continent = new Continent(Helper.getContinentCountId(), parsedContinentArray[0],
                            Integer.parseInt(parsedContinentArray[1]));
                    CreateMap.ContinentList.add(continent);
                    CreateMap.continentHashMap.put(parsedContinentArray[0], continent);
                } else if (readCountriesFromFile) {
                    String[] parsedCountryList = readLine.split(",");
                    String continentName = parsedCountryList[3];
                    Country country = new Country(Helper.getNewCountryCountId(), parsedCountryList[0],continentName);
                    country.setxCoordinate(Integer.parseInt(parsedCountryList[1]));
                    country.setyCoordinate(Integer.parseInt(parsedCountryList[2]));
                    int p = 0;
                    for (String neighborCountry : parsedCountryList) {
                        if (p > 3) {
                            country.addAdjacentCountry(neighborCountry);
                        }
                        p++;
                    }
                    CreateMap.countryIdHashMap.put(Helper.getCountryCountId() , parsedCountryList[0]);
                    CreateMap.countryHashMap.put(parsedCountryList[0],country);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }




}
