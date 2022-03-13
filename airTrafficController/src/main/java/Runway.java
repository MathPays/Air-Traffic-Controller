package air.airtrafficcontroller;

public class Runway {
    public enum State{
        FROZEN, FREE, RIOT, OCCUPIED
    }

    private State state;
    private Plane plane;

    public Runway(){
        this.state = State.FREE;
    }

    public Plane getPlane(){
        return this.plane;
    }

    public void setPlane(Plane p){
        this.plane = p;
        this.state = State.OCCUPIED;
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
        if (this.state == State.OCCUPIED) {
            this.plane.passHour();
        }
    }
}
