package air.airtrafficcontroller;

public class Game {

    public static Game instance = new Game();
    public int hour;
    public int peopleKilled;


    // Forced the default constructor
    private Game() {
        this.hour = 0;
        this.peopleKilled = 0;
    }

    public static void startGame(){
        if(instance == null)
            instance = new Game();
    }

    // Getter
    public int getHour() {return hour;}

    public int getPeopleKilled() {return peopleKilled;}


    // Methods
    public void passHour() {
        this.hour += 1;
    }

    public static void killMorePeople(int nb)
    {
        instance.peopleKilled += nb;
    }

    public void reduceDeadPeople(int nb)
    {
        if(this.peopleKilled <= nb)
            this.peopleKilled = 0;
        else
            this.peopleKilled -= nb;
    }

    public void display() {

    }


}