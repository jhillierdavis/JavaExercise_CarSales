package sales.model;

import java.util.Objects;

/**
 * Simple immutable data structure representing an individual Car Model sale within a particular City (i.e. a pairing, between Strings)
 */

public class SalesDatum {
    private final String carModel;
    private final String city;

    public SalesDatum(final String carModel, final String city)   {
        assertValidParameter(carModel, "Invalid carModel: " + carModel);
        assertValidParameter(city, "Invalid city: " + city);

        this.carModel = carModel.trim();
        this.city = city.trim();
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "sales.model.SalesDatum {" +
                "carModel='" + carModel + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesDatum that = (SalesDatum) o;
        return Objects.equals(carModel, that.carModel) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carModel, city);
    }

    private void assertValidParameter(String param, String message) {
        if (param == null || "".equals(param.trim()))   {
            throw new IllegalArgumentException(message);
        }
    }

}
