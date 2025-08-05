package de.exxcellent.challenge.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Abstract class for identifying the target(s) with the minimum distance between their x- and y-value.
 */
public abstract class MinDistTargetsFinder<T> {

    /**
     * Find target(s) mit minimum distance between their x- and y-value
     * @return List of targets. Has only one entry if the there is a unique minimum distance.
     * @throws IOException if errors occur while reading the data
     */
    public List<String> findTargetsWithMinDistance() throws IOException {
        List<String> targetsWithMinDist = new ArrayList<>();
        double minDist = Double.POSITIVE_INFINITY;

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

    /**
     * Computes the distance for an element, by default by computing the absolut difference between the x- and y-value of a target
     * @param element depends on implementation. Might be a list or a dictionary mapping targets to x and y
     * @return numeric absolut distance between x and y
     */
    protected double getDistance(T element) {
        return Math.abs(getX(element) - getY(element));
    }

    /**
     * Gets data with information about targets and their x- and y-values
     * @return data as a list
     * @throws IOException if an error occurs while reading the data
     */
    protected abstract List<T> getData() throws IOException;

    /**
     * Gets the x-value for a specific target
     * @param element depends on implementation. Might be a list or a dictionary mapping targets to x and y
     * @return x-value
     */
    protected abstract double getX(T element);

    /**
     * Gets the y-value for a specific target
     * @param element depends on implementation. Might be a list or a dictionary mapping targets to x and y
     * @return y-value
     */
    protected abstract double getY(T element);

    /**
     * Extracts the target from element
     * @param element depends on implementation. Might be a list or a dictionary mapping targets to x and y
     * @return target
     */
    protected abstract String getTarget(T element);
}
