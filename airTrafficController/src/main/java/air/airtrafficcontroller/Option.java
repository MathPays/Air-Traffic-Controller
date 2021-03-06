package air.airtrafficcontroller;

import java.io.Serializable;

public abstract class Option implements Serializable {

    private final String desc;

    public Option(String desc) {
        this.desc = desc;
    }

    public abstract String performOption();
    public abstract boolean checkRequirement();
    public String getDesc() {
        return desc;
    }
}
