package air.airtrafficcontroller;

public class AddToWaitingLineOption extends Option{

    private Plane plane;

    public AddToWaitingLineOption(String desc, Plane plane)
    {
        super(desc);
        this.plane = plane;
    }

    @Override
    public void performOption() {
        WaitingLine.addPlane(this.plane);
    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
