package air.airtrafficcontroller;

public class PlaneRequest extends Request{
    private Plane plane;

    public PlaneRequest(String description, String title, double rarity, int nbOptions, int h, int r, int p){
        super(description, title, rarity, nbOptions);
        this.createPlane(h, r, p);
    }

    private void createPlane(int h, int r, int p){
        Plane plane = new Plane(h, r, p);
        this.plane = plane;
    }

    public Plane getPlane(){
        return this.plane;
    }
}
