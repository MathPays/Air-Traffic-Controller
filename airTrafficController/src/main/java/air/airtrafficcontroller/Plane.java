package air.airtrafficcontroller;

public class Plane {
    private static int index = 1; //All planes indexed
    private final int id;
    private int hoursFuel, runwayTime, passengers; //hoursFuel = time left with fuel; runwayTime = time necessary in runway; passenger = number of passengers on plane

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
        this.hoursFuel -= hoursToSub;
    }
    public void setRunwayTime(int runwayTime) {
        this.runwayTime = runwayTime;
    }

    //Getters
    public int getId() { return this.id; }
    public int getHoursFuel() { return this.hoursFuel; }
    public int getPassengers() { return this.passengers; }
    public int getRunwayTime() {return runwayTime; }

    //Pass hour once in runway
    public void passHour() { this.hoursFuel--; } // TODO: maybe change this to runwayTime-- (pass hour in waiting line)
}