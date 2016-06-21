package sales.services;

import sales.model.SalesDatum;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Filters car model info. from sales data.
 */

class CarModelFilter {
    private String[] uniqueCarModels; // Cached set of unique car models

    public CarModelFilter(final SalesDatum[] data)    {
        if (null == data)   {
            throw new IllegalArgumentException("NULL data");
        }
        this.processData(data);
    }

    protected String[] getUniqueCarModels()  {
        return this.uniqueCarModels;
    }

    protected void processData(final SalesDatum[] data)  {
        this.uniqueCarModels = Arrays.stream(data).map(d -> d.getCarModel()).distinct().toArray(String[]::new);
    }
}
