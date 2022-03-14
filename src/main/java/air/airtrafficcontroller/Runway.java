package air.airtrafficcontroller;

public class Runway {
    public enum State{
        FROZEN, FREE, RIOT, OCCUPIED, PROTEST
    }

    private State state;
    private Plane plane;
    private int runwayTime;

    public Runway(){
        this.state = State.FREE;
        this.runwayTime = 0;
    }

    public Plane getPlane(){
        return this.plane;
    }
    public State getState() {
        return state;
    }
    public int getRunwayTime() { return runwayTime; }

    public void setRunwayTime(int runwayTime) {
        this.runwayTime = runwayTime;
    }

    public void setPlane(Plane p){
        this.plane = p;
        this.state = State.OCCUPIED;
        this.runwayTime = p.getRunwayTime();
    }
    public void removePlane(){
        this.plane = null;
        this.state = State.FREE;
    }
    public void emptyRunway(){
        this.state = State.FREE;
    }
    public void setState(State s){
        this.state = s;
    }
    public void passHour() {

    }
}
