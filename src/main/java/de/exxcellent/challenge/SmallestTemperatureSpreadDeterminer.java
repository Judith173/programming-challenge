package de.exxcellent.challenge;

import java.io.IOException;
import java.util.ArrayList;
import de.exxcellent.challenge.CSVReader;

public class SmallestTemperatureSpreadDeterminer {
    String file;
    String targetCol;
    String minValueCol;
    String maxValueCol;

    public SmallestTemperatureSpreadDeterminer(String file, String targetCol, String minValueCol, String maxValueCol){
        this.file = file;
        this.targetCol = targetCol;
        this.minValueCol = minValueCol;
        this.maxValueCol = maxValueCol;
    }

    public ArrayList<String> findTargetWithSmallestTempSpread() throws IOException {
        ArrayList<String> targetsWithSmallestTempSpread = new ArrayList<>();

        String[][] data = CSVReader.transformCSVToArray(file);
        //TODO: handle empty data
        //TODO: handle CSV without header
        //TODO: handle index as headerCol
        String[] header = data[0];
        int targetIndex = getIndexOfHeaderCol(targetCol, header);
        int minValueIndex = getIndexOfHeaderCol(minValueCol, header);
        int maxValueIndex = getIndexOfHeaderCol(maxValueCol, header);

        double minimalTempSpread = Double.POSITIVE_INFINITY;

        for (int rowIndex = 1; rowIndex < data.length; rowIndex++){
            String[] row = data[rowIndex];
            //TODO: handle parseDouble fail
            double minValue = Double.parseDouble(row[minValueIndex]);
            double maxValue = Double.parseDouble(row[maxValueIndex]);
            double tempSpread = maxValue - minValue;
            if (tempSpread < minimalTempSpread){
                minimalTempSpread = tempSpread;
                targetsWithSmallestTempSpread.clear();
                targetsWithSmallestTempSpread.add(row[targetIndex]);
            }
            else if (tempSpread == minimalTempSpread) {
                targetsWithSmallestTempSpread.add(row[targetIndex]);
            }

        }

        return targetsWithSmallestTempSpread;
    }

    private int getIndexOfHeaderCol(String headerCol, String[] header){
        //TODO: handle headerCol is not in header
        for (int index = 0; index < header.length; index++)
        {
            if (header[index].equals(headerCol)){
                return index;
            }
        }
        return -1;
    }
}
