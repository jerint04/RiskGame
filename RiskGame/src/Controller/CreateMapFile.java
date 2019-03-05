package Controller;

import Model.Country;
import Model.GameModel;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Controller.CreateMapFile Class
 * @author Hemanshu
 * @version 1.0.0
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
        for (String continentString : GameModel.ContinentList) {
            fileData.append( GameModel.continentHashMap.get(continentString).getContinentName() + "=" + GameModel.continentHashMap.get(continentString).getControlValue()
                    + "\r\n");
        }
        fileData.append("\n[Territories]\r\n");
        for (String continentString  : GameModel.ContinentList) {
            for (String countryName : GameModel.continentHashMap.get(continentString).getCountries()) {
                Country temp = GameModel.countryHashMap.get(countryName);
                fileData.append(temp.getCountryName() + "," + temp.getxCoordinate() + ","
                        + temp.getyCoordinate() + "," + GameModel.continentHashMap.get(continentString).getContinentName());
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
            Scanner sc=new Scanner(System.in);
            //Delete temporary file
            String currentDirectory = System.getProperty("user.dir"); /*TODO  take input of file name*/
            System.out.println("Enter the name of the file you want to name");
            String mapName=sc.nextLine();
            Path tempFilePath = Paths.get( "./assets/maps/" + mapName + ".map");
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
