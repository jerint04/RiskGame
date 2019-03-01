import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author Hemanshu
 * @date 2019-02-25
 */
public class CreateMapFile {
    public static void createFile() {
        StringBuffer fileData = new StringBuffer();
        fileData.append("[Map]\r\n");
        fileData.append("author=Concordia Students\r\n");
        fileData.append("image=\r\n");
        fileData.append("wrap=yes\r\n");
        fileData.append("scroll=horizontal\r\n");
        fileData.append("warn=yes\r\n");

        fileData.append("[Continents]\r\n");
        for (Continent continentObject : CreateMap.ContinentList) {
            fileData.append(continentObject.getContinentName() + "=" + continentObject.getControlValue()
                    + "\r\n");
        }
        fileData.append("\n[Territories]\r\n");
        for (Continent continentObject : CreateMap.ContinentList) {
            for (String countryName : continentObject.getCountries()) {
                Country temp = CreateMap.countryHashMap.get(countryName);
                fileData.append(temp.getCountryName() + "," + temp.getxCoordinate() + ","
                        + temp.getyCoordinate() + "," + continentObject.getContinentName());
                for (String adjacentCountries : temp.getAdjacentCountries()) {
                    fileData.append("," + adjacentCountries);
                }
                fileData.append("\r\n");
            }
        }
        /*Writing Map to disk*/
        Path path = Paths.get("./assets/maps");
        BufferedWriter writer = null;
        try {
            //Delete temporary file
            String currentDirectory = System.getProperty("user.dir");
            Path tempFilePath = Paths.get(currentDirectory + "/RiskGame/assets//maps/" + "temp" + ".map");
            Files.deleteIfExists(tempFilePath);
            writer = Files.newBufferedWriter(tempFilePath, StandardCharsets.UTF_8);
            writer.write(new String(fileData));
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        System.out.println(fileData);
    }

}
