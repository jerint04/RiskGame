
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.plaf.synth.SynthScrollBarUI;


/**
 * The class <code>TestCountry</code> contains tests for the class
 * <code> {@link Country}</code>
 *
 * @author Vikram
 * @version 1.0.0
 *
 */

public class TestCountry {

    Country country;
    Player player;
    Continent continent;

    /**
     * Test case Initialization for TestCountry
     */

    @Before
    public void beforeTestCountry()
    {
        country = new Country(1,"India","Asia");
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
