package air.airtrafficcontroller;

public class BlockRunwayOption extends Option{

    private int hour;
    private Runway.State state;
    //ajouter removed runway, runway aléatoire si pas libre incrémente
    //block 2 runways
    public BlockRunwayOption(String desc, int h, Runway.State s)
    {
        super(desc);
        this.hour = h;
        this.state = s;
    }

    @Override
    public void performOption() {

        if(this.state.equals(Runway.State.REMOVED)) // set runway time to one runway to 24 hours
        {
            int index = (int) (Math.random() * 8);
            for(int i = index; i < 7; i++) {
                if(!(Runways.getRunways()[i].getState().equals(Runway.State.FREE)))
                {
                    if(i == 7) //We go back to the beggining of the loop until we find a free runway
                        i = -1; //will be incremented at 0 automatically
                    continue;
                }
                else
                    Runways.getRunways()[i].setRunwayTime(24); // A game is 24 hours, so the runway will be out
                break; //we want only one runway removed
            }
        }

        else if(this.state.equals(Runway.State.PROTEST)) //add x hours to the runway time of 1 runway
        {
            for(Runway r : Runways.getRunways())
            {
                if(r.getState().equals(Runway.State.FREE))
                {
                    r.setState(Runway.State.PROTEST);
                    r.setRunwayTime(hour);
                    break;
                }

            }
        }

        else if(this.state.equals(Runway.State.RIOT)) //add x hours to the runway time of 2 runways
        {
            int compt = 0;
            for(Runway r : Runways.getRunways())
            {
                if(compt == 2)
                    break;

                if(r.getState().equals(Runway.State.FREE))
                {
                    r.setState(Runway.State.RIOT);
                    r.setRunwayTime(hour);
                    compt += 1;
                }

            }
        }

    }

    @Override
    public boolean checkRequirement() {
        if(this.state.equals(Runway.State.RIOT))
            return Runways.checkIf2AvailablesRunway();
        return Runways.checkIfAvailableRunway();
    }
}