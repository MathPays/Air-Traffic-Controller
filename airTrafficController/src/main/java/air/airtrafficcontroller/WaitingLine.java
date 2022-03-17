package air.airtrafficcontroller;

import java.io.Serializable;
import java.util.ArrayList;

public class WaitingLine implements Serializable {
    public static WaitingLine instance = new WaitingLine();
    private final ArrayList<Plane> waitingLine;

    //Singleton constructor
    private WaitingLine(){
        this.waitingLine = new ArrayList<>();
    }

    public static void replay() {
        instance = new WaitingLine();
    }

    public static void addPlane(Plane p){
        instance.waitingLine.add(p);
        Application.updateWaitingLine();
    }

    public static void removePlane(Plane p){
        instance.waitingLine.remove(p);
        Application.updateWaitingLine();
    }

    public static void removeFuel(int hour){
        for(Plane p : instance.waitingLine){
            p.removeFuel(hour);
        }
        Application.updateWaitingLine();
    }

    public static void addFuel(int hour){
        for(Plane p : instance.waitingLine){
            p.addFuel(hour);
        }
        Application.updateWaitingLine();
    }

    public static void landPlane(Plane plane){
        removePlane(plane);
        Runways.addPlane(plane);
        Application.updateWaitingLine();
    }

    public int size()
    {
        return this.waitingLine.size();
    }

    public static boolean checkIfExistsPlaneIn()
    {
        return !instance.waitingLine.isEmpty();
    }

    public static void passHour(){
        removeFuel(1);
    }

    //Getters
    public static ArrayList<Plane> getWaitingLine(){
        return instance.waitingLine;
    }
}
