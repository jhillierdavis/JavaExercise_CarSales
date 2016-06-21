package sales.services;

import org.junit.BeforeClass;
import org.junit.Test;

import sales.model.SalesDatum;


import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Unit tests for CarModelFilter class.
 */

public class CarModelFilterTest {
    private static SalesDatum[] testData;

    @BeforeClass
    public static void testDataSetUp() {
        testData = SharedTestData.generateSaleData();
    }

    @Test
    public void getUniqueCarModels() {
        // Given: A set of test data
        CarModelFilter filter = new CarModelFilter(testData);

        // When: Unique models are filtered
        String[] modelArray = filter.getUniqueCarModels();
        int length = modelArray.length;

        // Then: The expected unique car models are identified
        assertTrue("Unexpected length=" + length, length == 3);
        assertThat( Arrays.asList(modelArray), hasItem("Golf") );
        assertThat( Arrays.asList(modelArray), hasItem("Polo") );
        assertThat( Arrays.asList(modelArray), hasItem("Scirocco") );
    }

    @Test(expected = IllegalArgumentException.class)
    public void handleNullData()    {
         new CarModelFilter(null);
    }

    @Test
    public void handleEmptyData()    {
        // Given: An empty data set
        CarModelFilter filter = new CarModelFilter(new SalesDatum[0]);

        // When: Filtered
        String[] modelArray = filter.getUniqueCarModels();

        // Then: An empty array is returned
        assertThat(modelArray.length, is(0));
    }

}
