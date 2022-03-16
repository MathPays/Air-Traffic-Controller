package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.WaitingLine;

public class AddFuelOption extends Option {
    private final int hour;

    public AddFuelOption(int hour, String desc)
    {
        super(desc);
        this.hour = hour;
    }

    @Override
    public void performOption() {
        WaitingLine.addFuel(hour);
    }

    @Override
    public boolean checkRequirement() {
        return WaitingLine.checkIfExistsPlaneIn();
    }
}
