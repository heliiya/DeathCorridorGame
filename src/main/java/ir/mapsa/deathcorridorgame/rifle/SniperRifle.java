package ir.mapsa.deathcorridorgame.rifle;

import ir.mapsa.deathcorridorgame.helper.Helper;
import org.bson.Document;

import java.util.Random;

public class SniperRifle extends Rifle{
    private boolean isZoom;

    public SniperRifle(Bullet bullet, boolean isZoom) {
        super(0.60, 20, Accuracy.HIGH, bullet);
        this.type = this.getClass().getSimpleName();
        this.isZoom = isZoom;
        checkZoom();
    }

    public SniperRifle(Document doc) {
        super(doc);
        isZoom = doc.getBoolean("isZoom");
    }

    private void checkZoom() {
        if(isZoom){
            double random = 1;
            try {
                random = Helper.showRandomPercent(0.05, 0.15, new Random());
            }catch (IllegalArgumentException e){
                System.err.println("Can't calculate random about zoom of rifle because: " + e.getMessage());
            }
            targetHitRate += random * targetHitRate;
        }
    }

    @Override
    public Document generateDocument(){
        Document doc = super.generateDocument();
        doc.append("isZoom", isZoom);
        return doc;
    }

}