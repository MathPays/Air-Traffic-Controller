package air.airtrafficcontroller;

public class Game {

    public static Game instance = new Game();
    private int hour;
    private int peopleKilled;

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
        Application.updateHour(instance.hour);
    }

    /**
     * increases the number of dead people
     * @param nb the number of people to add
     */
    public static void killMorePeople(int nb) {
        instance.peopleKilled += nb;
        Application.updateDeaths(instance.peopleKilled);
    }

    /**
     * decreases the number of dead people
     * @param nb the number of dead people to be removed
     */
    public static void reduceDeadPeople(int nb) {
        if(instance.peopleKilled <= nb)
            instance.peopleKilled = 0;
        else
            instance.peopleKilled -= nb;
        Application.updateDeaths(instance.peopleKilled);
    }

}