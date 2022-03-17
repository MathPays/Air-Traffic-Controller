package air.airtrafficcontroller;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        Application.launch(Application.class, "");
    }

    public static void load() throws IOException, ClassNotFoundException {
        FileInputStream inStream = new FileInputStream("Game.ser");
        ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
        while( inStream.available() > 0) {
            Game.instance = (Game) objectInputFile.readObject();
        }
        inStream = new FileInputStream("Runways.ser");
        objectInputFile = new ObjectInputStream(inStream);
        while( inStream.available() > 0) {
            Runways.instance = (Runways) objectInputFile.readObject();
        }
        inStream = new FileInputStream("WaitingLine.ser");
        objectInputFile = new ObjectInputStream(inStream);
        while( inStream.available() > 0) {
            WaitingLine.instance = (WaitingLine) objectInputFile.readObject();
        }
        Application.updateHour();
        Application.updateDeaths();
        Application.updateWaitingLine();
        Application.updateRunways();
        inStream.close();
        objectInputFile.close();
    }

    public static void save() throws IOException {
        FileOutputStream outStream = new FileOutputStream("Game.ser");
        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
        objectOutputFile.writeObject(Game.instance);
        outStream = new FileOutputStream("Runways.ser");
        objectOutputFile = new ObjectOutputStream(outStream);
        objectOutputFile.writeObject(Runways.instance);
        outStream = new FileOutputStream("WaitingLine.ser");
        objectOutputFile = new ObjectOutputStream(outStream);
        objectOutputFile.writeObject(WaitingLine.instance);
    }
}
