package air.airtrafficcontroller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Application extends javafx.application.Application {
    public static Application instance = new Application();
    public VBox runwaysDisplay, requestMenu, waitingLineDisplay;
    public Text hour, deaths;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("content");

        //Initialization of the top bar (hour and deaths)
        HBox display = new HBox();
        display.setSpacing(5);
        display.setPadding(new Insets(10, 10, 10, 10));
        Button button = new Button("Save");
        button.getStyleClass().add("button");
        button.setOnAction(event -> {
            try {
                Main.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        instance.hour = new Text(10, 50, "00:00");
        instance.hour.getStyleClass().add("hour");
        ImageView image = new ImageView(new Image("death.png"));
        image.setFitWidth(35);
        image.setPreserveRatio(true);
        instance.deaths = new Text(10, 50, "0");
        instance.deaths.getStyleClass().add("hour");
        display.getChildren().addAll(button, instance.hour, image, instance.deaths);
        root.setTop(display);

        //Initialization of the runway menu
        instance.runwaysDisplay = new VBox();
        instance.runwaysDisplay.setMaxWidth(100);
        instance.runwaysDisplay.getStyleClass().add("runways");
        instance.runwaysDisplay.setSpacing(5);
        instance.runwaysDisplay.setPadding(new Insets(5, 5, 5, 5));
        for (int i = 0; i<8; i++) {
            HBox runway = new HBox();
            runway.getStyleClass().add("freeRunway");
            runway.setPadding(new Insets(0, 5, 0, 5));
            runway.setAlignment(Pos.CENTER_LEFT);
            runway.setSpacing(5);
            instance.runwaysDisplay.getChildren().addAll(runway);
        }
        root.setCenter(instance.runwaysDisplay);

        //Initialization of the request menu
        StackPane screen = new StackPane();
        screen.getStyleClass().add("screen");

        BorderPane menu = new BorderPane();
        menu.getStyleClass().add("menu");
        screen.getChildren().add(menu);
        StackPane.setMargin(menu, new Insets(27,50,160,50));
        root.setLeft(screen);

        instance.requestMenu = new VBox(10);
        instance.requestMenu.setAlignment(Pos.CENTER);
        instance.requestMenu.getStyleClass().add("requestMenu");

        menu.setTop(instance.requestMenu);

        displayStart();

        //Initialization of the waiting line
        instance.waitingLineDisplay = new VBox(10);
        instance.waitingLineDisplay.setPadding(new Insets(10, 10, 10, 10));
        instance.waitingLineDisplay.getStyleClass().add("waitingLine");
        HBox plane = new HBox();
        plane.getStyleClass().add("plane");
        Text name = new Text("Waiting to land...");
        name.getStyleClass().add("desc");
        Font font = Font.loadFont("NewHiScore", 12);
        name.setFont(font);
        plane.getChildren().addAll(name);
        instance.waitingLineDisplay.getChildren().add(plane);
        root.setRight(instance.waitingLineDisplay);

        Scene scene = new Scene(root, 1200, 650);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Air Traffic Controller");
        stage.setScene(scene);
        stage.getIcons().add(new Image("icon.png"));
        stage.setResizable(false);
        stage.show();

    }

    /**
     * Updates the hour on the UI
     */
    public static void updateHour() {
        instance.hour.setText(Game.getHour()+":00");
    }

    /**
     * Updates the count of deaths on the UI
     */
    public static void updateDeaths() {
        instance.deaths.setText(String.valueOf(Game.getPeopleKilled()));
    }

    /**
     * Displays the request on the monitor of the UI
     * @param request request to display
     */
    public static void displayRequest(Request request,String effect) {
        if (request == null) {
            displayPassHour(effect);
            return;
        }
        instance.requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image(request.getImagePath(),100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text effects = new Text(10, 50, effect);
        Text title = new Text(10, 50, request.getTitle());
        Text desc = new Text(10, 50, request.getDescription());
        effects.getStyleClass().add("effect");
        effects.setWrappingWidth(350);
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(350);
        text.getChildren().addAll(effects, title, desc);
        descMenu.getChildren().addAll(gif,text);
        instance.requestMenu.getChildren().addAll(descMenu);
        ArrayList<Option> options = request.getListOptions();
        for (Option option: options) {
            Button button = new Button(option.getDesc());
            if (option.checkRequirement()) {
                button.getStyleClass().add("button");
                button.setOnAction(event -> {
                    String effectDesc = option.performOption();
                    Game.getCurrentTurn().removeRequest();
                    displayRequest(Game.getCurrentTurn().getNextRequest(),effectDesc);
                });
            } else {
                button.getStyleClass().add("buttonImpossible");
            }
            instance.requestMenu.getChildren().add(button);
        }
    }

    /**
     * Displays the infos about a crash on the monitor of the UI
     * @param planesToCrash list of planes to crash
     */
    public static void displayCrash(ArrayList<Plane> planesToCrash) {
        if (planesToCrash.isEmpty()) {
            Application.updateWaitingLine();
            Application.updateRunways();
            Application.updateHour();
            Application.updateDeaths();
            if (Game.checkDefeat() || Game.checkVictory()) {
                return;
            }
            displayRequest(Game.getCurrentTurn().getNextRequest(),null);
            return;
        }
        Plane plane = planesToCrash.get(0);
        planesToCrash.remove(0);
        instance.requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image("protest.gif",100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text title = new Text(10, 50, "Crash !");
        Text desc = new Text(10, 50, "The plane "+plane.getId()+" has crashed, killing "+plane.getPassengers()+" people...");
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(400);
        text.getChildren().addAll(title, desc);
        descMenu.getChildren().addAll(gif,text);
        instance.requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button button = new Button("Ok");
        button.getStyleClass().add("button");
        button.setOnAction(event -> {
            Game.killMorePeople(plane.getPassengers());
            WaitingLine.removePlane(plane);
            displayCrash(planesToCrash);
        });
        instance.requestMenu.getChildren().add(button);
    }

    /**
     * Displays the victory message on the monitor
     */
    public static void displayVictory() {
        updateWaitingLine();
        updateRunways();
        updateHour();
        updateDeaths();
        instance.requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image("trophy.png",100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text title = new Text(10, 50, "Victory");
        Text desc = new Text(10, 50, "Good job ! You have managed to hold 24 hours as an air controller.");
        desc.setWrappingWidth(300);
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        text.getChildren().addAll(title, desc);
        descMenu.getChildren().addAll(gif,text);
        instance.requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button replay = new Button("Replay");
        replay.getStyleClass().add("button");
        replay.setOnAction(event -> {
            Game.replay();
            displayRequest(Game.getCurrentTurn().getNextRequest(),null);
        });
        instance.requestMenu.getChildren().add(replay);

    }

    /**
     * Displays the game over on the monitor
     */
    public static void displayGameOver() {
        updateWaitingLine();
        updateRunways();
        updateHour();
        updateDeaths();
        instance.requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image("protest.gif",100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text title = new Text(10, 50, "Game Over");
        Text desc = new Text(10, 50, "Too bad, you killed too many people. :(");
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(400);
        text.getChildren().addAll(title, desc);
        descMenu.getChildren().addAll(gif,text);
        instance.requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button replay = new Button("Replay");
        replay.getStyleClass().add("button");
        replay.setOnAction(event -> {
            Game.replay();
            displayRequest(Game.getCurrentTurn().getNextRequest(),null);
        });
        instance.requestMenu.getChildren().add(replay);
    }

    public static void displayStart() {
        instance.requestMenu.getChildren().clear();
        VBox text = new VBox(10);
        Text effects = new Text(10, 50, "Mathilde PAYSANT\nClovis THOUVENOT OUDART\nPaul CAVROIS\nHugo ANCEAUX");
        Text title = new Text(10, 50, "Air Traffic Controller");
        Text desc = new Text(10, 50, "Welcome to our Air Traffic Controller simulator.");
        effects.getStyleClass().add("effect");
        effects.setWrappingWidth(350);
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(400);
        title.setTextAlignment(TextAlignment.CENTER);
        effects.setTextAlignment(TextAlignment.CENTER);
        desc.setTextAlignment(TextAlignment.CENTER);
        instance.requestMenu.getChildren().addAll(effects,title,desc);
        instance.requestMenu.getChildren().addAll(text);
        //Choices
        Button start = new Button("Start");
        start.getStyleClass().add("button");
        start.setOnAction(event -> {
            displayRequest(Game.getCurrentTurn().getNextRequest(),null);
        });
        Button load = new Button("Load");
        load.getStyleClass().add("button");
        load.setOnAction(event -> {
            try {
                Main.load();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            displayRequest(Game.getCurrentTurn().getNextRequest(),null);
        });
        instance.requestMenu.getChildren().addAll(start, load);
    }

    /**
     * Displays the end of the hour
     */
    public static void displayPassHour(String effect) {
        instance.requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image("plane_landing.gif",100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text effects = new Text(10, 50, effect);
        Text title = new Text(10, 50, "No more requests");
        Text desc = new Text(10, 50, "You completed all the requests of this hour.");
        effects.getStyleClass().add("effect");
        effects.setWrappingWidth(350);
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(350);
        text.getChildren().addAll(effects,title, desc);
        descMenu.getChildren().addAll(gif,text);
        instance.requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button button = new Button("Move to next hour");
        button.getStyleClass().add("button");
        button.setOnAction(event -> {
            if (Game.passHour()) {
                displayRequest(Game.getCurrentTurn().getNextRequest(),null);
            }
        });
        instance.requestMenu.getChildren().add(button);
    }

    /**
     * Updates the runways on the center
     */
    public static void updateRunways() {
        instance.runwaysDisplay.getChildren().clear();
        for (Runway runway : Runways.getRunways()) {
            HBox runwayDisplay = new HBox();
            runwayDisplay.setPadding(new Insets(0, 5, 0, 5));
            runwayDisplay.setAlignment(Pos.CENTER_LEFT);
            Text planeHour = new Text(10, 50, runway.getRunwayTime()+"H");

            switch (runway.getState()) {
                case FREE:
                    runwayDisplay.getStyleClass().add("freeRunway");
                    break;
                case OCCUPIED:
                    planeHour.getStyleClass().add("runwayHour");
                    runwayDisplay.getStyleClass().add("occupiedRunway");
                    runwayDisplay.getChildren().addAll(planeHour);
                    break;
                case FROZEN:
                    planeHour.getStyleClass().add("runwayHour");
                    runwayDisplay.getStyleClass().add("frozenRunway");
                    runwayDisplay.getChildren().addAll(planeHour);
                    break;
                case RIOT:
                    planeHour.getStyleClass().add("runwayHour");
                    runwayDisplay.getStyleClass().add("riotRunway");
                    runwayDisplay.getChildren().addAll(planeHour);
                    break;
                case PROTEST:
                    planeHour.getStyleClass().add("runwayHour");
                    runwayDisplay.getStyleClass().add("protestRunway");
                    runwayDisplay.getChildren().addAll(planeHour);
                    break;
                case REMOVED:
                    planeHour.setText("");
                    runwayDisplay.getStyleClass().add("removedRunway");
                    runwayDisplay.getChildren().addAll(planeHour);
                    break;
            }
            runwayDisplay.setSpacing(5);
            instance.runwaysDisplay.getChildren().addAll(runwayDisplay);
        }
    }

    /**
     * Updates the waiting line on the left
     */
    public static void updateWaitingLine() {
        instance.waitingLineDisplay.getChildren().clear();
        HBox planeDisplay = new HBox();
        planeDisplay.getStyleClass().add("plane");
        Text name = new Text("Waiting to land...");
        name.getStyleClass().add("desc");
        planeDisplay.getChildren().addAll(name);
        instance.waitingLineDisplay.getChildren().add(planeDisplay);
        boolean availableRunway = Runways.checkIfAvailableRunway();
        for (Plane plane: WaitingLine.getWaitingLine()) {
            planeDisplay = new HBox();
            planeDisplay.setAlignment(Pos.CENTER);
            planeDisplay.getStyleClass().add("plane");
            planeDisplay.setSpacing(10);
            Text planeName = new Text("#"+plane.getId());
            planeName.getStyleClass().add("desc");
            Text time = new Text(plane.getHoursFuel()+"H left");
            time.getStyleClass().add("desc");
            Text need = new Text(plane.getRunwayTime()+"H needed");
            need.getStyleClass().add("desc");
            Button button = new Button("Land");
            if (availableRunway) {
                button.getStyleClass().add("button");
                button.setOnAction(event -> {
                    WaitingLine.landPlane(plane);
                    displayRequest(Game.getCurrentTurn().getNextRequest(),null);
                });
            } else {
                button.getStyleClass().add("buttonImpossible");
            }
            planeDisplay.getChildren().addAll(planeName, time, need, button);
            instance.waitingLineDisplay.getChildren().add(planeDisplay);
        }
    }
}