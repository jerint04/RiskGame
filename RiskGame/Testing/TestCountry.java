import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 * The class <code>TestCountry</code> contains tests for the class
 * <code> {@link Model.Country}</code>
 *
 * @author Vikram
 * @version 1.0.0
 *
 */


public class TestCountry {

    Model.Country country;
    Model.Player player;
    Model.Continent continent;


/**
     * Test case Initialization for TestCountry
     */


    @Before
    public void beforeTestCountry()
    {
        country = new Model.Country(1,"India","Asia");
    }


/**
     * Test case to tests x value is correct
     */


    @Test
    public void TestGetX() {
        System.out.println("testGetX");
        int x = country.getxCoordinate();
        System.out.println(x);
        assertEquals(0, x);
    }

    @Test
    public void TestCountryName()
    {
        String name = country.getCountryName();
        System.out.print(name);
        assertEquals("India",name);

    }
      @Test
    public void TestContinentName()
    {
        String name = country.getParentContinent();
        System.out.print(name);
        assertEquals("Asia",name);

    }



/**
     * Test case to tests x value is correct
     */


    @Test
    public void TestGetY() {
        System.out.println("testGetY");
        int y = country.getyCoordinate();
        System.out.println(y);
        assertEquals(0, y);
    }


    @Test
    public void TestgetId() {

        int id = country.getId();
        System.out.println(id);
        assertEquals(1, id);
    }

/**
     * returning in int
     */

    @Test
    public void TestNumberOfSoldiers() {
        System.out.println("NumberOfSoldiers");
        int army = country.getNumberOfSoldiers();
        System.out.println(army);
        assertEquals(0, army);
    }



/**
     * Perform post-test clean-up.
     *
     * @throws Exception
     *             if the clean-up fails for some reason
     */


    @After
    public void TearDown() throws Exception {
        System.out.println("");
        country = null;
        assertNull(country);
    }
}



