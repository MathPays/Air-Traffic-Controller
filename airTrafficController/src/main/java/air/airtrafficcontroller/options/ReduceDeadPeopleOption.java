package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Application;
import air.airtrafficcontroller.Game;
import air.airtrafficcontroller.Option;

public class ReduceDeadPeopleOption extends Option {

    private int nb;

    public ReduceDeadPeopleOption(String desc, int nb)
    {
        super(desc);
        this.nb = nb;
    }

    @Override
    public void performOption() {
        Game.reduceDeadPeople(nb);
        Application.updateDeaths();
    }

    @Override
    public boolean checkRequirement() {
        return true; //No requirements needed
    }
}
