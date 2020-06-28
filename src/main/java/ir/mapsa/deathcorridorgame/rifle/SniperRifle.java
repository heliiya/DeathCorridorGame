package ir.mapsa.deathcorridorgame.rifle;

import ir.mapsa.deathcorridorgame.helper.Helper;
import org.bson.Document;

import java.util.Random;

public class SniperRifle extends Rifle{
    private boolean isZoom;

    public SniperRifle(Bullet bullet, boolean isZoom) {
        super(0.60f, 20, Accuracy.HIGH, bullet);
        this.isZoom = isZoom;
        checkZoom();
    }

    public SniperRifle(Document doc) {
        super(doc);
    }

    private void checkZoom() {
        if(isZoom){
            float random = 1;
            try {
                random = Helper.showRandomPercent(0.05f, 0.15f, new Random());
            }catch (IllegalArgumentException e){
                System.err.println("Can't calculate random about zoom of rifle because: " + e.getMessage());
            }
            targetHitRate += random * targetHitRate;
        }
    }

}
