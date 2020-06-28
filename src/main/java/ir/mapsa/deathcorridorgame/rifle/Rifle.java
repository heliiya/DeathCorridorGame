package ir.mapsa.deathcorridorgame.rifle;

import ir.mapsa.deathcorridorgame.helper.TeamType;
import org.bson.Document;

import java.util.NoSuchElementException;

public abstract class Rifle {
    double targetHitRate;
    int injuryRate;
    Accuracy accuracy;
    private Bullet bullet;

    public Rifle(double targetHitRate, int injuryRate, Accuracy accuracy, Bullet bullet) {
        this.targetHitRate = targetHitRate;
        this.injuryRate = injuryRate;
        this.accuracy = accuracy;
        this.bullet = bullet;
        checkBullet();
    }

    public Rifle(Document doc) {
        targetHitRate = doc.getDouble("targetHitRate");
        injuryRate = doc.getInteger("injuryRate");
        try{
            accuracy = Accuracy.getAccuracy(doc.getString("accuracy"));
        }catch(NoSuchElementException e){
            System.err.println(e.getMessage());
        }
        try{
            bullet = Accuracy.getBullet(doc.getString("bullet"));
        }catch(NoSuchElementException e){
            System.err.println(e.getMessage());
        }
    }

    public double getTargetHitRate() {
        return targetHitRate;
    }

    public int getInjuryRate() {
        return injuryRate;
    }

    private void checkBullet(){
        if(Bullet.LOW_CALIBER.name().equals(bullet)){
            targetHitRate += 0.15f * targetHitRate;
        }else if(Bullet.HIGH_CALIBER.name().equals(bullet)){
            targetHitRate -= 0.1f * targetHitRate;
            injuryRate += 10;
        }
    }

    public Document generateDocument(){
        Document document = new Document();
        document.append("targetHitRate", targetHitRate);
        document.append("injuryRate", injuryRate);
        document.append("accuracy", accuracy.name());
        document.append("bullet", bullet.name());
        return document;
    }

}