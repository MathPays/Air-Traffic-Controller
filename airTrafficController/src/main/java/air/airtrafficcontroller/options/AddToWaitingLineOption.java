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
    public String performOption() {
        WaitingLine.addPlane(this.plane);
        return "Plane added to the waiting line.";
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
