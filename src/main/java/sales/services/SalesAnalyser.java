package sales.services;

import sales.model.SalesDatum;

import java.util.Optional;

/**
 * Sales data analyser
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
        int count = 0;
        for(SalesDatum datum: this.data)  {
            if (datum.getCarModel().equals(model))    {
                count++;
            }
        }
        return count;
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

        int count = 0;
        for(SalesDatum datum: this.data)  {
            if (entryToMatch.equals(datum))    {
                count++;
            }
        }
        return count;
    }
}
