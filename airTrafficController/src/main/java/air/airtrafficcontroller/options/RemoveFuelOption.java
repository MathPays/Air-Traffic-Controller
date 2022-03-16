package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.WaitingLine;

public class RemoveFuelOption extends Option {

    private final int hour;

    public RemoveFuelOption(int h, String desc)
    {
        super(desc);
        hour = h;
    }

    @Override
    public void performOption() {
        WaitingLine.removeFuel(hour);
    }

    @Override
    public boolean checkRequirement() {
        return true; //No requirements needed
    }
}
