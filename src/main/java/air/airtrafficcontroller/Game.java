package air.airtrafficcontroller;

public class Game {

    public int hour;
    public int peopleKilled;

    // Forced the default constructor
    public Game() {
        this.hour = 0;
        this.peopleKilled = 0;
    }

    // Getter
    public int getHour() {return hour;}

    public int getPeopleKilled() {return peopleKilled;}


    // Methods
    public void passHour() {
        this.hour += 1;
    }

    public void display() {

    }
}