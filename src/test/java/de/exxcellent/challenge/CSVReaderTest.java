package de.exxcellent.challenge;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CSVReaderTest {
    @Test
    void getFileContentTest() {
        try{
            String[][] expected ={{"col1", "col2"},{"1", "2"},{"3", "4"}, {"5", "6"}};
            List<String[]> actual = CSVReader.getFileContent("simpleTestCSV.csv");

            assertEquals(expected.length, actual.size());
            for (int i = 0; i < expected.length; i++)
            {
                assertArrayEquals(expected[i], actual.get(i));
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void transformCSVToArrayTest_emptyCSV() {
        try{
            List<String[]> actual = CSVReader.getFileContent("emptyCSV.csv");
            assertEquals(0, actual.size());
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void transformCSVToArrayTest_onlyHeaderCSV() {
        try{
            String[][] expected ={{"c1", "c2"}};
            List<String[]> actual = CSVReader.getFileContent("onlyHeaderCSV.csv");
            assertEquals(1, actual.size());
            assertArrayEquals(expected[0], actual.get(0));
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void getFileContentTest_nullFile() {
        try{
            List<String[]> actual = CSVReader.getFileContent(null);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }

        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void getFileContentTest_emptyFileName() {
        try{
            CSVReader.getFileContent("");
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }

        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void getFileContentTest_whitespaceFileName() {
        try{
            CSVReader.getFileContent("   ");
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }

        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void getFileContentTest_fileDoesNotExist() {
        try{
            CSVReader.getFileContent("thisFileDoesNotExist.csv");
            fail();
        }
        catch (FileNotFoundException e)
        {
            assertTrue(true);
        }

        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }
}
