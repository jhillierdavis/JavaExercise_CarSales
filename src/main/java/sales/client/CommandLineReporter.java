package sales.client;

import sales.model.SalesDatum;
import sales.persistence.CsvParser;
import sales.services.SalesAnalyser;
import sales.services.SalesInfo;

import java.io.File;

/**
 * Command line client to generate a report on car sales across a set of cities.
 */

@Deprecated
public class CommandLineReporter {
    public static final String NONE = "None";

    private CommandLineReporter() {
        // Prevent instantiation
    }

    public static void main(String[] args)   {

        ArgsValidator validator = new ArgsValidator(args);
        if (validator.isHasErrors())    {
            displayMessageAndExit( validator.getMessage() );
        }

        SalesDatum[] testData = getCarSalesData( validator.getInputFile() );
        displayReportInfo(validator.getCarModel(), validator.getCity(), testData);
    }

    private static SalesDatum[] getCarSalesData(File inputFile) {
        CsvParser parser  = new CsvParser(inputFile);
        return parser.toData();
    }

    private static void displayReportInfo(String carModel, String city, SalesDatum[] testData) {
        SalesInfo info = new SalesAnalyser(testData);

        String mostPopularModel = info.mostPopularModel().orElse(NONE);

        System.out.println("Most popular model = " + mostPopularModel );
        System.out.println("Total sales of car model: " + carModel + " = " + info.sales(carModel) );
        System.out.println("Most popular car model in city: " + city + " = " + info.mostPopularModelOf(city).orElse(NONE) );
    }

    private static void displayMessageAndExit(final String msg) {
        System.out.println(msg);
        System.exit(1);
    }
}
