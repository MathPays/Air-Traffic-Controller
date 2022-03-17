package air.airtrafficcontroller;

import java.io.Serializable;

public class PlaneRequest extends Request implements Serializable {
    private Plane plane;

    //Constructor
    public PlaneRequest(String description, String title, double rarity, int h, int r, int p){
        super(description, title, rarity);
        this.createPlane(h, r, p);
    }


    public PlaneRequest(String description, String title, double rarity, int h, int r, int p, String imagePath){
        super(description, title, rarity, imagePath);
        this.createPlane(h, r, p);
    }

    /**
     * Creates the plane associated to the request
     * @param h hours of fuel
     * @param r hours to wait on the runway
     * @param p number of passengers
     */
    private void createPlane(int h, int r, int p){
        this.plane = new Plane(h, r, p);
    }

    //Getter
    public Plane getPlane(){
        return this.plane;
    }
}
