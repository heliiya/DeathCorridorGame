package ir.mapsa.deathcorridorgame.soldier;

import ir.mapsa.deathcorridorgame.helper.Helper;
import ir.mapsa.deathcorridorgame.helper.TeamType;
import ir.mapsa.deathcorridorgame.rifle.AssaultRifle;
import ir.mapsa.deathcorridorgame.rifle.Rifle;
import ir.mapsa.deathcorridorgame.rifle.SniperRifle;
import org.bson.Document;

import java.util.NoSuchElementException;
import java.util.Random;

public class Soldier {
    private long stamina;
    private Rifle rifle;
    private TeamType teamType;

    public Soldier(Rifle rifle, TeamType teamType) {
        this.rifle = rifle;
        this.teamType = teamType;
        stamina = 100;
    }

    public Soldier(Document doc) {
        Document rifleDoc = (Document) doc.get("rifle");
        String rifleType = rifleDoc.getString("type");
        if(SniperRifle.class.getSimpleName().equals(rifleType)){
            rifle = new SniperRifle(rifleDoc);
        }else if(AssaultRifle.class.getSimpleName().equals(rifleType)){
            rifle = new AssaultRifle(rifleDoc);
        }
        try{
            teamType = TeamType.getTeamType(doc.getString("teamType"));
        }catch(NoSuchElementException e){
            System.err.println(e.getMessage());
        }
        stamina = doc.getLong("stamina");
    }

    public Rifle getRifle() {
        return rifle;
    }

    public boolean beingShot(Soldier attackingSoldier) {
        double random = 0.00f;
        try {
            random = Helper.showRandomPercent(0.00, 1.00, new Random());
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

    public Document generateDocument(){
        Document document = new Document();
        document.append("stamina", stamina);
        document.append("rifle", rifle.generateDocument());
        document.append("teamType", teamType.name());
        return document;
    }

}