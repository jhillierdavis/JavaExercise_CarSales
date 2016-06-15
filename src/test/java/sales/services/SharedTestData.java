package sales.services;

import sales.model.SalesDatum;

/**
 * Shared test data generator.
 */
class SharedTestData {

    static SalesDatum[] generateSaleData()  {
        SalesDatum[] testData = new SalesDatum[8];
        testData[0] = new SalesDatum("Golf", "London");
        testData[1] = new SalesDatum("Scirocco", "London");
        testData[2] = new SalesDatum("Golf", "London");
        testData[3] = new SalesDatum("Golf", "Liverpool");
        testData[4] = new SalesDatum("Golf", "Leeds");
        testData[5] = new SalesDatum("Polo", "Leeds");
        testData[6] = new SalesDatum("Polo", "Liverpool");
        testData[7] = new SalesDatum("Polo", "Leeds");

        return testData;
    }
}
