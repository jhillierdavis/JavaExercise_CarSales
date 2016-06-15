package sales.services;

import org.junit.*;
import sales.model.SalesDatum;

import static org.junit.Assert.*;

/**
 * Unit tests for SalesAnalyser class.
 */

public class SalesAnalyserTest {
    private static SalesDatum[] testData;

    @BeforeClass
    public static void testDataSetUp() {
        testData = SharedTestData.generateSaleData();
    }

    @Test
    public void checkTestData() {
        assertTrue(testData.length == 8);
    }

    @Test
    public void mostPopularModel()  {
        // Given:
        SalesInfo info = new SalesAnalyser(testData);

        // Then:
        assertEquals("Golf", info.mostPopularModel().get());
    }

    @Test
    public void mostPopularModelOfCity()    {
        // Given:
        SalesInfo info = new SalesAnalyser(testData);

        // Then:
        assertEquals(false, info.mostPopularModelOf("Bogus").isPresent());
        assertEquals( "Golf", info.mostPopularModelOf("London").get() );
        assertEquals( "Golf", info.mostPopularModelOf("Liverpool").get() ); // Ambiguous, use first
        assertEquals( "Polo", info.mostPopularModelOf("Leeds").get() );
    }



    @Test
    public void salesForModel()    {
        // Given:
        SalesAnalyser analyser = new SalesAnalyser(testData);

        // Then:
        assertTrue(0 == analyser.sales("Bogus"));

        // And:
        int golfCount = analyser.sales("Golf");
        assertTrue("Unexpected " + golfCount, 4 == golfCount);

        // And:
        int sciroccoCount = analyser.sales("Scirocco");
        assertTrue("Unexpected " + sciroccoCount, 1 == sciroccoCount);

        // And:
        assertTrue(3 == analyser.sales("Polo"));
    }

    @Test
    public void salesForModelAndCity()    {
        // Given:
        SalesAnalyser analyser = new SalesAnalyser(testData);

        // Then: Absent data has no sales results
        assertTrue(0 == analyser.sales("Bogus", "Bogus"));
        assertTrue(0 == analyser.sales("Golf", "Bogus"));
        assertTrue(0 == analyser.sales("Bogus", "London"));
        assertTrue(0 == analyser.sales("Scirocco", "Liverpool"));

        // And: Present data has sales results
        assertTrue(1 == analyser.sales("Scirocco", "London"));
        assertTrue(2 == analyser.sales("Golf", "London"));
        assertTrue(1 == analyser.sales("Golf", "Leeds"));
        assertTrue(2 == analyser.sales("Polo", "Leeds"));
    }


}