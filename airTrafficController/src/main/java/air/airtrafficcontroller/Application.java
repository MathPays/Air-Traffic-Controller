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
    VBox runwaysDisplay, requestMen u, waitingLineDisplay;
    Text hour, deaths;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("content");

        //Initialization of the top bar (hour and deaths)
        HBox display = new HBox();
        display.setSpacing(5);
        display.setPadding(new Insets(10, 10, 10, 10));
        hour = new Text(10, 50, "00:00");
        hour.getStyleClass().add("hour");
        ImageView image = new ImageView(new Image("death.png"));
        image.setFitWidth(35);
        image.setPreserveRatio(true);
        deaths = new Text(10, 50, "0");
        deaths.getStyleClass().add("hour");
        display.getChildren().addAll(hour, image, deaths);
        root.setTop(display);

        //Initialization of the runway menu
        runwaysDisplay = new VBox();
        runwaysDisplay.setMaxWidth(100);
        runwaysDisplay.getStyleClass().add("runways");
        runwaysDisplay.setSpacing(5);
        runwaysDisplay.setPadding(new Insets(5, 5, 5, 5));
        for (int i = 0; i<8; i++) {
            HBox runway = new HBox();
            runway.getStyleClass().add("freeRunway");
            runway.setPadding(new Insets(0, 5, 0, 5));
            runway.setAlignment(Pos.CENTER_LEFT);
            runway.setSpacing(5);
            runwaysDisplay.getChildren().addAll(runway);
        }
        root.setCenter(runwaysDisplay);

        //Initialization of the request menu
        StackPane screen = new StackPane();
        screen.getStyleClass().add("screen");

        BorderPane menu = new BorderPane();
        menu.getStyleClass().add("menu");
        screen.getChildren().add(menu);
        screen.setMargin(menu, new Insets(27,50,160,50));
        root.setLeft(screen);

        requestMenu = new VBox(10);
        requestMenu.setAlignment(Pos.CENTER);
        requestMenu.getStyleClass().add("requestMenu");

        displayVictory();

        menu.setTop(requestMenu);

        //Initizalization of the waiting line
        VBox waitingLine = new VBox(10);
        waitingLine.setPadding(new Insets(10, 10, 10, 10));
        waitingLine.getStyleClass().add("waitingLine");
        HBox plane = new HBox();
        plane.getStyleClass().add("plane");
        Text name = new Text("Waiting to land...");
        name.getStyleClass().add("desc");
        plane.getChildren().addAll(name);
        waitingLine.getChildren().add(plane);
        root.setRight(waitingLine);

        Scene scene = new Scene(root, 1200, 650);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Air Traffic Controller");
        stage.setScene(scene);
        stage.getIcons().add(new Image("plane_landing.gif"));
        stage.setResizable(false);
        stage.show();

    }

    public void updateHour(int hour) {
        this.hour.setText(hour+":00");
    }

    public void updateDeaths(int deaths) {
        this.deaths.setText(String.valueOf(deaths));
    }

    public void displayRequest() {
        //Title and description
        requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image("protest.gif",100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text title = new Text(10, 50, "Funding Event");
        Text desc = new Text(10, 50, "A group of protestors are blocking the runway claiming that your airplanes are dropping chemtrails which has caused the following health issues: “makes their toothpaste taste like mint.”");
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(400);
        text.getChildren().addAll(title, desc);
        descMenu.getChildren().addAll(gif,text);
        requestMenu.getChildren().addAll(descMenu);
        //Choices
        ArrayList<String> options = new ArrayList<>();
        options.add("Empty random runway");
        options.add("Rescue team");
        options.add("Air refueling");
        for (String option: options) {
            Button button = new Button(option);
            button.getStyleClass().add("button");
            requestMenu.getChildren().add(button);
        }
    }

    public void displayCrash() {
        requestMenu.getChildren().clear();
        HBox descMenu = new HBox(10);
        descMenu.setAlignment(Pos.CENTER);
        ImageView gif = new ImageView(new Image("protest.gif",100,100,false, false));
        gif.getStyleClass().add("gif");
        VBox text = new VBox(10);
        Text title = new Text(10, 50, "Crash !");
        Text desc = new Text(10, 50, "The plane XXX has crashed, killing XXX people...");
        title.getStyleClass().add("title");
        desc.getStyleClass().add("desc");
        desc.setWrappingWidth(400);
        text.getChildren().addAll(title, desc);
        descMenu.getChildren().addAll(gif,text);
        requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button button = new Button("Ok");
        button.getStyleClass().add("button");
        requestMenu.getChildren().add(button);
    }

    public void displayVictory() {
        requestMenu.getChildren().clear();
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
        requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button replay = new Button("Replay");
        replay.getStyleClass().add("button");
        requestMenu.getChildren().add(replay);
    }

    public void displayGameOver() {
        requestMenu.getChildren().clear();
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
        requestMenu.getChildren().addAll(descMenu);
        //Choices
        Button replay = new Button("Replay");
        replay.getStyleClass().add("button");
        requestMenu.getChildren().add(replay);
    }

    public void updateRunways() {
        Runways runways = Runways.getInstance();

        runwaysDisplay.getChildren().clear();
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
            runwaysDisplay.getChildren().addAll(runwayDisplay);
        }
    }

    public void updateWaitingLine() {
        WaitingLine waitingLine = WaitingLine.getInstance();
        waitingLineDisplay.getChildren().clear();
        HBox planeDisplay = new HBox();
        planeDisplay.getStyleClass().add("plane");
        Text name = new Text("Waiting to land...");
        name.getStyleClass().add("desc");
        planeDisplay.getChildren().addAll(name);
        waitingLineDisplay.getChildren().add(planeDisplay);
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
            waitingLineDisplay.getChildren().add(planeDisplay);
        }
    }
}