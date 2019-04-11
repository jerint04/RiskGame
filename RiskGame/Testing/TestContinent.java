

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * The class <code>TestContinent</code> contains tests for the class
 * <code> {@link Model.Continent}</code>
 *
 * @author Vikram
 * @version 1.0.0
 */

public class TestContinent {


    Model.Continent continent;
    public List<String> Country = new ArrayList<>();

    /*
     * Test case Initialization for TestContinent

     */

    @BeforeClass
    public static void BeforeClass() {
        System.out.println("Entered TestContinent Class");
    }

    /*
     * This method runs after all test cases were ran

     */
    @AfterClass
    public static void AfterClass() {
        System.out.println("Left TestContinent Class");
    }

    /*
     * This method initiate the variable before each test case

     */

    @Before
    public void BeforeTestContinent() {
        continent = new Model.Continent("Asia", 5);

    }

    /*
     * This testcase tests the continent Id in continent test class and shows
     * that the id is correct
     */


    @Test
    public void TestSetContinentName() {

       continent.setContinentName("Asia");
    }

    @Test
    public void TestGetContinentName() {

        String name = continent.getContinentName();
        System.out.println(name);
        assertEquals("Asia", name);
    }

    /*
     * This testcase tests the continent control value in continent test class
     * and shows that the control value is correct
     *
     */


    @Test
    public void TestGetControl() {
        int continentcontrol = continent.getControlValue();
        assertEquals(5, continentcontrol);
    }

    @Test
    public void TestCase() {
        int continentcontrol = continent.getControlValue();
        assertEquals(5, continentcontrol);
    }

    @Test
    public void TestCountryList() {
        List<String> Countries = new ArrayList<>();
        Countries = continent.getCountries();
        assertEquals(Country, Countries);
    }
}


