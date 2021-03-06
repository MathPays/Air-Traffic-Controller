package air.airtrafficcontroller;

import java.io.Serializable;

public class Runway implements Serializable {
    public enum State{
        FROZEN, FREE, RIOT, OCCUPIED, PROTEST, REMOVED
    } //Possible states a runway can be in

    private State state;
    private Plane plane;
    private int runwayTime;

    //Constructor
    public Runway(){
        this.state = State.FREE;
        this.runwayTime = 0;
    }

    /**
     * Removes plane from the runway
     */
    public void removePlane(){
        this.plane = null;
        this.state = State.FREE;
    }

    /**
     * Pass hour by decreasing the runway time
     */
    public void passHour() { this.runwayTime--; }

    //Getters
    public Plane getPlane(){
        return this.plane;
    }
    public State getState() {
        return state;
    }
    public int getRunwayTime() { return runwayTime; }

    //Setters
    public void setRunwayTime(int runwayTime) {
        this.runwayTime = runwayTime;
    }
    public void setPlane(Plane p){
        this.plane = p;
        this.state = State.OCCUPIED;
        this.runwayTime = p.getRunwayTime();
    }
    public void setState(State s){
        this.state = s;
    }
}
