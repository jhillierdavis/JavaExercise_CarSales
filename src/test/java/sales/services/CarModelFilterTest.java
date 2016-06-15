package sales.services;

import org.junit.BeforeClass;
import org.junit.Test;
import sales.model.SalesDatum;

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
    public void getUniqueCarModels()  {
        // Given:
        CarModelFilter filter = new CarModelFilter(testData);

        // When: Unique models are filtered
        String[] modelArray = filter.getUniqueCarModels();
        int length = modelArray.length;

        // Then: The expected number of car models are identified
        assertTrue("Unexpected length=" + length, length == 3);
    }


}
