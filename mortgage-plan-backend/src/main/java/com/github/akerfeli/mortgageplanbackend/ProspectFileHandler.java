package com.github.akerfeli.mortgageplanbackend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.github.akerfeli.mortgageplanbackend.model.Prospect;
import org.springframework.core.io.ClassPathResource;

/**
 * Utility class to read comma-separated prospect data from a file and create Prospect objects.
 * The file format should be "name,loanAmount,interestRate,loanTerm" per line.
 * The decimal values can have max 2 decimals.
 * Invalid lines, empty values, or parsing errors are skipped, ensuring a list of valid prospects.
 */
public class ProspectFileHandler {

    private final String filePath;

    // Default constructor uses the original file path
    public ProspectFileHandler() {
        this.filePath = "data/prospects.txt";
    }

    // Constructor with a custom file path, used in tests
    public ProspectFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public List<Prospect> getAllProspects() {
        List<Prospect> prospects = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ClassPathResource(filePath).getInputStream()))) {

            // Skip the header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                Prospect prospect = parseProspect(line);
                if (prospect != null) {
                    prospects.add(prospect);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // ToDo: Handle the exception appropriately
        }

        return prospects;
    }

    private Prospect parseProspect(String line) {
        // Use regex to split the line while ignoring commas within quotes
        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        // Check if the line has the expected number of values
        if (values.length != 4) {
            // Handle invalid line
            System.err.println("Skipping invalid line: " + line);
            return null;
        }

        // Check for empty values
        for (String value : values) {
            if (value.trim().isEmpty()) {
                System.err.println("Skipping line due to empty value: " + line);
                return null;
            }
        }

        try {
            long totalLoanCents = parseDecimalStringToLong(values[1].trim());
            int interestRateBps = parseDecimalStringToInteger(values[2].trim());

            return new Prospect(
                    values[0].trim(),
                    totalLoanCents,
                    interestRateBps,
                    Integer.parseInt(values[3].trim())
            );
        } catch (IllegalArgumentException e) {
            // Handle parsing error
            System.err.println("Skipping line due to parsing error: " + line);
            return null;
        }
    }

    public int parseDecimalStringToInteger(String string) {

        // If there are more than two symbols after the decimal point, throw an error
        if (string.contains(".") && string.split("\\.")[1].length() > 2) {
            throw new IllegalArgumentException("Invalid value, more than two decimals: " + string);
        }

        // Normalize the value in the string to two decimal places
        String normalizedValue = normalizeDecimalString(string);

        // Remove the dot
        String valueScaledBy100 = normalizedValue.replaceFirst("\\.", "");

        return Integer.parseInt(valueScaledBy100);
    }

    public long parseDecimalStringToLong(String string) {

        // If there are more than two symbols after the decimal point, throw an error
        if (string.contains(".") && string.split("\\.")[1].length() > 2) {
            throw new IllegalArgumentException("Invalid value, more than two decimals: " + string);
        }

        // Normalize the value in the string to two decimal places
        String normalizedValue = normalizeDecimalString(string);

        // Remove the dot and convert to cents
        String valueScaledBy100 = normalizedValue.replaceFirst("\\.", "");

        return Long.parseLong(valueScaledBy100);
    }

    /**
     * Normalizes a decimal string by ensuring it has two decimal places.
     *
     * @param value The decimal string to be normalized.
     * @return The normalized decimal string.
     */
    private String normalizeDecimalString(String value) {
        if (!value.contains(".")) {
            // Add ".00" if decimal is not present
            return value + ".00";
        }

        // Add a zero after the decimal if there is only one digit after it
        if (value.matches(".*\\.\\d$")) {
            return value + "0";
        }
        return value;
    }

}