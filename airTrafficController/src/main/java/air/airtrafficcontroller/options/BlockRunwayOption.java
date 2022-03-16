package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Application;
import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Runway;
import air.airtrafficcontroller.Runways;

import java.util.ArrayList;

public class BlockRunwayOption extends Option {

    private int hour;
    private Runway.State state;
    //ajouter removed runway, runway aléatoire si pas libre incrémente
    //block 2 runways
    public BlockRunwayOption(String desc, int h, Runway.State s)
    {
        super(desc);
        this.hour = h;
        this.state = s;
    }

    @Override
    public void performOption() {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (Runways.getRunways()[i].getState() == Runway.State.FREE) {
                indexList.add(i);
            }
        }
        if (state == Runway.State.RIOT) {
            int index = (int) (Math.random() * indexList.size() - 1);
            int index2 = (int) (Math.random() * indexList.size() - 1);
            while (index2 == index) {
                index2 = (int) (Math.random() * indexList.size() - 1);
            }
            Runways.getRunways()[indexList.get(index)].setState(state);
            Runways.getRunways()[indexList.get(index)].setRunwayTime(hour);
            Runways.getRunways()[indexList.get(index2)].setState(state);
            Runways.getRunways()[indexList.get(index2)].setRunwayTime(hour);
            Application.updateRunways();
        } else {
            int index = (int) (Math.random() * indexList.size() - 1);
            Runways.getRunways()[indexList.get(index)].setState(state);
            Runways.getRunways()[indexList.get(index)].setRunwayTime(hour);//state free + remove plane
            Application.updateRunways();
        }
    }

    @Override
    public boolean checkRequirement() {
        if (state == Runway.State.RIOT)
            return Runways.checkIf2AvailableRunway();
        return Runways.checkIfAvailableRunway();
    }
}