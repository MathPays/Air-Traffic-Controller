package air.airtrafficcontroller;

public class Plane {
    private static int index = 1;
    private final int id;
    private int hoursFuel, runwayTime, passengers;

    public Plane(int h, int r, int p){
        this.hoursFuel = h;
        this.runwayTime = r;
        this.passengers = p;
        this.id = index;
        index++;
    }

    public void addFuel(int hoursToAdd){
        this.hoursFuel += hoursToAdd;
    }
    public void removeFuel(int hoursToSub){
        this.hoursFuel -= hoursToSub;
    }
    public int getRunwayTime() {return runwayTime; }
    public int getId() { return id; }
    public int getHoursFuel() { return hoursFuel; }
}