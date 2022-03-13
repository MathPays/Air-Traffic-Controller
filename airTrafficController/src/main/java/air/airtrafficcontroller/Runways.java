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

    public Runway[] getRunways(){
        return this.runways;
    }

    public static void addPlane(Plane plane, int index){
        instance.getRunways()[index].setPlane(plane);
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

    public static boolean checkIfPlaneIn(Plane plane){
        for(Runway r : instance.getRunways()){
            if(r.getPlane() == plane){
                return true;
            }
        }
        return false;
    }

    public static void passHour(){
        for(Runway r : instance.getRunways()){
            r.passHour();
        }
    }
}