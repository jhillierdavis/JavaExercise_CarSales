package sales.services;

import data.structures.Matrix;
import sales.model.SalesDatum;

import java.util.Arrays;
import java.util.Optional;

/**
 * Analyses sales data to obtain meta data (e.g. sales counts, popularity)
 */

public class SalesAnalyser implements SalesInfo, SalesData {
    Matrix saleMatrix;

    public SalesAnalyser()  {
        this.saleMatrix = new Matrix();
    }

    public SalesAnalyser(final SalesDatum[] data)    {
        this.reload(data);
    }

    @Override
    public void reload(final SalesDatum[] data)    {
        if (null == data)   {
            throw new IllegalArgumentException("Null data");
        }

        this.saleMatrix = new Matrix();
        for (SalesDatum datum: data)    {
            this.saleMatrix.add(datum.getCarModel(), datum.getCity(), 1);
        }
    }

    @Override
    public int sales(final String model)    {
        return this.saleMatrix.getRowSum(model);
    }

    @Override
    public Optional<String> mostPopularModel()    {
        return getPopularity( Optional.empty() );
    }

    @Override
    public Optional<String> mostPopularModelOf(final String city)   {
        if (null == city)   {
            throw new IllegalArgumentException("City is null");
        }
        return getPopularity( Optional.of(city) );
    }

    @Override
    public void addSale(String model, String city) {
        this.saleMatrix.add(model, city, 1);
    }

    private Optional<String> getPopularity(final Optional<String> optionalCity) {
        int maxSales = 0;
        String mostPopular = "";
        for (String carModel: this.saleMatrix.getRows())    {
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
        return this.saleMatrix.get(model, city);
    }
}