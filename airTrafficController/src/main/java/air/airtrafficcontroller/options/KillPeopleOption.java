package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Application;
import air.airtrafficcontroller.Game;
import air.airtrafficcontroller.Option;

public class KillPeopleOption extends Option {

    private final int number;

    public KillPeopleOption(String desc, int nb)
    {
        super(desc);
        this.number = nb;
    }

    @Override
    public String performOption() {
        Game.killMorePeople(number);
        Application.updateDeaths();
        return number+" people were killed";
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
