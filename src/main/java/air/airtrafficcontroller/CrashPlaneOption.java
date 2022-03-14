package air.airtrafficcontroller;

public class CrashPlaneOption extends Option{ //diffrence avec KillPeopleOption ?

    public CrashPlaneOption(String desc)
    {
        super(desc);
    }

    @Override
    public void performOption() {

    }

    @Override
    public boolean checkRequirement() {
        return true;
    }
}
