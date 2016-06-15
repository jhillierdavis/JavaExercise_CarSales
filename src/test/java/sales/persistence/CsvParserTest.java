package sales.persistence;

import org.junit.*;
import sales.model.SalesDatum;

import static org.junit.Assert.*;

import java.io.*;


/**
 * Unit tests for CsvParserTest class.
 */

public class CsvParserTest {
    public static final String MODEL_CITY_SALES_DATA_CSV = "model_city_sales_data.csv";

    @Test
    public void fileOnClasspath() {

        // Given
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(MODEL_CITY_SALES_DATA_CSV).getFile());

        // Then:
        // System.out.println("File: " + file);
        assertNotNull(file);
        assertEquals(file.getName(), MODEL_CITY_SALES_DATA_CSV);
    }

    @Test
    public void toData() {
        // Given: A test CSV file
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(MODEL_CITY_SALES_DATA_CSV).getFile());

        // When: Parsed for data
        CsvParser csvParser = new CsvParser(file);
        SalesDatum[] data = csvParser.toData();

        // Then: Extracted data is as expected
        assertNotNull(data);
        assertEquals(7, data.length);
        assertEquals("Golf Cabriolet", data[2].getCarModel());
        assertEquals("Bristol", data[6].getCity());
    }
}