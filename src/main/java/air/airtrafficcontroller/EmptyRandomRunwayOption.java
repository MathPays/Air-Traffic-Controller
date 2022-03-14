package air.airtrafficcontroller;

public class EmptyRandomRunwayOption extends Option{

    private Runway.State state;

    public EmptyRandomRunwayOption(String desc)
    {
        super(desc);
    }

    @Override
    public void performOption() { //à retaper pour gérer l'aléatoire
        for(Runway r : Runways.instance.getRunways())
        {
            if(r.getState().equals(Runway.State.OCCUPIED))
                r.emptyRunway();
                break;
        }
    }

    @Override
    public boolean checkRequirement() {
        return Runways.checkIfAPlaneExistsIn();
    }
}
