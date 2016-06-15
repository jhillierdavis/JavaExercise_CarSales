package sales.model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Unit tests for SalesDatum class.
 */

@RunWith(JUnitParamsRunner.class)
public class SalesDatumTest {
    private static final String
            VALID_CAR_MODEL = "Beetle",
            VALID_CITY = "Liverpool",
            NULL_PLACEHOLDER = "null";

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    @Parameters({
            // carModel, city
            NULL_PLACEHOLDER + ", " + VALID_CITY,
            ", " + VALID_CITY,
            " , " + VALID_CITY,
            VALID_CAR_MODEL + "," + NULL_PLACEHOLDER,
            VALID_CAR_MODEL + ",",
            VALID_CAR_MODEL + ", "
    })
    public void invalidCreation(String carModel, String city) {
        // Expect: An exception to be thrown
        thrown.expect(IllegalArgumentException.class);

        // When: An invalid car model is used during instantiation
        new SalesDatum(convertToNullIfAppropriate(carModel), convertToNullIfAppropriate(city));
    }

    @Test
    @Parameters({
            // carModel, city
            VALID_CAR_MODEL + ", " + VALID_CITY
    })

    public void validCreation(String carModel, String city) {
        // Given
        SalesDatum datum = new SalesDatum(carModel, city);

        // Then
        assertNotNull(datum);
        assertEquals(VALID_CAR_MODEL, datum.getCarModel());
        assertEquals(VALID_CITY, datum.getCity());
    }

    private String convertToNullIfAppropriate(String param) {
        return null == param || param.equals(NULL_PLACEHOLDER) ? null : param;
    }

}
