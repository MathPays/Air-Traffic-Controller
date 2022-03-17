package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Application;
import air.airtrafficcontroller.Game;
import air.airtrafficcontroller.Option;

import java.io.Serializable;

public class ReduceDeadPeopleOption extends Option implements Serializable {

    private final int nb;

    public ReduceDeadPeopleOption(String desc, int nb)
    {
        super(desc);
        this.nb = nb;
    }

    @Override
    public String performOption() {
        Game.reduceDeadPeople(nb);
        Application.updateDeaths();
        return nb+" people were saved";
    }

    @Override
    public boolean checkRequirement() {
        return true; //No requirements needed
    }
}
