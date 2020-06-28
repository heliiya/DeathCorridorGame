package ir.mapsa.deathcorridorgame.rifle;

import java.util.Arrays;

public enum Bullet {
    LOW_CALIBER, HIGH_CALIBER;

    Bullet() {
    }

    public static Bullet getBullet(String key) {
        return Arrays.stream(Bullet.values())
                .filter(value -> value.name().equals(key)).findFirst().get();
    }
}
