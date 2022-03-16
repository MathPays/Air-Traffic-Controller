package air.airtrafficcontroller;

import java.util.ArrayList;

public class WaitingLine {
    public static WaitingLine instance = new WaitingLine();
    private ArrayList<Plane> waitingLine;

    //Singleton constructor
    private WaitingLine(){
        this.waitingLine = new ArrayList<Plane>();
    }

    public static void replay() {
        instance = new WaitingLine();
    }

    //Getters
    public static WaitingLine getInstance() {
        if(instance == null) {
            instance = new WaitingLine();
        }
        return instance;
    }
    public static ArrayList<Plane> getWaitingLine(){
        return instance.waitingLine;
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

    //Land a plane => remove here and add to runways
    public static void landPlane(Plane plane, int index){
        removePlane(plane);
        Runways.addPlane(plane, index);
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

    //Check if plane is here or if empty
    public static boolean checkIfPlaneIn(Plane p){
        return instance.waitingLine.contains(p);
    }
    public static boolean checkIfExistsPlaneIn()
    {
        return instance.waitingLine.isEmpty();
    }

    public static void passHour(){
        instance.removeFuel(1);
    }
}