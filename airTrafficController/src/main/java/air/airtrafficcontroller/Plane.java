package air.airtrafficcontroller;

import java.io.Serializable;

public class Plane implements Serializable {
    private static int index = 1; //All planes indexed
    private final int id;
    private final int runwayTime, passengers; //hoursFuel = time left with fuel; runwayTime = time necessary in runway; passenger = number of passengers on plane
    private int hoursFuel;

    //constructor
    public Plane(int hoursFuel, int runwayTime, int passengers){
        this.hoursFuel = hoursFuel;
        this.runwayTime = runwayTime;
        this.passengers = passengers;
        this.id = index;
        index++;
    }

    /**
     * Adds fuel to the plane
     * @param hoursToAdd hours of fuel to add
     */
    public void addFuel(int hoursToAdd){
        this.hoursFuel += hoursToAdd;
    }

    /**
     * Removes fuel from the plane
     * @param hoursToSub hours of fuel to remove
     */
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