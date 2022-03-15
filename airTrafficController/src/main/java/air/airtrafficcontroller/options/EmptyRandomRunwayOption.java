package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Runway;
import air.airtrafficcontroller.Runways;

public class EmptyRandomRunwayOption extends Option {

    private Runway.State state;

    public EmptyRandomRunwayOption(String desc)
    {
        super(desc);
    }

    @Override
    public void performOption() { //à retaper pour gérer l'aléatoire

        int index = (int) (Math.random() * 8);
        for(int i = index; i < 7; i++) {
            if(!(Runways.getRunways()[i].getState().equals(Runway.State.OCCUPIED)))
            {
                if(i == 7) //We go back to the beggining of the loop until we find a free runway
                    i = -1; //will be incremented at 0 automatically
                continue;
            }
            else
                Runways.getRunways()[i].removePlane(); //state free + remove plane
            break;
        }
    }

    @Override
    public boolean checkRequirement() {
        return Runways.checkIfAPlaneExistsIn();
    }
}
