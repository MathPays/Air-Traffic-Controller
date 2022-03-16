package air.airtrafficcontroller.options;

import air.airtrafficcontroller.Game;
import air.airtrafficcontroller.Option;

public class KillPeopleOption extends Option {

    private int number;

    public KillPeopleOption(String desc, int nb)
    {
        super(desc);
        this.number = nb;
    }

    @Override
    public void performOption() {
        Game.killMorePeople(number);
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
