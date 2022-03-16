package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Application;
import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Runway;
import air.airtrafficcontroller.Runways;

import java.util.ArrayList;

public class EmptyRandomRunwayOption extends Option {

    public EmptyRandomRunwayOption(String desc)
    {
        super(desc);
    }

    @Override
    public void performOption() { //à retaper pour gérer l'aléatoire
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (Runways.getRunways()[i].getState() == Runway.State.OCCUPIED) {
                indexList.add(i);
            }
        }
        int index = (int) (Math.random() * indexList.size()-1);
        Runways.getRunways()[indexList.get(index)].removePlane(); //state free + remove plane
        Application.updateRunways();
    }

    @Override
    public boolean checkRequirement() {
        return Runways.checkIfAPlaneExistsIn();
    }
}
