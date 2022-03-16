package air.airtrafficcontroller;

import java.lang.Math;

public class Runways{
    public static Runways instance = new Runways();
    private Runway[] runways; //List of runways

    //Singleton constructor
    private Runways(){
        this.runways = new Runway[8];
        for (int i=0; i < 8; i++) {
            runways[i] = new Runway();
        }
    }

    public static void replay() {
        instance = new Runways();
    }

    //Getters
    public static Runways getInstance() {
        if(instance == null) {
            instance = new Runways();
        }
        return instance;
    }
    public static Runway[] getRunways(){
        return instance.runways;
    }

    //Add planes
    public static void addPlane(Plane plane, int index){
        instance.getRunways()[index].setPlane(plane);
    }
    public static void addPlane(Plane plane){
        for (Runway runway : instance.runways) {
            if (runway.getState() == Runway.State.FREE) {
                runway.setPlane(plane);
                runway.setState(Runway.State.OCCUPIED);
                runway.setRunwayTime(plane.getRunwayTime());
                break;
            }
        }
        Application.updateRunways();
    }

    //Remove planes
    public static Plane removePlane(int index){
        Plane p = instance.getRunways()[index].getPlane();
        instance.getRunways()[index] = null;
        Application.updateRunways();
        return p;
    }
    public static void removePlane(Plane plane){
        for(int i = 0; i < instance.getRunways().length; i++){
            if(plane == instance.getRunways()[i].getPlane()){
                instance.getRunways()[i] = null;
                break;
            }
        }
        Application.updateRunways();
    }

    //Clear a random runway
    public static void emptyRandomRunway(){
        int index = (int) (Math.random() * 8);
        instance.getRunways()[index] = null;
        Application.updateRunways();
    } // TODO: can empty already empty runway, should check if runway FREE or not ?

    //Check available runways
    public static boolean checkIfAvailableRunway() {
        for (Runway runway : instance.runways) {
            if (runway.getState() == Runway.State.FREE) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkIf2AvailableRunway(){
        int compt = 0;

        for (Runway runway : instance.runways) {
            if (runway.getState() == Runway.State.FREE) {
                compt += 1;
            }
        }

        if(compt >= 2)
            return true;
        return false;
    }

    //Check if plane in or if empty
    public static boolean checkIfPlaneIn(Plane plane){
        for(Runway r : instance.getRunways()){
            if(r.getPlane() == plane){
                return true;
            }
        }
        return false;
    }
    public static boolean checkIfAPlaneExistsIn() {
        boolean emptyRunway = false;
        for(Runway r : instance.getRunways()){
            if(!(r.getPlane() == null))
                emptyRunway = true;
        }
        return emptyRunway;
    }

    //Pass hour for each runway
    public static void passHour(){
        for(Runway r : instance.getRunways()){
            r.passHour();
        }
    }
}