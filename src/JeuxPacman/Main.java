package JeuxPacman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Game game = new Game();
        game.init();
        game.start();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    game.update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        //DisplayPacman pacman = new DisplayPacman(ge, nameScene);
        //pacman.displayPacMan("Pacman_left",new Position(272,454)); // Position Initial de PacMan

        //DisplayBlinky clyde = new DisplayBlinky(ge, nameScene);
        //clyde.displayBlinky("blinky_left",272,224);

    }


    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }
}
