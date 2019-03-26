import org.junit.Before;
import org.junit.Test;

import static Model.Helper.*;
import static junit.framework.TestCase.assertEquals;

/**

 * @author Vikram
 * @version 1.0.0
 */

public class TestHelper {

    int continents,countries;



    @Before
    public void BeforeTestCase()
    {
        setContinentCountId(7);
        setCountryCountId(45);


    }
    @Test
    public void TestCase1()
    {
        continents=getContinentCountId();
        assertEquals(7,continents);
    }
    @Test
    public void TestCase2()
    {
        countries=getCountryCountId();
        assertEquals(45,countries);
    }


}


