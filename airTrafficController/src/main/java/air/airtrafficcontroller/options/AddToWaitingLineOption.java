package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Plane;
import air.airtrafficcontroller.WaitingLine;

public class AddToWaitingLineOption extends Option {

    private final Plane plane;

    public AddToWaitingLineOption(String desc, Plane plane)
    {
        super(desc);
        this.plane = plane;
    }

    @Override
    public void performOption() {
        WaitingLine.addPlane(this.plane);
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
