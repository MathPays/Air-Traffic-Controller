package air.airtrafficcontroller;

public class Request {
    private String description, title;
    private double rarity;
    private Option[] listOptions;

    public Request(String description, String title, double rarity, int nbOptions){
        this.description = description;
        this.title = title;
        this.rarity = rarity;
        this.listOptions = new Option[nbOptions];
    }

    public void addOption(Option option){
        if(this.listOptions.length == 2){
            this.listOptions[2] = option;
        }
        else if(this.listOptions.length ==1){
            this.listOptions[1] = option;
        }
        else if(this.listOptions.length == 0){
            this.listOptions[0] = option;
        }
    }

    public void choose(int optionIndex){
        this.listOptions[optionIndex].performOption();
    }

    public String toString(){
        String res = this.title + " : " + this.description + "\n";
        for(int i = 0; i < this.listOptions.length; i++){
            res += "Option " + i + " : " + this.listOptions[i].toString() + "\n";
        }
        return res;
    }
}
