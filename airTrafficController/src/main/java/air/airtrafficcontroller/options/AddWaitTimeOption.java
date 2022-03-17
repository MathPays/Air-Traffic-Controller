package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Application;
import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Runway;
import air.airtrafficcontroller.Runways;

import java.io.Serializable;

public class AddWaitTimeOption extends Option implements Serializable {
    private final int hour;

    public AddWaitTimeOption(String desc, int h)
    {
        super(desc);
        this.hour = h;
    }

    @Override
    public String performOption() {
        for(Runway r : Runways.getRunways()) {
            if (r.getState() == Runway.State.OCCUPIED) {
                r.setRunwayTime(r.getRunwayTime() + hour);
            }
        }
        Application.updateRunways();
        return "Wait time added to all planes on the ground.";
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
