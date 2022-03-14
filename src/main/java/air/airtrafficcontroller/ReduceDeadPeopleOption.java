package air.airtrafficcontroller;

public class ReduceDeadPeopleOption extends Option{

    private int nb;
    private Game game;

    public ReduceDeadPeopleOption(String desc, int nb, Game game)
    {
        super(desc);
    }

    @Override
    public void performOption() {
        game.reduceDeadPeople(nb);
    }

    @Override
    public boolean checkRequirement() {
        return true; //No requirements needed
    }
}
