package de.exxcellent.challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class TargetsWithMinDistFinder<T> {

    public List<String> findTargetsWithMinDistance() throws IOException {
        List<String> targetsWithMinDist = new ArrayList<>();
        double minDist = Double.POSITIVE_INFINITY;

        //TODO: handle empty data
        List<T> data = getData();
        for (T element : data) {
            double dist = getDistance(element);
            if (dist < minDist) {
                minDist = dist;
                targetsWithMinDist.clear();
                targetsWithMinDist.add(getTarget(element));
            }
            else if (dist == minDist) {
                targetsWithMinDist.add(getTarget(element));
            }
        }
        return targetsWithMinDist;
    }

    protected double getDistance(T element) {
        return Math.abs(getX(element) - getY(element));
    }

    protected abstract List<T> getData() throws IOException;

    protected abstract double getX(T element);

    protected abstract double getY(T element);

    protected abstract String getTarget(T element);
}
