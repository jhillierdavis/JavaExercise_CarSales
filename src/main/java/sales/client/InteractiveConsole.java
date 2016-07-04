package sales.client;

import sales.persistence.CsvParser;
import sales.services.*;

import java.io.File;
import java.util.Scanner;

/**
 * Interactive console for user input based on a presented menu.
 * <p>
 * NB: Uses java.util.Scanner rather than java.io.Console to obtain command-line user input since the latter is typically unavailable (e.g. via IDE execution).
 */

public class InteractiveConsole {
    private static final String NONE = "None";
    private Scanner scanner = new Scanner(System.in);
    private SalesData salesData;
    private SalesInfo salesInfo;
    
    public InteractiveConsole() {
        SalesAnalyser analyser = new SalesAnalyser();
        this.salesData = analyser;
        this.salesInfo = analyser;

        this.run();
    }

    public void run()    {
        boolean isContinue = true;
        while (isContinue) {
            this.displayMenu();

            int choice = -1;
            try {
                String input = scanner.next();
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid input choice, please retry.");
                continue;
            }

            switch (choice) {
                default:
                    System.out.println("Unknown choice: " + choice);
                    break;

                case 0:
                    System.out.println("Goodbye");
                    isContinue = false;
                    break;

                case 1:
                    userInputLoadFile();
                    break;

                case 2:
                    userInputAddSale();
                    break;

                case 3:
                    String mostPopularModel = this.salesInfo.mostPopularModel().orElse(NONE);
                    System.out.println("Most popular car model is " + mostPopularModel);
                    break;

                case 4:
                    userInputMostPopular();
                    break;

                case 5:
                    userInputTotalSales();
                    break;
            }
        }
    }

    private void userInputLoadFile() {
        System.out.print("Please enter the file name (including path): ");
        String fileToLoad = scanner.next();
        File file = new File(fileToLoad);
        if (!file.exists()) {
            System.out.print("Non-existent sales data file: " + fileToLoad);
            return;
        }
        System.out.println("Loading data from: " + fileToLoad + " .... ");
        try {
            CsvParser parser = new CsvParser(file);
            this.salesData.reload(parser.toData());
        } catch (Exception e) {
            System.out.println("Failed to load (please recheck whether valid): " + fileToLoad);
        }
    }

    private void userInputAddSale() {
        System.out.print("Please enter the car model: ");
        String model = scanner.next();
        System.out.print("Please enter the city: ");
        String city = scanner.next();
        this.salesData.addSale(model, city);
        System.out.println("Added data: " + model + " , " + city);
    }

    private void userInputTotalSales() {
        System.out.print("Please enter the car model: ");
        String model = scanner.next();
        int modelSales = this.salesInfo.sales(model);
        System.out.println("Total sales for model " + model + " : " + modelSales);
    }

    private void userInputMostPopular() {
        System.out.print("Please enter the city: ");
        String cityForModel = this.scanner.next();
        String mostPopularModelOf = this.salesInfo.mostPopularModelOf(cityForModel).orElse(NONE);
        System.out.println("Most popular car model is " + mostPopularModelOf);
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("Please your choice from this menu: ");
        System.out.println("0 - exit");
        System.out.println("1 - load data from CSV file.");
        System.out.println("2 - add a sale");
        System.out.println("3 - most popular model");
        System.out.println("4 - most popular model by city");
        System.out.println("5 - total model sales");
        System.out.println();
    }
}