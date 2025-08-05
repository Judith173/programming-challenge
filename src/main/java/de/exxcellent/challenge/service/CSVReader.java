package de.exxcellent.challenge.service;

import de.exxcellent.challenge.exception.InvalidCSVFormatException;

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
public class CSVReader implements TableReader{

    private String file;

    public CSVReader(String csvFileName)
    {
        this.file = csvFileName;
    }
    /**
     * Reads CSV-file. Skips empty lines.
     * @param file name of CSV-file
     * @return content of file as two-dimensional list
     * @throws IOException if errors occur while reading the file or the file does not have the required format
     */
    @Override
    public List<String[]> readContent() throws IOException {

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
                int nbrCols = 0;
                while ((row = br.readLine()) != null) {
                    if (!row.trim().isEmpty())
                    {
                        String[] cols = row.split(",");
                        if (nbrCols == 0){
                            nbrCols = cols.length;
                        }
                        validateNbrElements(cols, nbrCols);
                        rows.add(row.split(","));
                    }
                }
            }

            return rows;
        }


    }
    private void validateNbrElements(String[] cols, int expectedNbrElements) throws InvalidCSVFormatException
    {

        if (cols.length != expectedNbrElements)
        {
            String message = String.format("Error: Invalid number of elements in line '%s': Expected: %d, Actual: %d",
                    String.join(", ", cols), expectedNbrElements, cols.length);
            throw new InvalidCSVFormatException(message);
        }
    }

}
