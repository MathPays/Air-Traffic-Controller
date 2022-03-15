package air.airtrafficcontroller;

import java.util.ArrayList;

public class Request {
    private String description, title;
    private String imagePath;
    private double rarity;
    private ArrayList<Option> listOptions;

    public Request(String description, String title, double rarity, int nbOptions){
        this.description = description;
        this.imagePath = "plane_landing.gif";
        this.title = title;
        this.rarity = rarity;
        this.listOptions = new ArrayList<>();
    }

    public Request(String description, String title, double rarity, int nbOptions, String imagePath) {
        this.description = description;
        this.title = title;
        this.imagePath = imagePath;
        this.rarity = rarity;
        this.listOptions = new ArrayList<>();
    }

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
