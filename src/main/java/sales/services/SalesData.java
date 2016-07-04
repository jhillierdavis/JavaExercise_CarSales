package sales.services;

import sales.model.SalesDatum;

import java.io.File;

/**
 * Interface for sales data management.
 */

public interface SalesData {
    void reload(SalesDatum[] salesData);

    void addSale(String model, String city);
}
