import java.util.*;

/*import com.risk.model.Continent;
import com.risk.model.Country;
*/
import java.io.*;
public class ReadMap {

	public static void main(String args[]) {
		try {
			
			 boolean getContinents = false;
	            boolean getCountries = false;
	            HashMap<String, Integer> Continent = new HashMap<String, Integer>();

			File file = new File("\\E:\\example.map");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));	
            String readLine;
            int continentID = 0;
            int countryID = 0;
            while ((readLine = bufferedReader.readLine()) != null) {
                if (readLine.trim().length() == 0)
                    continue;
                else if ((readLine.trim()).equals("[Continents]")) {
                    getContinents = true;
                    continue;
                } else if ((readLine.trim()).equals("[Territories]")) {
                    getContinents = false;
                    getCountries = true;
                    continue;
                }

                if (getContinents) {
                	String[] ContinentsArray = readLine.split("=");
                	System.out.println(ContinentsArray[0]+ " "+ ContinentsArray[1]);

                	Continent.put(ContinentsArray[0], Integer.parseInt(ContinentsArray[1]));
                	}
                else if (getCountries) {
                    String[] TerritoriesArray = readLine.split(",");
                    String continentName = TerritoriesArray[3];
                    Country country = new Country(countryID++, TerritoriesArray[0]);
                    System.out.println(countryID+ " "+ TerritoriesArray[0]);
                    
                }
            
            }
		}
		catch (Exception e) {
			System.out.println("sorry");
			// handle exception
		}
	}
	
}
