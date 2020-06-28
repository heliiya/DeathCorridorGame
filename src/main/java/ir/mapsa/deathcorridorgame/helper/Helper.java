package ir.mapsa.deathcorridorgame.helper;

import java.util.Random;

public class Helper {

    public static double showRandomPercent(double start, double end, Random random) throws IllegalArgumentException{
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        double range = end - start + 1;
        double fraction = range * random.nextDouble();
        return fraction + start;
    }

}
