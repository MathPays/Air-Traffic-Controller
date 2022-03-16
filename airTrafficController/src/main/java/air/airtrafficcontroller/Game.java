package air.airtrafficcontroller;

import java.util.ArrayList;

public class Game {
    public static Game instance = new Game();
    private int hour;
    private int peopleKilled;
    private final ArrayList<Turn> turns;


    //Singleton constructor
    private Game() {
        this.hour = 0;
        this.peopleKilled = 0;
        this.turns = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            turns.add(new Turn());
        }
    }

    public static void replay() {
        instance = new Game();
        Runways.replay();
        WaitingLine.replay();
        Application.updateWaitingLine();
        Application.updateRunways();
        Application.updateDeaths();
        Application.updateHour();
    }

    public static boolean checkDefeat() {
        if (instance.peopleKilled >= 350) {
            Application.displayGameOver();
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkVictory() {
        if (instance.hour >= 23) {
            Application.displayVictory();
            return true;
        } else {
            return false;
        }
    }

    // Getters
    public static int getHour() {return instance.hour;}
    public static int getPeopleKilled() {return instance.peopleKilled;}
    public static Turn getCurrentTurn() {
        return instance.turns.get(instance.hour);
    }

    //calls methods that move entities to next hour
    public static boolean passHour() {
        Runways.passHour();
        WaitingLine.passHour();
        getCurrentTurn().allTakeOff(); //update runways, get rid of planes that are done waiting
        instance.hour += 1;
        if (getCurrentTurn().crashWaitingPlanes() || checkDefeat()  || checkVictory())
            return false;
        Application.updateWaitingLine();
        Application.updateRunways();
        Application.updateHour();
        Application.updateDeaths();
        return true;
    }

    //Change amount of dead people (see option consequences)
    public static void killMorePeople(int nb) {
        instance.peopleKilled += nb;
    }
    public static void reduceDeadPeople(int nb) {
        if(instance.peopleKilled <= nb)
            instance.peopleKilled = 0;
        else
            instance.peopleKilled -= nb;
    }



}