package com.github.akerfeli.mortgageplanbackend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.github.akerfeli.mortgageplanbackend.model.Prospect;
import org.springframework.core.io.ClassPathResource;

/**
 * Utility class to read comma-separated prospect data from a file and create Prospect objects.
 * The file format should be "name,loanAmount,interestRate,loanTerm" per line.
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
            return new Prospect(
                    values[0].trim(),
                    Double.parseDouble(values[1].trim()),
                    Double.parseDouble(values[2].trim()),
                    Integer.parseInt(values[3].trim())
            );
        } catch (NumberFormatException e) {
            // Handle parsing error
            System.err.println("Skipping line due to parsing error: " + line);
            return null;
        }
    }
}