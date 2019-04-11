import org.junit.Before;
import org.junit.Test;

import static Model.Helper.*;
import static junit.framework.TestCase.assertEquals;

/**
 * @author Vikram
 * @version 1.0.0
 */

public class TestHelper {

    int continents, countries;


    @Before
    public void BeforeTestCase() {




    }


    @Test
    public void TestCase1() {
        setContinentCountId(7);
    }
    @Test
    public void TestCase2() {
        continents = getContinentCountId();
        assertEquals(7, continents);
    }
    @Test
    public void TestCase3() {
        setCountryCountId(45);
    }


    @Test
    public void TestCase4() {
        countries = getCountryCountId();
        assertEquals(45, countries);
    }

    }


