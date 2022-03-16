package air.airtrafficcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Application extends javafx.application.Application {
    public static Application instance = new Application();
    public VBox runwaysDisplay, requestMenu, waitingLineDisplay;
    public Text hour, deaths;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("content");

        //Initialization of the top bar (hour and deaths)
        HBox display = new HBox();
        display.setSpacing(5);
        display.setPadding(new Insets(10, 10, 10, 10));
        instance.hour = new Text(10, 50, "00:00");
        instance.hour.getStyleClass().add("hour");
        ImageView image = new ImageView(new Image("death.png"));
        image.setFitWidth(35);
        image.setPreserveRatio(true);
        instance.deaths = new Text(10, 50, "0");
        instance.deaths.getStyleClass().add("hour");
        display.getChildren().addAll(instance.hour, image, instance.deaths);
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
        screen.setMargin(menu, new Insets(27,50,160,50));
        root.setLeft(screen);

        instance.requestMenu = new VBox(10);
        instance.requestMenu.setAlignment(Pos.CENTER);
        instance.requestMenu.getStyleClass().add("requestMenu");

        menu.setTop(instance.requestMenu);

        //Initizalization of the waiting line
        instance.waitingLineDisplay = new VBox(10);
        instance.waitingLineDisplay.setPadding(new Insets(10, 10, 10, 10));
        instance.waitingLineDisplay.getStyleClass().add("waitingLine");
        HBox plane = new HBox();
        plane.getStyleClass().add("plane");
        Text name = new Text("Waiting to land...");
        name.getStyleClass().add("desc");
        plane.getChildren().addAll(name);
        instance.waitingLineDisplay.getChildren().add(plane);
        root.setRight(instance.waitingLineDisplay);

        Scene scene = new Scene(root, 1200, 650);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Air Traffic Controller");
        stage.setScene(scene);
        stage.getIcons().add(new Image("plane_landing.gif"));
        stage.setResizable(false);
        stage.show();

    }

    /**
     * update the hour on the UI
     * @param hour hour to display
     */
    public static void updateHour(int hour) {
        instance.hour.setText(hour+":00");
    }

    /**
     * update the count of deaths on the UI
     * @param deaths number of deaths
     */
    public static void updateDeaths(int deaths) {
        instance.deaths.setText(String.valueOf(deaths));
    }

    /**
     * display the request on the monitor of the UI
     * @param request request to display
     */
    public static void displayRequest(Request request) {
        //Title and description
        instance.requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image(request.getImagePath(),100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text title = new Text(10, 50, request.getTitle());
        Text desc = new Text(10, 50, request.getDescription());
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(400);
        text.getChildren().addAll(title, desc);
        descMenu.getChildren().addAll(gif,text);
        instance.requestMenu.getChildren().addAll(descMenu);
        ArrayList<Option> options = request.getListOptions();
        for (Option option: options) {
            Button button = new Button(option.getDesc());
            if (option.checkRequirement()) {
                button.getStyleClass().add("button");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        option.performOption();
                    }
                });
            } else {
                button.getStyleClass().add("buttonImpossible");
            }
            instance.requestMenu.getChildren().add(button);
        }
    }


    //SUITE A FAIRE
    public static void displayCrash(ArrayList<Plane> planesToCrash) {
        if (planesToCrash.isEmpty()) {
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
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayCrash(planesToCrash);
            }
        });
        instance.requestMenu.getChildren().add(button);
    }

    /**
     * display the victory message on the monitor
     */
    public static void displayVictory() {
        instance.requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image("protest.gif",100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text title = new Text(10, 50, "Victory");
        Text desc = new Text(10, 50, "Good job ! You have managed to hold 24 hours as an air controller.");
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(400);
        text.getChildren().addAll(title, desc);
        descMenu.getChildren().addAll(gif,text);
        instance.requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button replay = new Button("Replay");
        replay.getStyleClass().add("button");
        instance.requestMenu.getChildren().add(replay);
    }

    /**
     * display the game over on the monitor
     */
    public static void displayGameOver() {
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
        instance.requestMenu.getChildren().add(replay);
    }

    /**
     * display the end of the hour
     */
    public static void displayPassHour() {
        instance.requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image("plane_landing.gif",100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text title = new Text(10, 50, "No more requests");
        Text desc = new Text(10, 50, "You completed all the requests of this hour.");
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(400);
        text.getChildren().addAll(title, desc);
        descMenu.getChildren().addAll(gif,text);
        instance.requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button replay = new Button("Ok");
        replay.getStyleClass().add("button");
        instance.requestMenu.getChildren().add(replay);
    }

    /**
     * update the runways on the center
     */
    public static void updateRunways() {
        System.out.println("TEST");
        Runways runways = Runways.getInstance();

        instance.runwaysDisplay.getChildren().clear();
        for (Runway runway : runways.getRunways()) {
            HBox runwayDisplay = new HBox();
            runwayDisplay.setPadding(new Insets(0, 5, 0, 5));
            runwayDisplay.setAlignment(Pos.CENTER_LEFT);
            Text planeHour = new Text(10, 50, String.valueOf(runway.getRunwayTime()));

            switch (runway.getState()) {
                case FREE:
                    runwayDisplay.getStyleClass().add("freeRunway");
                    break;
                case OCCUPIED:
                    planeHour = new Text(10, 50, String.valueOf(runway.getRunwayTime()));
                    planeHour.getStyleClass().add("runwayHour");
                    runwayDisplay.getStyleClass().add("takenRunway");
                    runwayDisplay.getChildren().addAll(planeHour);
                    break;
                case FROZEN:
                    planeHour = new Text(10, 50, String.valueOf(runway.getRunwayTime()));
                    planeHour.getStyleClass().add("runwayHour");
                    runwayDisplay.getStyleClass().add("takenRunway");
                    runwayDisplay.getChildren().addAll(planeHour);
                    break;
                case RIOT:
                    planeHour = new Text(10, 50, String.valueOf(runway.getRunwayTime()));
                    planeHour.getStyleClass().add("runwayHour");
                    runwayDisplay.getStyleClass().add("takenRunway");
                    runwayDisplay.getChildren().addAll(planeHour);
                    break;
            }
            runwayDisplay.setSpacing(5);
            instance.runwaysDisplay.getChildren().addAll(runwayDisplay);
        }
    }

    /**
     * update the waiting line on the left
     */
    public static void updateWaitingLine() {
        System.out.println("TEST2");
        WaitingLine waitingLine = WaitingLine.getInstance();
        instance.waitingLineDisplay.getChildren().clear();
        HBox planeDisplay = new HBox();
        planeDisplay.getStyleClass().add("plane");
        Text name = new Text("Waiting to land...");
        name.getStyleClass().add("desc");
        planeDisplay.getChildren().addAll(name);
        instance.waitingLineDisplay.getChildren().add(planeDisplay);
        boolean availableRunway = Runways.checkIfAvailableRunway();
        for (Plane plane: waitingLine.getWaitingLine()) {
            planeDisplay = new HBox();
            planeDisplay.setAlignment(Pos.CENTER);
            planeDisplay.getStyleClass().add("plane");
            planeDisplay.setSpacing(40);
            Text planeName = new Text(String.valueOf(plane.getId()));
            planeName.getStyleClass().add("desc");
            Text time = new Text(plane.getHoursFuel()+" left");
            time.getStyleClass().add("desc");
            Button button = new Button("Land");
            if (availableRunway) {
                button.getStyleClass().add("button");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        WaitingLine.landPlane(plane);
                    }
                });
            } else {
                button.getStyleClass().add("blockedButton");
            }
            planeDisplay.getChildren().addAll(planeName, time, button);
            instance.waitingLineDisplay.getChildren().add(planeDisplay);
        }
    }
}