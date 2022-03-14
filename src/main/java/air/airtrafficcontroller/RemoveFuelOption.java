package air.airtrafficcontroller;

public class RemoveFuelOption extends Option{

    private int hour;

    public RemoveFuelOption(int h, String desc)
    {
        super(desc);
        hour = h;
    }

    @Override
    public void performOption() {
        WaitingLine.removeFuel(hour);
    }

    @Override
    public boolean checkRequirement() {
        return true; //No requirements needed
    }
}
