package sales.services;

import sales.model.SalesDatum;

import java.util.Arrays;

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
        String[] allCarModelEntries = getAllCarModelEntries(data);
        int uniqueCount = getUniqueCarModelCount(allCarModelEntries);
        this.uniqueCarModels = getUniqueCarModels(allCarModelEntries, uniqueCount);
    }


    private String[] getAllCarModelEntries(final SalesDatum[] data) {
        int length = data.length;
        String[] allCarModelEntries = new String[length];

        for (int i =0; i < length; i++ ) {
            allCarModelEntries[i] = data[i].getCarModel();
        }
        return allCarModelEntries;
    }

    private int getUniqueCarModelCount(String[] allCarModelEntries) {
        int uniqueCount = 0;

        Arrays.sort(allCarModelEntries);
        String lastCarModel = "";
        for (String currentCarModel: allCarModelEntries)  {
            if (!currentCarModel.equals(lastCarModel))    {
                uniqueCount++;
                lastCarModel = currentCarModel;
            }
        }
        return uniqueCount;
    }

    private String[] getUniqueCarModels(String[] allCarModelEntries, final int uniqueCount) {
        String[] uniqueCarModels = new String[uniqueCount];
        String lastCarModel = "";

        int offset = uniqueCount;
        for (String carModel: allCarModelEntries)  {
            if (!carModel.equals(lastCarModel))    {
                uniqueCarModels[uniqueCarModels.length - offset] = carModel;
                offset--;
                lastCarModel = carModel;
            }
        }
        return uniqueCarModels;
    }

}
