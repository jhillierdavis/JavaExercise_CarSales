package sales.services;

import sales.model.SalesDatum;

import java.util.Arrays;
import java.util.Optional;

/**
 * Analyses sales data to obtain meta data (e.g. sales counts, popularity)
 */

public class SalesAnalyser implements SalesInfo {
    private final SalesDatum[] data;
    private final String[] uniqueCarModels;

    public SalesAnalyser(final SalesDatum[] data)    {
        if (null == data)   {
            throw new IllegalArgumentException("Null data");
        }

        CarModelFilter filter = new CarModelFilter(data);
        this.uniqueCarModels = filter.getUniqueCarModels();
        this.data = data;
    }

    public int sales(final String model)    {
        return (int) Arrays.stream(this.data).filter( d -> d.getCarModel().equals(model) ).count();
    }

    public Optional<String> mostPopularModel()    {
        return getPopularity( Optional.empty() );
    }

    public Optional<String> mostPopularModelOf(final String city)   {
        if (null == city)   {
            throw new IllegalArgumentException("City is null");
        }
        return getPopularity( Optional.of(city) );
    }

    private Optional<String> getPopularity(final Optional<String> optionalCity) {
        int maxSales = 0;
        String mostPopular = "";
        for (String carModel: this.uniqueCarModels)    {
            int sales = getSales( carModel, optionalCity );

            if (sales > maxSales)   {
                mostPopular = carModel;
                maxSales = sales;
            }
        }
        return 0 == maxSales ? Optional.empty() : Optional.of(mostPopular);
    }

    private int getSales(String carModel, Optional<String> optionalCity) {
        int sales;
        if (optionalCity.isPresent()) {
            sales = this.sales(carModel, optionalCity.get());
        } else {
            sales = this.sales(carModel);
        }
        return sales;
    }

    protected int sales(final String model, final String city)    {
        SalesDatum entryToMatch = new SalesDatum(model, city);
        return (int) Arrays.stream(this.data).filter( d -> d.equals(entryToMatch) ).count();
    }
}
