package de.exxcellent.challenge;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {
    public static String[][] transformCSVToArray(String file) throws IOException{
        List<String[]> rows = new ArrayList<>();

        try (InputStream is = CSVReader.class.getResourceAsStream(file)) {
            if (is == null){
                String message = String.format("File %s not found in %s", file, CSVReader.class.getResource(""));
                throw new FileNotFoundException(message);
            }

        try (
             BufferedReader br = new BufferedReader(new InputStreamReader(is)))
        {
            String row;
            while ((row = br.readLine()) != null) {
                rows.add(row.split(","));
            }
        }

        return toArray(rows);}}

    private static String[][] toArray(List<String[]> l){
        String[][] twoDimensionalArray = new String[l.size()][];

        for (int i = 0; i < l.size(); i++) {
            twoDimensionalArray[i] = l.get(i);
        }
        return twoDimensionalArray;
    }
}
