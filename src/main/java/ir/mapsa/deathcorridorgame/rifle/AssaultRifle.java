package ir.mapsa.deathcorridorgame.rifle;

import org.bson.Document;

public class AssaultRifle extends Rifle {

    public AssaultRifle(Bullet bullet) {
        super(0.50, 10, Accuracy.LOW, bullet);
    }

    public AssaultRifle(Document doc) {
        super(doc);
    }
}
