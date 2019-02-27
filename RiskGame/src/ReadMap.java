import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

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
                    Country country = new Country(Helper.getNewCountryCountId(), parsedCountryList[0], continentName);
                    country.setxCoordinate(Integer.parseInt(parsedCountryList[1]));
                    country.setyCoordinate(Integer.parseInt(parsedCountryList[2]));
                    int p = 0;
                    for (String neighborCountry : parsedCountryList) {
                        if (p > 3) {
                            country.addAdjacentCountry(neighborCountry);
                        }
                        p++;
                    }

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

    public static void createAdjacentMatrix() {
        for (String a : CreateMap.CountryList) {
            Country xTemp = CreateMap.countryHashMap.get(a);
            int xo = getKeyFromValue(CreateMap.countryIdHashMap, xTemp.getCountryName());
            int x = (Integer) xo;
            for (String adjacentCountryName : xTemp.getAdjacentCountries()) {
                Country yTemp = CreateMap.countryHashMap.get(adjacentCountryName);
                int yo = getKeyFromValue(CreateMap.countryHashMap, yTemp.getCountryName());
                int y = (Integer) yo;
                GraphNew.countryMatrix[y][x] = 1;
            }
        }
    }

    public static int getKeyFromValue(Map hm, String value) {
        int i = 0;

        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
//            if () {
//                return i+1;
//            }

            i++;

            it.remove(); // avoids a ConcurrentModificationException
        }
       /*
        for (String o : hm.keySet()) {

        }*/
        return -1;
    }
}
