package ir.mapsa.deathcorridorgame.helper;

import java.util.Random;

public class Helper {

    public static float showRandomPercent(float start, float end, Random random) throws IllegalArgumentException{
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        double range = (double)end - (double)start + 1;
        double fraction = range * random.nextDouble();
        return (float)(fraction + start);
    }

}
