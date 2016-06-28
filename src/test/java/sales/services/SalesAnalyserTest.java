package sales.services;

import org.junit.*;
import sales.model.SalesDatum;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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
        assertThat(info.mostPopularModel().get(), is( equalTo("Golf".toLowerCase() ) ) );
    }

    @Test
    public void mostPopularModelOfCity()    {
        // Given: test data processed
        SalesInfo info = new SalesAnalyser(testData);

        // Then: check expected results
        assertEquals(false, info.mostPopularModelOf("Bogus").isPresent());
        assertThat(info.mostPopularModelOf("London").get(), is (equalTo("Golf".toLowerCase() ) ) );
        assertThat(info.mostPopularModelOf("Liverpool").get(), is (equalTo("Golf".toLowerCase() ) ) );
        assertThat(info.mostPopularModelOf("Leeds").get(), is (equalTo("Polo".toLowerCase() ) ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void mostPopularModelOfEmptyCity()    {
        // Given: test data processed
        SalesInfo info = new SalesAnalyser(testData);

        // Then: check expected results
        assertEquals(false, info.mostPopularModelOf(" ").isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void mostPopularModelOfNullCity()    {
        // Given: test data processed
        SalesInfo info = new SalesAnalyser(testData);

        // Then: check expected results
        assertEquals(false, info.mostPopularModelOf(null).isPresent());
    }

    @Test
    public void salesForModel()    {
        // Given:
        SalesAnalyser analyser = new SalesAnalyser(testData);

        // Then:
        assertTrue(0 == analyser.sales("Bogus"));

        // And:
        int golfCount = analyser.sales("Golf");
        assertThat( golfCount, is(4) );

        // And:
        int sciroccoCount = analyser.sales("Scirocco");
        assertThat(sciroccoCount, is(1));

        // And:
        assertThat( analyser.sales("Polo"), is(3) );
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


    @Test
    public void addSale()   {
        // Given:
        SalesInfo analyser = new SalesAnalyser(testData);

        // Then:
        assertThat( analyser.mostPopularModelOf("London").get(), is (equalTo("Golf".toLowerCase() )) );
        assertThat(analyser.sales("Scirocco"), is(1) );

        // When:
        analyser.addSale("Scirocco", "London");
        analyser.addSale("Scirocco", "London");
        analyser.addSale("Scirocco", "London");

        // Then:
        assertThat( analyser.mostPopularModelOf("London").get(), is (equalTo("Scirocco".toLowerCase() )) );
        assertThat(analyser.sales("Scirocco"), is(4) );
    }
}