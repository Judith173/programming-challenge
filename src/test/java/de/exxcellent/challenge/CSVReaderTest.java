package de.exxcellent.challenge;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class CSVReaderTest {
    @Test
    void transformCSVToArrayTest() {
        try{
            String[][] expected ={{"col1", "col2"},{"1", "2"},{"3", "4"}, {"5", "6"}};
            String[][] actual = CSVReader.transformCSVToArray("simpleTestCSV.csv");

            assertEquals(expected.length, actual.length);
            for (int i = 0; i < expected.length; i++)
            {
                assertArrayEquals(expected[i], actual[i]);
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }
}
