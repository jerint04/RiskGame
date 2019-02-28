import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

/**
 * Map Class
 * @author Jerin
 * @version 1.0.0
 * @date 22-February-2019
 */
public class ReadMap {

/*    public static void main(String[] args) {
        readMap();
        System.out.println("Countinent List : " + CreateMap.ContinentList );
        System.out.println("Country List : "  + CreateMap.countryIdHashMap );
    }*/
    /**
     * This function loads the Map into the memory
     * @param getFileName,
     *
     */
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
                    Country country = new Country(Helper.getNewCountryCountId(), parsedCountryList[0], continentName);
                    country.setxCoordinate(Integer.parseInt(parsedCountryList[1]));
                    country.setyCoordinate(Integer.parseInt(parsedCountryList[2]));
                    int p = 0;
                    // p is initialized to get neighbouring countries
                    for (String neighborCountry : parsedCountryList) {
                        if (p > 3) {
                            country.addAdjacentCountry(neighborCountry);
                        }
                        p++;
                    }
                /**
                 * This adds a Country to the Country list
                 *
                 * @param parsedCountryList[],
                 *            arrayList of the Country
                 */
                    CreateMap.CountryList.add(parsedCountryList[0]);
                    CreateMap.countryIdHashMap.put(Helper.getCountryCountId(), parsedCountryList[0]);
                    CreateMap.countryHashMap.put(parsedCountryList[0], country);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        GraphNew.initializeCountryMatrix();
        createAdjacentMatrix();
        GraphNew.printGraph();
    }

    /**
     * This function creates adjacent Countries
     */
    public static void createAdjacentMatrix() {
        for (String a : CreateMap.CountryList) {
            Country xTemp = CreateMap.countryHashMap.get(a);
            Object xo = getKeyFromValue(CreateMap.countryIdHashMap, xTemp.getCountryName());
            int x = (Integer) xo;
            for (String adjacentCountryName : xTemp.getAdjacentCountries()) {
                Country yTemp = CreateMap.countryHashMap.get(adjacentCountryName);
                Object yo = getKeyFromValue(CreateMap.countryIdHashMap, yTemp.getCountryName());
                int y = (Integer) yo;
                System.out.println(y+" "+x);
                GraphNew.countryMatrix[y][x] = 1;
            }
        }
    }

    /**
     * This function gets the country value
     * @param hm,
     * @param value ,
     */
    public static Object getKeyFromValue(Map hm, String value) {
        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
            if (pair.getValue().equals(value)) {
                return pair.getKey();
            }
        }
        return -1;
    }
}
