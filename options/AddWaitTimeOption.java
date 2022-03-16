package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Application;
import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Runway;
import air.airtrafficcontroller.Runways;

public class AddWaitTimeOption extends Option {

    private int hour;

    public AddWaitTimeOption(String desc, int h)
    {
        super(desc);
        this.hour = h;
    }

    @Override
    public void performOption() {
        for(Runway r : Runways.instance.getRunways()) {
            if (r.getState() == Runway.State.OCCUPIED) {
                r.setRunwayTime(r.getRunwayTime() + hour);
            }
        }
        Application.updateRunways();
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
