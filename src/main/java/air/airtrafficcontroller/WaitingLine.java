package air.airtrafficcontroller;

import java.util.ArrayList;

public class WaitingLine {
    public static WaitingLine instance = new WaitingLine();
    private ArrayList<Plane> waitingLine;

    private WaitingLine(){
        this.waitingLine = new ArrayList<Plane>();
    }

    public static WaitingLine getInstance() {
        if(instance == null) {
            instance = new WaitingLine();
        }
        return instance;
    }

    public  ArrayList<Plane> getWaitingLine(){
        return waitingLine;
    }

    public static void addPlane(Plane p){
        instance.waitingLine.add(p);
    }

    public static void removePlane(Plane p){
        instance.waitingLine.remove(p);
    }

    public static void removeFuel(int hour){
        for(Plane p : instance.waitingLine){
            p.removeFuel(hour);
        }
    }

    public static void addFuel(int hour){
        for(Plane p : instance.waitingLine){
            p.addFuel(hour);
        }
    }

    public static void landPlane(Plane plane, int index){
        removePlane(plane);
        Runways.addPlane(plane, index);
    }

    public static void landPlane(Plane plane){
        removePlane(plane);
        Runways.addPlane(plane);
    }

    public static boolean checkIfPlaneIn(Plane p){
        return instance.waitingLine.contains(p);
    }
}
