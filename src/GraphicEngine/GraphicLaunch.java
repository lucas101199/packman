package GraphicEngine;

import Interfaces.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GraphicLaunch extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        String gameClassName = getParameters().getRaw().get(0);
        String gameName = getParameters().getRaw().get(1);

        GraphicEngine graphic = new GraphicEngine(gameName);

        Class<? extends Game> gameClass =
                Class.forName(gameClassName).asSubclass(Game.class);

        Game game = gameClass.getConstructor().newInstance();
        game.set_graphic(graphic);

        game.init();
        game.start();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),
                actionEvent -> game.update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

}
