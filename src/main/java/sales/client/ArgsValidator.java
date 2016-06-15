package sales.client;

import java.io.File;

/**
 * Validator for command line args
 */

public class ArgsValidator {
    public static final String USAGE = "Usage: CommandLineReporter <CSV file> <Car Model> <City>";

    private File inputFile;
    private String carModel;
    private String city;

    private boolean hasErrors = true; // Pessimistic by default, assume invalid
    private String message;

    ArgsValidator(final String[] args) {
        if (args.length < 3)    {
            setMessage(USAGE);
            return;
        }

        String inputFileName = args[0];
        inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            setMessage("Non-existent sales data file: " + inputFileName);
            return;
        }

        carModel = args[1];
        city = args[2];
        hasErrors = false;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public File getInputFile() {
        return inputFile;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCity() {
        return city;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public String getMessage() {
        return message;
    }
}
