package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Plane;
import air.airtrafficcontroller.Runways;

import java.io.Serializable;

public class LandPlaneOption extends Option implements Serializable {

    private final Plane plane;

    public LandPlaneOption(String desc, Plane plane)
    {
        super(desc);
        this.plane = plane;
    }

    @Override
    public String performOption() {
        Runways.addPlane(this.plane);
        return "One plane landed successfully.";
    }

    @Override
    public boolean checkRequirement() {
        return Runways.checkIfAvailableRunway();
    }
}
