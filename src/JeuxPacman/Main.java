package JeuxPacman;

import GraphicEngine.GraphicLaunch;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Application.launch(GraphicLaunch.class,Game.class.getName(),"Pacman");
    }

}
