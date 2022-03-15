package air.airtrafficcontroller;

import java.lang.Math;

public class Runways{
    public static Runways instance = new Runways();
    private Runway[] runways;

    private Runways(){
        this.runways = new Runway[8];
        for (int i=0; i < 8; i++) {
            runways[i] = new Runway();
        }
    }

    public static Runways getInstance() {
        if(instance == null) {
            instance = new Runways();
        }
        return instance;
    }

    public static Runway[] getRunways(){
        return instance.runways;
    }

    public static void addPlane(Plane plane, int index){
        instance.getRunways()[index].setPlane(plane);
    }

    public static void addPlane(Plane plane){
        for (Runway runway : instance.runways) {
            if (runway.getState() == Runway.State.FREE) {
                runway.setPlane(plane);
            }
        }
    }

    public static Plane removePlane(int index){
        Plane p = instance.getRunways()[index].getPlane();
        instance.getRunways()[index] = null;
        return p;
    }

    public static void removePlane(Plane plane){
        for(int i = 0; i < instance.getRunways().length; i++){
            if(plane == instance.getRunways()[i].getPlane()){
                instance.getRunways()[i] = null;
                break;
            }
        }
    }

    public static void emptyRandomRunway(){
        int index = (int) (Math.random() * 8);
        instance.getRunways()[index] = null;
    }

    public static boolean checkIfAvailableRunway() {
        for (Runway runway : instance.runways) {
            if (runway.getState() == Runway.State.FREE) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfPlaneIn(Plane plane){
        for(Runway r : instance.getRunways()){
            if(r.getPlane() == plane){
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfAPlaneExistsIn()
    {
        boolean emptyRunway = false;
        for(Runway r : instance.getRunways()){
            if(!(r.getPlane() == null))
                emptyRunway = true;
        }
        return emptyRunway;
    }

    public static void passHour(){
        for(Runway r : instance.getRunways()){
            r.passHour();
        }
    }
}