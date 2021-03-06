package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.WaitingLine;

import java.io.Serializable;

public class RemoveFuelOption extends Option implements Serializable {

    private final int hour;

    public RemoveFuelOption(int h, String desc)
    {
        super(desc);
        hour = h;
    }

    @Override
    public String performOption() {
        WaitingLine.removeFuel(hour);
        return hour+"H of fuel were removed from each plane in the waiting line.";
    }

    @Override
    public boolean checkRequirement() {
        return true; //No requirements needed
    }
}
