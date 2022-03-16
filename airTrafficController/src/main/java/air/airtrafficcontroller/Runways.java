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

    /**
     * Reboot the runways
     */
    public static void replay() {
        instance = new Runways();
    }

    /**
     * Adds plane to the runways
     * @param plane plane to add
     */
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

    /**
     * Checks if a runway is free
     * @return true if a runway is free
     */
    public static boolean checkIfAvailableRunway() {
        for (Runway runway : instance.runways) {
            if (runway.getState() == Runway.State.FREE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if 2 runways are free
     * @return true if 2 runway are free
     */
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

    /**
     * Checks if a plane is in the runways
     * @return true if a plane is in the runways
     */
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

    /**
     * Pass hour for each runway
     */
    public static void passHour(){
        for(Runway r : getRunways()){
            r.passHour();
        }
    }

    public static Runway[] getRunways(){
        return instance.runways;
    }
}