import Controller.ReadMap;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


       /**
        * This class read the map file and tells whether it's whether
        * the map file is valid or invaild
        * @author Vikram
        * @version 1.0.0
        */


    public class TestMapFile {

        String filename;

        @BeforeClass
        public static void BeforeClass()
        {


        }


    @Before public void BeforeTestCase()
    {

    }

        @Test
        public void TestCase1()
        {
            //String mapName="trial";
            filename ="E:/Risk Game/RiskGame/assets/maps/trial.map";
            boolean value=ReadMap.readMap(filename);
            //boolean value= ValidateMap.validateMap();
            String s1=Boolean.toString(value);

            assertEquals("false",s1);
        }


        @Test
    public void TestCase2()
    {
        //String mapName="trial";
        filename ="E:/Risk Game/RiskGame/assets/maps/Asia.map";
        boolean value=ReadMap.readMap(filename);
                //boolean value= ValidateMap.validateMap();
                String s1=Boolean.toString(value);

        assertEquals("true",s1);
    }




}
