package ir.mapsa.deathcorridorgame.rifle;

public abstract class Rifle {
    float targetHitRate;
    int injuryRate;
    Accuracy accuracy;
    private Bullet bullet;

    public Rifle(float targetHitRate, int injuryRate, Accuracy accuracy, Bullet bullet) {
        this.targetHitRate = targetHitRate;
        this.injuryRate = injuryRate;
        this.accuracy = accuracy;
        this.bullet = bullet;
        checkBullet();
    }

    public float getTargetHitRate() {
        return targetHitRate;
    }

    public int getInjuryRate() {
        return injuryRate;
    }

    private void checkBullet(){
        switch (bullet){
            case LOW_CALIBER:
                targetHitRate += 0.15f * targetHitRate;
                break;
            case HIGH_CALIBER:
                targetHitRate -= 0.1f * targetHitRate;
                injuryRate += 10;
                break;
        }
    }

}