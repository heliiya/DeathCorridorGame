package ir.mapsa.deathcorridorgame.helper;

import ir.mapsa.deathcorridorgame.rifle.AssaultRifle;
import ir.mapsa.deathcorridorgame.rifle.Bullet;
import ir.mapsa.deathcorridorgame.rifle.Rifle;
import ir.mapsa.deathcorridorgame.rifle.SniperRifle;

public enum RifleType {
    ASSAULT_RIFLE_LOW_CALIBER(1), ASSAULT_RIFLE_HIGH_CALIBER(2),
    SNIPER_RIFLE_LOW_CALIBER(3), SNIPER_RIFLE_LOW_CALIBER_HAS_ZOOM(4),
    SNIPER_RIFLE_HIGH_CALIBER(5), SNIPER_RIFLE_HIGH_CALIBER_HAS_ZOOM(6);

    int id;

    RifleType(int id) {
        this.id = id;
    }

    public static RifleType getRifleType(int id) {
        for (RifleType value : RifleType.values())
            if(value.id == id)
                return value;
        return null;
    }

    public Rifle getRifle(){
        switch (this){
            case ASSAULT_RIFLE_LOW_CALIBER:
                return new AssaultRifle(Bullet.LOW_CALIBER);
            case ASSAULT_RIFLE_HIGH_CALIBER:
                return new AssaultRifle(Bullet.HIGH_CALIBER);
            case SNIPER_RIFLE_LOW_CALIBER:
                return new SniperRifle(Bullet.LOW_CALIBER, false);
            case SNIPER_RIFLE_LOW_CALIBER_HAS_ZOOM:
                return new SniperRifle(Bullet.LOW_CALIBER, true);
            case SNIPER_RIFLE_HIGH_CALIBER:
                return new SniperRifle(Bullet.HIGH_CALIBER, false);
            case SNIPER_RIFLE_HIGH_CALIBER_HAS_ZOOM:
                return new SniperRifle(Bullet.HIGH_CALIBER, true);
        }
        return null;
    }
}