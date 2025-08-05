package de.exxcellent.challenge.service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a CSV-file and returns its content as two-dimensional list.
 */
public class CSVReader {
    /**
     * Reads CSV-file. Skips empty lines.
     * @param file name of CSV-file
     * @return content of file as two-dimensional list
     * @throws IOException if there are errors while reading the file
     */
    public static List<String[]> getFileContent(String file) throws IOException {

        if (file == null || file.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename must not be null or empty.");
        }

        List<String[]> rows = new ArrayList<>();

        try (InputStream is = CSVReader.class.getResourceAsStream("../" + file)) {
            if (is == null) {
                String message = String.format("File %s not found in %s", file, CSVReader.class.getResource(""));
                throw new FileNotFoundException(message);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String row;
                while ((row = br.readLine()) != null) {
                    if (!row.trim().isEmpty())
                    {
                        rows.add(row.split(","));
                    }
                }
            }

            return rows;
        }
    }

}
