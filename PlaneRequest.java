package air.airtrafficcontroller;

public class PlaneRequest extends Request{
    private Plane plane;

    //Constructor
    public PlaneRequest(String description, String title, double rarity, int nbOptions, int h, int r, int p){
        super(description, title, rarity, nbOptions);
        this.createPlane(h, r, p);
    }

    //Constructor with image path
    public PlaneRequest(String description, String title, double rarity, int nbOptions, int h, int r, int p, String imagePath){
        super(description, title, rarity, nbOptions, imagePath);
        this.createPlane(h, r, p);
    }

    //Creates the plane associated with the request
    private void createPlane(int h, int r, int p){
        Plane plane = new Plane(h, r, p);
        this.plane = plane;
    }

    //Getter
    public Plane getPlane(){
        return this.plane;
    }
}
