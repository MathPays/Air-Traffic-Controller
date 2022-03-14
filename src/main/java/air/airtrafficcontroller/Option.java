package air.airtrafficcontroller;

public abstract class Option {

    private String desc;

    public Option(String desc)
    {
        this.desc = desc;
    }

    public abstract void performOption();
    public abstract boolean checkRequirement();
}
