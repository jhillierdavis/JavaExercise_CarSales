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
        int maxSales = 0;
        String mostPopular = "";
        for (String carModel: this.uniqueCarModels)    {
            int sales = this.sales(carModel);
            if (sales > maxSales)   {
                mostPopular = carModel;
                maxSales = sales;
            }
        }
        return 0 == maxSales ? Optional.empty() : Optional.of(mostPopular);
    }

    public Optional<String> mostPopularModelOf(final String city)   {
        int maxSales = 0;
        String mostPopular = "";
        for (String carModel: this.uniqueCarModels)    {
            int sales = this.sales(carModel, city);
            if (sales > maxSales)   {
                mostPopular = carModel;
                maxSales = sales;
            }
        }
        return 0 == maxSales ? Optional.empty() : Optional.of(mostPopular);
    }

    protected int sales(final String model, final String city)    {
        SalesDatum entryToMatch = new SalesDatum(model, city);
        return (int) Arrays.stream(this.data).filter( d -> d.equals(entryToMatch) ).count();
    }
}
