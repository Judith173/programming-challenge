package de.exxcellent.challenge.service;

import java.io.IOException;
import java.util.List;

public interface TableReader {
    /**
     * Reads tabular data and returns it as a list of arrays.
     * The first row should be the header (colum names)
     *
     * @return list of arrays representing rows and columns
     * @throws IOException if reading fails
     */
    public List<String[]> readContent() throws IOException;
}