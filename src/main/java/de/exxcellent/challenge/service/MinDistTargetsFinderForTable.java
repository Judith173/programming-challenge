package de.exxcellent.challenge.service;

import de.exxcellent.challenge.exception.InvalidCSVFormatException;

import java.io.IOException;
import java.util.List;

/**
 * Identifies the target(s) with the minimum distance between their x- and y-value by using tabular data
 */
public class MinDistTargetsFinderForTable extends MinDistTargetsFinder<String[]>
{
    private final String fileName;
    private final String targetIdentifier;
    private final String xIdentifier;
    private final String yIdentifier;
    private int targetIndex;
    private int xIndex;
    private int yIndex;

    /**
     *
     * @param fileName name of file containing tabular data
     * @param targetIdentifier column header of target column
     * @param xIdentifier column header of x-value-column
     * @param yIdentifier column header of y-value-column
     */
    public MinDistTargetsFinderForTable(String fileName, String targetIdentifier, String xIdentifier, String yIdentifier)
    {
        this.fileName = fileName;
        this.targetIdentifier = targetIdentifier;
        this.xIdentifier = xIdentifier;
        this.yIdentifier = yIdentifier;

        this.targetIndex = -1;
        this.xIndex = -1;
        this.yIndex = -1;
    }

    /**
     * Reads a file with tabular data, validates it and returns its content (excluding the header)
     * @return Table content (excluding header) as two-dimensional list
     * @throws IOException if an error occurs while reading or validating the csv file
     *      (for example if not all values of an x- or y-column are numeric)
     */
    @Override
    protected List<String[]> getData() throws IOException {
        TableReader reader = new CSVReader(fileName);
        List<String[]> content =  reader.readContent();
        if (content.isEmpty())
        {
            return content;
        }
        String[] header = content.get(0);
        updateIndexes(header);
        content.remove(0);
        validateData(content, header);

        return content;

    }

    /**
     * Extracts the x-value from a specific row in table
     * @param row of table (represented by an array)
     * @return x-value
     */
    @Override
    protected double getX(String[] row){
        assert (xIndex >= 0 && xIndex < row.length);
        return Double.parseDouble(row[xIndex]);
    }

    /**
     * Extracts the y-value from a specific row in the table
     * @param row of table (represented by an array)
     * @return y-value
     */
    @Override
    protected double getY(String[] row){
        assert (yIndex >=0 && yIndex < row.length);
        return Double.parseDouble(row[yIndex]);
    }

    /**
     * Extracts the target from a specific row in the table
     * @param row of table (represented by an array)
     * @return target
     */
    @Override
    protected String getTarget(String[] row){
        assert (targetIndex >=0 && targetIndex < row.length);
        return row[targetIndex];
    }

    private void updateIndexes(String[] header){
        targetIndex = getIndexOfElement(targetIdentifier, header);
        xIndex = getIndexOfElement(xIdentifier, header);
        yIndex = getIndexOfElement(yIdentifier, header);
    }

    private int getIndexOfElement(String element, String[] a){
        for (int index = 0; index < a.length; index++)
        {
            if (a[index].equals(element)){
                return index;
            }
        }
        return -1;
    }

    private void validateIdentifier(String identifier, int identifierIndex, String[] header){
        if(identifierIndex == -1)
        {
            String message = String.format("Identifier '%s' is invalid. Valid identifiers: %s", identifier, String.join(", ", header));
            throw new IllegalArgumentException(message);
        }
    }

    private void validateData(List<String[]> dataWithoutHeader, String[] header) throws InvalidCSVFormatException
    {
        validateIdentifier(targetIdentifier, targetIndex, header);
        validateIdentifier(xIdentifier, xIndex, header);
        validateIdentifier(yIdentifier, yIndex, header);

        for (String[] row : dataWithoutHeader){
            validateValue(row[xIndex], xIdentifier, row);
            validateValue(row[yIndex], yIdentifier, row);
        }
    }


    private void validateValue(String value, String colName, String[] row) throws InvalidCSVFormatException{
        try {
            Double.parseDouble(value);
        }
        catch (NumberFormatException e)
        {
            String message = String.format("Error: To compute the minimum distance each value of column '%s' must be " +
                    "numeric. Invalid value '%s' in line '%s'.", colName, value, String.join(", ", row));
            throw new InvalidCSVFormatException(message);
        }
    }

}
