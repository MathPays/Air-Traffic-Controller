package air.airtrafficcontroller;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private final String description, title;
    private final String imagePath; //image used in UI
    private final double rarity;
    private final ArrayList<Option> listOptions; //list of options to deal with request

    //Basic constructor
    public Request(String description, String title, double rarity){
        this.description = description;
        this.imagePath = "plane_landing.gif";
        this.title = title;
        this.rarity = rarity;
        this.listOptions = new ArrayList<>();
    }

    //Constructor with request image
    public Request(String description, String title, double rarity, String imagePath) {
        this.description = description;
        this.title = title;
        this.imagePath = imagePath;
        this.rarity = rarity;
        this.listOptions = new ArrayList<>();
    }

    /**
     * Adds option to the options list
     * @param option option to add to the list
     */
    public void addOption(Option option){
       listOptions.add(option);
    }

    //Getters
    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }
    public String getImagePath() {
        return imagePath;
    }
    public ArrayList<Option> getListOptions() {
        return listOptions;
    }
}
