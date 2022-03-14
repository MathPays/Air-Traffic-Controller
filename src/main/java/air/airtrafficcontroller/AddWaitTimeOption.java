package air.airtrafficcontroller;

public class AddWaitTimeOption extends Option{

    private int hour;

    public AddWaitTimeOption(String desc, int h)
    {
        super(desc);
        this.hour = h;
    }

    @Override
    public void performOption() {
        for(Runway r : Runways.instance.getRunways())
        {
            r.getPlane().setRunwayTime(r.getRunwayTime() + hour);
        }
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
