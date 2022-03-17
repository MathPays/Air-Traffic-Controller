package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Application;
import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Runway;
import air.airtrafficcontroller.Runways;

import java.io.Serializable;
import java.util.ArrayList;

public class EmptyRandomRunwayOption extends Option implements Serializable {

    public EmptyRandomRunwayOption(String desc)
    {
        super(desc);
    }

    @Override
    public String performOption() {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (Runways.getRunways()[i].getState() == Runway.State.OCCUPIED) {
                indexList.add(i);
            }
        }
        int index = (int) (Math.random() * indexList.size()-1);
        Runways.getRunways()[indexList.get(index)].removePlane(); //state free + remove plane
        Application.updateRunways();
        return "Emptied one runway.";
    }

    @Override
    public boolean checkRequirement() {
        return Runways.checkIfAPlaneExistsIn();
    }
}
