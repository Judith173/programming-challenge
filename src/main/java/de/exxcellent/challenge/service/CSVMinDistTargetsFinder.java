package de.exxcellent.challenge.service;

import de.exxcellent.challenge.exception.InvalidCSVFormatException;

import java.io.IOException;
import java.util.List;

public class CSVMinDistTargetsFinder extends MinDistTargetsFinder<String[]>
{
    private final String fileName;
    private final String targetIdentifier;
    private final String xIdentifier;
    private final String yIdentifier;
    private int targetIndex;
    private int xIndex;
    private int yIndex;

    public CSVMinDistTargetsFinder(String fileName, String targetIdentifier, String xIdentifier, String yIdentifier)
    {
        this.fileName = fileName;
        this.targetIdentifier = targetIdentifier;
        this.xIdentifier = xIdentifier;
        this.yIdentifier = yIdentifier;

        this.targetIndex = -1;
        this.xIndex = -1;
        this.yIndex = -1;
    }


    @Override
    protected List<String[]> getData() throws IOException {
        List<String[]> content =  CSVReader.getFileContent(fileName);
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
    @Override
    protected double getX(String[] row){
        assert (xIndex >= 0 && xIndex < row.length);
        return Double.parseDouble(row[xIndex]);
    }

    @Override
    protected double getY(String[] row){
        assert (yIndex >=0 && yIndex < row.length);
        return Double.parseDouble(row[yIndex]);
    }
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
