package air.airtrafficcontroller.options;

import air.airtrafficcontroller.*;

import java.io.Serializable;

public class CrashPlaneOption extends Option implements Serializable {
    private final int number;

    public CrashPlaneOption(String desc, int nb)
    {
        super(desc);
        this.number = nb;
    }

    @Override
    public String performOption() {
        if(WaitingLine.instance.size() > 0) {
            int id = (int) (Math.random() * WaitingLine.instance.size());
            for(Plane p : WaitingLine.getWaitingLine())
            {
                if(p.getId() == id)
                    WaitingLine.removePlane(p);
            }
        }
        Game.killMorePeople(number);
        Application.updateDeaths();
        Application.updateWaitingLine();
        return "One plane crashed and "+number+" people were killed.";
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
