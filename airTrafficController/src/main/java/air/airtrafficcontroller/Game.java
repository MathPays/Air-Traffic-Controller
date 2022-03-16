package air.airtrafficcontroller;

import java.util.ArrayList;

public class Game {

    // TODO: 16/03/2022 the loop  
    public static Game instance = new Game();
    private int hour;
    private int peopleKilled;
    private ArrayList<Turn> turns;


    //Singleton constructor
    private Game() {
        this.hour = 0;
        this.peopleKilled = 0;
        this.turns = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            turns.add(new Turn());
        }
    }

    //Resets the game values (for a new game)
    public static void restart() {
        instance.hour = 0;
        instance.peopleKilled = 0;
    }

    // Getters
    public static int getHour() {return instance.hour;}
    public static int getPeopleKilled() {return instance.peopleKilled;}
    public static Turn getCurrentTurn() { return instance.turns.get(instance.hour);}

    // TODO: 16/03/2022 passHour should call the other passHour methods in the other classes
    public static void passHour() {
        instance.hour += 1;
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