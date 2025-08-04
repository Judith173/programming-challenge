package de.exxcellent.challenge;

import java.io.IOException;
import java.util.List;

public class CSVTargetsWithMinDistFinder extends TargetsWithMinDistFinder<String[]>
{
    private String fileName;
    private String targetIdentifier;
    private String xIdentifier;
    private String yIdentifier;

    public CSVTargetsWithMinDistFinder(String fileName, String targetIdentifier, String xIdentifier, String yIdentifier)
    {
        this.fileName = fileName;
        this.targetIdentifier = targetIdentifier;
        this.xIdentifier = xIdentifier;
        this.yIdentifier = yIdentifier;
    }


    @Override
    protected String[][] getData() throws IOException {
        return CSVReader.transformCSVToArray(fileName);
    }
    @Override
    protected double getX(String[] row){
        return 0;
    }

    @Override
    protected double getY(String[] row){
        return 0;
    }
    @Override
    protected String getTarget(String[] row){
        return "";
    }
}
