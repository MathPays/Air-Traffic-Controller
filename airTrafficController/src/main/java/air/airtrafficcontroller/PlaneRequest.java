package air.airtrafficcontroller;

public class PlaneRequest extends Request{
    private Plane plane;

    //Constructor
    public PlaneRequest(String description, String title, double rarity, int h, int r, int p){
        super(description, title, rarity);
        this.createPlane(h, r, p);
    }

    //Constructor with image path
    public PlaneRequest(String description, String title, double rarity, int h, int r, int p, String imagePath){
        super(description, title, rarity, imagePath);
        this.createPlane(h, r, p);
    }

    //Creates the plane associated with the request
    private void createPlane(int h, int r, int p){
        this.plane = new Plane(h, r, p);
    }

    //Getter
    public Plane getPlane(){
        return this.plane;
    }
}
