package de.exxcellent.challenge;

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
        //TODO: handle index instead of col_name as identifier
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
        //TODO handle empty list
        //TODO handdle CSV without header
        String[] header = content.get(0);
        updateIndexes(header);
        content.remove(0);

        return content;

    }
    @Override
    protected double getX(String[] row){
        //TODO: handle index is -1
        //TODO: handle parseDouble fail
        return Double.parseDouble(row[xIndex]);
    }

    @Override
    protected double getY(String[] row){
        //TODO: handle index is -1
        //TODO: handle parseDouble fail
        return Double.parseDouble(row[yIndex]);
    }
    @Override
    protected String getTarget(String[] row){
        //TODO: handle index is -1
        return row[targetIndex];
    }

    private void updateIndexes(String[] header){
        //TODO: handle identifier is not in header
        //TODO: don't try to update if identifier is index
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
}
