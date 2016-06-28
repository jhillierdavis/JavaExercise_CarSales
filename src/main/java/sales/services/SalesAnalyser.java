package sales.services;

import data.structures.Matrix;
import sales.model.SalesDatum;

import java.util.Arrays;
import java.util.Optional;

/**
 * Analyses sales data to obtain meta data (e.g. sales counts, popularity)
 */

public class SalesAnalyser implements SalesInfo {
    // private SalesDatum[] data;
    // private String[] uniqueCarModels;
    Matrix saleMatrix;

    public SalesAnalyser()  {
        // this.uniqueCarModels = new String[0];
        // this.data = new SalesDatum[0];
        this.saleMatrix = new Matrix();
    }

    public SalesAnalyser(final SalesDatum[] data)    {
        this.reload(data);
    }

    public void reload(final SalesDatum[] data)    {
        if (null == data)   {
            throw new IllegalArgumentException("Null data");
        }

        CarModelFilter filter = new CarModelFilter(data);
        // this.uniqueCarModels = filter.getUniqueCarModels();
        // this.data = data;
        this.saleMatrix = new Matrix();
        for (SalesDatum datum: data)    {
            this.saleMatrix.add(datum.getCarModel(), datum.getCity(), 1);
        }
    }

    @Override
    public int sales(final String model)    {
        // return (int) Arrays.stream(this.data).filter( d -> d.getCarModel().equals(model) ).count();
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
        /*
        int newLength = this.data.length + 1;
        SalesDatum[] replacement = new SalesDatum[ newLength ];
        System.arraycopy( this.data, 0, replacement, 0, newLength - 1 );
        replacement[ newLength - 1 ] = new SalesDatum(model, city);

        this.data = replacement;

        CarModelFilter filter = new CarModelFilter(this.data);
        this.uniqueCarModels = filter.getUniqueCarModels();
        */
        this.saleMatrix.add(model, city, 1);
    }

    private Optional<String> getPopularity(final Optional<String> optionalCity) {
        int maxSales = 0;
        String mostPopular = "";
        // for (String carModel: this.uniqueCarModels)    {
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
        /*
        SalesDatum entryToMatch = new SalesDatum(model, city);
        return (int) Arrays.stream(this.data).filter( d -> d.equals(entryToMatch) ).count();
        */
        return this.saleMatrix.get(model, city);
    }
}