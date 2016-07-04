package sales.client;

import java.io.File;

/**
 * Command line argument validation
 */

@Deprecated
class ArgsValidator {
    private static final String USAGE = "Usage: CommandLineReporter <CSV file> <Car Model> <City>";

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


    private void setMessage(String message) {
        this.message = message;
    }

    File getInputFile() {
        return inputFile;
    }

    String getCarModel() {
        return carModel;
    }

    String getCity() {
        return city;
    }

    boolean isHasErrors() {
        return hasErrors;
    }

    String getMessage() {
        return message;
    }
}
