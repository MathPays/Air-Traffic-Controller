package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Game;
import air.airtrafficcontroller.Option;
import air.airtrafficcontroller.Plane;
import air.airtrafficcontroller.WaitingLine;

public class CrashPlaneOption extends Option {
    private int number;

    public CrashPlaneOption(String desc, int nb)
    {
        super(desc);
        this.number = nb;
    }

    @Override
    public void performOption() {

        if(WaitingLine.instance.size() > 0)
        {
            int id = (int) (Math.random() * WaitingLine.instance.size());
            for(Plane p : WaitingLine.getWaitingLine())
            {
                if(p.getId() == id)
                    WaitingLine.removePlane(p);
            }
        }
        Game.killMorePeople(number);
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
