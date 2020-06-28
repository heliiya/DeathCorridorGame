package ir.mapsa.deathcorridorgame.helper;

import java.util.Arrays;

public enum TeamType {
    BLUE, RED;

    TeamType() {
    }

    public static TeamType getTeamType(String key) {
        return Arrays.stream(TeamType.values())
                .filter(value -> value.name().equals(key)).findFirst().get();
    }
}
