package de.exxcellent.challenge;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {
    public static List<String[]> getFileContent(String file) throws IOException {

        if (file == null || file.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename must not be null or empty.");
        }

        List<String[]> rows = new ArrayList<>();

        try (InputStream is = CSVReader.class.getResourceAsStream(file)) {
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
