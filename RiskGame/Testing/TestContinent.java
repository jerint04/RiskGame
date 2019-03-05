/*
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

*/
/**
 * The class <code>TestContinent</code> contains tests for the class
 * <code> {@link Model.Continent}</code>
 *
 * @author Vikram
 * @version 1.0.0
 *//*


public class TestContinent {


    Model.Continent continent;

    */
/**
     * Test case Initialization for TestContinent
     *//*


    @BeforeClass
    public static void BeforeClass() {
        System.out.println("Entered TestContinent Class");
    }

    */
/**
     * This method runs after all test cases were ran
     *//*

    @AfterClass
    public static void AfterClass() {
        System.out.println("Left TestContinent Class");
    }

    */
/**
     * This method initiate the variable before each test case
     *//*


    @Before
    public void BeforeTestContinent() {
        continent = new Model.Continent("Asia", 5);
    }

    */
/**
     * This testcase tests the continent Id in continent test class and shows
     * that the id is correct
     *//*


    @Test
    public void TestGetContinentName() {

        String name = continent.getContinentName();
        System.out.println(name);
        assertEquals("Asia", name);
    }

    */
/**
     * This testcase tests the continent control value in continent test class
     * and shows that the control value is correct
     *
     *//*


    @Test
    public void TestGetControl() {
        int continentcontrol = continent.getControlValue();
        assertEquals(5, continentcontrol);
    }
}

*/
