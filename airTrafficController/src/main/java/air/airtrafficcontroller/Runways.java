package air.airtrafficcontroller;

public class Runways{
    public static Runways instance = new Runways();
    private final Runway[] runways; //List of runways

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

    public static Runway[] getRunways(){
        return instance.runways;
    }

    //Add planes
    public static void addPlane(Plane plane, int index){
        getRunways()[index].setPlane(plane);
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
        int count = 0;
        for (Runway runway : instance.runways) {
            if (runway.getState() == Runway.State.FREE) {
                count += 1;
                if(count >= 2)
                    return true;
            }
        }
        return false;
    }

    //Check if plane in or if empty
    public static boolean checkIfAPlaneExistsIn() {
        boolean emptyRunway = false;
        for(Runway r : getRunways()){
            if (!(r.getPlane() == null)) {
                emptyRunway = true;
                break;
            }
        }
        return emptyRunway;
    }

    //Pass hour for each runway
    public static void passHour(){
        for(Runway r : getRunways()){
            r.passHour();
        }
    }
}