package ir.mapsa.deathcorridorgame.manager;

import ir.mapsa.deathcorridorgame.helper.RifleType;
import ir.mapsa.deathcorridorgame.helper.TeamType;
import ir.mapsa.deathcorridorgame.soldier.Soldier;
import org.bson.Document;

import java.util.*;

public class GameManager {
    private static DatabaseManager databaseManager;

    public static void main(String[] args) {
        databaseManager = DatabaseManager.getInstance();
        print("~~WELCOME TO DEATH CORRIDOR GAME~~");

        print("How many soldier do you want?");
        Scanner sc = new Scanner(System.in);
        int soldierCount = sc.nextInt();
        populateTeams(soldierCount);

        while (true){
            long blueTeamCounts = databaseManager.getSoldierCount(TeamType.BLUE);
            long redTeamCounts = databaseManager.getSoldierCount(TeamType.RED);
            print("Team BLUE has " + blueTeamCounts + " soldier, and team RED has " + redTeamCounts + " soldier.");
            if(blueTeamCounts == 0 && redTeamCounts == 0){
                print("Both team was equal.");
                break;
            }
            if(blueTeamCounts == 0){
                print("Team RED won.");
                break;
            }
            if(redTeamCounts == 0){
                print("Team BLUE won.");
                break;
            }
            fight();
        }
    }

    private static void populateTeams(int soldierCount) {
        Random random = new Random();
        List<Document> soldiers = new ArrayList<>();
        for (int i=0; i<soldierCount; i++){
            addSoldier(soldiers, random, TeamType.BLUE);
            addSoldier(soldiers, random, TeamType.RED);
        }
        databaseManager.addSoldiers(soldiers);
    }

    private static void addSoldier(List<Document> soldiers, Random random, TeamType teamType) {
        int rifleTypeId = random.nextInt(6);
        RifleType rifleType = RifleType.getRifleType(rifleTypeId);
        if(rifleType != null){
            soldiers.add(new Soldier(rifleType.getRifle(), teamType).generateDocument());
        }
    }

    private static void fight() {
        Soldier blueSoldier = databaseManager.findAndDeleteFirstSoldier(TeamType.BLUE);
        Soldier redSoldier = databaseManager.findAndDeleteFirstSoldier(TeamType.RED);

        Random random = new Random();
        int firstShoot = random.nextInt(2);
        if(firstShoot == 1){
            boolean isDeathA = blueSoldier.beingShot(redSoldier);
            if(!isDeathA){
                boolean isDeathB = redSoldier.beingShot(blueSoldier);
                if(!isDeathB){
                    databaseManager.addSoldier(redSoldier);
                }
                databaseManager.addSoldier(blueSoldier);
            }
        }else{
            boolean isDeathB = redSoldier.beingShot(blueSoldier);
            if(!isDeathB){
                boolean isDeathA = blueSoldier.beingShot(redSoldier);
                if(!isDeathA){
                    databaseManager.addSoldier(blueSoldier);
                }
                databaseManager.addSoldier(redSoldier);
            }
        }
    }

    private static void print(String text){
        System.out.println(text);
    }

}