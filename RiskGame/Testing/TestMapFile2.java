import Controller.ReadMap;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Hemanshu
 * @date 2019-03-27
 */
public class TestMapFile2 {

    static String filename;
    static boolean value;

    @BeforeClass
    public static void BeforeClass() {

    }


    @Before
    public void BeforeTestCase() {
        filename = "./assets/maps/updated.map";
        value = ReadMap.readMap(filename);
    }




    @Test
    public void TestCase2() {
        //String mapName="trial";
        //boolean value= ValidateMap.validateMap();
        String s1 = Boolean.toString(value);
        assertEquals("true", s1);
    }


}
