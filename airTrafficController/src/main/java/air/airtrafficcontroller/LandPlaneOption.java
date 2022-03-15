package air.airtrafficcontroller;

public class LandPlaneOption extends Option{

    private Plane plane;

    public LandPlaneOption(String desc, Plane plane)
    {
        super(desc);
        this.plane = plane;
    }

    @Override
    public void performOption() {
        for(Runway r : Runways.instance.getRunways())
        {
            if (r.getState().equals(Runway.State.FREE)) {
                r.setPlane(plane);
                r.setState(Runway.State.OCCUPIED);
                r.setRunwayTime(plane.getRunwayTime());
                break;
            }
        }

    }

    @Override
    public boolean checkRequirement() {
        return Runways.checkIfAvailableRunway();
    }
}