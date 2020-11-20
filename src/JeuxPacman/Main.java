package JeuxPacman;

import GraphicEngine.GraphicEngine;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        GraphicEngine.game = game;

        Application.launch(GraphicEngine.class);
    }

}
