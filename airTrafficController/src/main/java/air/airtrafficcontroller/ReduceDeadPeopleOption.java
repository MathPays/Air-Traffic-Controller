package air.airtrafficcontroller;

public class ReduceDeadPeopleOption extends Option{

    private int nb;

    public ReduceDeadPeopleOption(String desc, int nb)
    {
        super(desc);
        this.nb = nb;
    }

    @Override
    public void performOption() {
        Game.instance.reduceDeadPeople(nb);
    }

    @Override
    public boolean checkRequirement() {
        return true; //No requirements needed
    }
}
