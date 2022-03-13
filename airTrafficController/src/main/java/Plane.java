package air.airtrafficcontroller;

public class Plane {
    private static int index = 1;
    private int id;
    private int hoursFuel, runwayTime, passengers;

    public Plane(int h, int r, int p){
        this.hoursFuel = h;
        this.runwayTime = r;
        this.passengers = p;
        this.id = index;
        index++;
    }

    public void passHour(){
        this.runwayTime --;
    }
    public void addFuel(int hoursToAdd){
        this.hoursFuel += hoursToAdd;
    }
    public void removeFuel(int hoursToSub){
        this.hoursFuel -= hoursToSub;
    }
}