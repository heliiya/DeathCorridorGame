package ir.mapsa.deathcorridorgame.rifle;

import ir.mapsa.deathcorridorgame.helper.TeamType;

import java.util.Arrays;

public enum Accuracy {
    LOW, HIGH;

    Accuracy() {
    }

    public static Accuracy getAccuracy(String key) {
        return Arrays.stream(Accuracy.values())
                .filter(value -> value.name().equals(key)).findFirst().get();
    }

}
