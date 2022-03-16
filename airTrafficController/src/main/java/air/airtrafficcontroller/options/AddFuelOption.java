package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.WaitingLine;

public class AddFuelOption extends Option {

    // A t-on vraiment besoin de cet attribut? On fait pas le plein par défaut ?
    private int hour;

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
