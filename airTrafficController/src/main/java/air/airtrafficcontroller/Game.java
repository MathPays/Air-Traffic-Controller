package air.airtrafficcontroller;

public class Game {

    // TODO: main loop to create a game and a turn, interaction with UI

    public static Game instance = new Game();
    public int hour;
    public int peopleKilled;

    // Forced the default constructor
    private Game() {
        this.hour = 0;
        this.peopleKilled = 0;
    }

    // Create a new singleton to could create new game when we want
    public static void restart() {
        instance.hour = 0;
        instance.peopleKilled = 0;
    }

    // Getters
    public static int getHour() {return instance.hour;}
    public static int getPeopleKilled() {return instance.peopleKilled;}

    public static void passHour() {
        instance.hour += 1;
    }

    public static void killMorePeople(int nb) {
        instance.peopleKilled += nb;
    }

    public static void reduceDeadPeople(int nb) {
        if(instance.peopleKilled <= nb)
            instance.peopleKilled = 0;
        else
            instance.peopleKilled -= nb;
    }

    public boolean checkDefeat() {
        return (instance.peopleKilled >= 350);
    }

    public boolean checkVictory() {
        return (instance.hour >= 24);
    }

}