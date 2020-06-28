package ir.mapsa.deathcorridorgame.manager;

import ir.mapsa.deathcorridorgame.helper.RifleType;
import ir.mapsa.deathcorridorgame.soldier.Soldier;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    private static Queue<Soldier> teamA;
    private static Queue<Soldier> teamB;

    public static void main(String[] args) {
        print("~~WELCOME TO DEATH CORRIDOR GAME~~");
        teamA = new LinkedList<>();
        teamB = new LinkedList<>();

        print("How many soldier do you want?");
        Scanner sc = new Scanner(System.in);
        int soldierCount = sc.nextInt();
        populateTeams(soldierCount);

        while (true){
            int sizeA = teamA.size();
            int sizeB = teamB.size();
            print("Team A has " + sizeA + " soldier, and team B has " + sizeB + " soldier.");
            if(sizeA == 0 && sizeB == 0){
                print("Both team was equal.");
                break;
            }
            if(sizeA == 0){
                print("Team B won.");
                break;
            }
            if(sizeB == 0){
                print("Team A won.");
                break;
            }
            game();
        }
    }

    private static void populateTeams(int soldierCount) {
        for (int i=0; i<soldierCount; i++){
            addToTeamA();
            addToTeamB();
        }
    }

    private static void addToTeamB() {
        Random random = new Random();
        int randomRifleStateB = random.nextInt(6);
        RifleType rifleTypeB = RifleType.getRifleState(randomRifleStateB);
        if(rifleTypeB != null){
            teamB.add(new Soldier(rifleTypeB.getRifle()));
        }else {
            addToTeamB();
        }
    }

    private static void addToTeamA() {
        Random random = new Random();
        int randomRifleStateA = random.nextInt(6);
        RifleType rifleTypeA = RifleType.getRifleState(randomRifleStateA);
        if(rifleTypeA != null){
            teamA.add(new Soldier(rifleTypeA.getRifle()));
        }else{
            addToTeamA();
        }
    }

    private static void game() {
        Soldier soldierA = teamA.poll();
        Soldier soldierB = teamB.poll();
        Random random = new Random();
        int firstShoot = random.nextInt(2);
        if(firstShoot == 1){
            boolean isDeathA = soldierA.beingShot(soldierB);
            if(!isDeathA){
                boolean isDeathB = soldierB.beingShot(soldierA);
                if(!isDeathB){
                    teamB.add(soldierB);
                }
                teamA.add(soldierA);
            }
        }else{
            boolean isDeathB = soldierB.beingShot(soldierA);
            if(!isDeathB){
                boolean isDeathA = soldierA.beingShot(soldierB);
                if(!isDeathA){
                    teamA.add(soldierA);
                }
                teamB.add(soldierB);
            }
        }
    }

    private static void print(String text){
        System.out.println(text);
    }

}