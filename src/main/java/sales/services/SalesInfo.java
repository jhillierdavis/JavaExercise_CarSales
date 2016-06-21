package sales.services;

import java.util.Optional;

/**
 * Interface capturing common sales query methods.
 */

public interface SalesInfo {
    Optional<String> mostPopularModel();

    Optional<String> mostPopularModelOf(String city);

    int sales(String model);

    void addSale(String model, String city);
}
