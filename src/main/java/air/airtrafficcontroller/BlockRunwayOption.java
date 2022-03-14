package air.airtrafficcontroller;

public class BlockRunwayOption extends Option{

    private int hour;
    private Runway.State state;

    public BlockRunwayOption(String desc, int h, Runway.State s)
    {
        super(desc);
        this.hour = h;
        this.state = s;
    }

    @Override
    public void performOption() {
        for(Runway r : Runways.instance.getRunways())
        {
            if(r.getState().equals(Runway.State.FREE))
                if(this.state.equals(Runway.State.PROTEST))
                {
                    r.setState(Runway.State.PROTEST);
                    r.setRunwayTime(hour);
                }
                else if(this.state.equals(Runway.State.RIOT))
                {
                    r.setState(Runway.State.RIOT);
                    r.setRunwayTime(hour);
                }
                break;
            //comment gérer le temps pour repasser en free
        }
    }

    @Override
    public boolean checkRequirement() {
        return Runways.checkIfAvailableRunway();
    }
}
