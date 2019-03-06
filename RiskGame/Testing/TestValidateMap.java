import Controller.ReadMap;
import Controller.ValidateMap;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * This class validates the map
 * @author Vikram
 * @version 1.0.0
 */

public class TestValidateMap {



    @Before
    public void BeforeTestCase() {

        ReadMap.readMap("E:/JavaPrograms/RiskGame/RiskGame/assets/maps/Asia.map");
    }

    @Test
    public void TestCase1() {
        boolean value = ValidateMap.validateMap();
        String s1 = Boolean.toString(value);

        assertEquals("true", s1);

    }


}
