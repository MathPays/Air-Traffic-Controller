package air.airtrafficcontroller;

public abstract class Option {

    private final String desc;

    public Option(String desc)
    {
        this.desc = desc;
    }

    public abstract void performOption();
    public abstract boolean checkRequirement();
    public String getDesc() {
        return desc;
    }
}
