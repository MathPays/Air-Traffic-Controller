package air.airtrafficcontroller;

import java.util.ArrayList;

public class Request {
    private String description, title;
    private String imagePath; //image used in UI
    private double rarity;
    private ArrayList<Option> listOptions; //list of options to deal with request

    //Basic constructor
    public Request(String description, String title, double rarity, int nbOptions){
        this.description = description;
        this.imagePath = "plane_landing.gif";
        this.title = title;
        this.rarity = rarity;
        this.listOptions = new ArrayList<>();
    }

    //Constructor with request image
    public Request(String description, String title, double rarity, int nbOptions, String imagePath) {
        this.description = description;
        this.title = title;
        this.imagePath = imagePath;
        this.rarity = rarity;
        this.listOptions = new ArrayList<>();
    }

    //Add an option to option list
    public void addOption(Option option){
       listOptions.add(option);
    }

    public String toString(){
        String res = this.title + " : " + this.description + "\n";
        for(int i = 0; i < this.listOptions.size(); i++){
            res += "Option " + i + " : " + listOptions.get(i).toString() + "\n";
        }
        return res;
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
