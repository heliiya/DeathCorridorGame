package ir.mapsa.deathcorridorgame.soldier;

import ir.mapsa.deathcorridorgame.helper.Helper;
import ir.mapsa.deathcorridorgame.rifle.Rifle;
import org.bson.types.ObjectId;

import java.util.Random;

public class Soldier {
    private ObjectId id;
    private long stamina;
    private Rifle rifle;

    public Soldier(Rifle rifle) {
        this.rifle = rifle;
        stamina = 100;
    }

    public Rifle getRifle() {
        return rifle;
    }

    public boolean beingShot(Soldier attackingSoldier) {
        float random = 0.00f;
        try {
            random = Helper.showRandomPercent(0.00f, 1.00f, new Random());
        }catch (IllegalArgumentException e){
            System.err.println("Can't calculate random about targetHitRate of shot soldier because: " + e.getMessage());
        }
        Rifle attackingRifle = attackingSoldier.getRifle();
        if(random <= attackingRifle.getTargetHitRate()){
            return true;
        }

        if(stamina > 0){
            stamina -= attackingRifle.getInjuryRate();
        }

        return stamina <= 0;
    }
}