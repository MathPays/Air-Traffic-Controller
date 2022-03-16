package air.airtrafficcontroller;

public class Plane {
    private static int index = 1; //All planes indexed
    private final int id;
    private final int runwayTime, passengers; //hoursFuel = time left with fuel; runwayTime = time necessary in runway; passenger = number of passengers on plane
    private int hoursFuel;

    //constructor
    public Plane(int h, int r, int p){
        this.hoursFuel = h;
        this.runwayTime = r;
        this.passengers = p;
        this.id = index;
        index++;
    }

    //Setters / Modifiers
    public void addFuel(int hoursToAdd){
        this.hoursFuel += hoursToAdd;
    }
    public void removeFuel(int hoursToSub){
        if (hoursFuel >= 0) {
            this.hoursFuel -= hoursToSub;
        }
    }

    //Getters
    public int getId() { return this.id; }
    public int getHoursFuel() { return this.hoursFuel; }
    public int getPassengers() { return this.passengers; }
    public int getRunwayTime() { return runwayTime; }
}