package air.airtrafficcontroller;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    public static Game instance = new Game();
    private int hour;
    private int peopleKilled;
    private final ArrayList<Turn> turns;

    private Game() {
        this.hour = 0;
        this.peopleKilled = 0;
        this.turns = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            turns.add(new Turn());
        }
    }


    /**
     * Reboots all the informations of the game for the user to replay the game
     */
    public static void replay() {
        instance = new Game();
        Runways.replay();
        WaitingLine.replay();
        Application.updateWaitingLine();
        Application.updateRunways();
        Application.updateDeaths();
        Application.updateHour();
    }

    /**
     * Checks if the game is over
     * @return true if game over
     */
    public static boolean checkDefeat() {
        if (instance.peopleKilled >= 350) {
            Application.displayGameOver();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if user meets victory requirements
     * @return true if victory
     */
    public static boolean checkVictory() {
        if (instance.hour >= 24) {
            Application.displayVictory();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Pass the hour by checking if the user wins, looses or if planes crash
     * @return true if continue the game
     */
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

    /**
     * Add people to the number of people killed
     * @param nb number of people killed
     */
    public static void killMorePeople(int nb) {
        instance.peopleKilled += nb;
    }

    /**
     * Reduce people to the number of people killed
     * @param nb number of people to save
     */
    public static void reduceDeadPeople(int nb) {
        if(instance.peopleKilled <= nb)
            instance.peopleKilled = 0;
        else
            instance.peopleKilled -= nb;
    }

    // Getters
    public static int getHour() {return instance.hour;}
    public static int getPeopleKilled() {return instance.peopleKilled;}
    public static Turn getCurrentTurn() {
        return instance.turns.get(instance.hour);
    }
}