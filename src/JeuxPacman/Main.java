package JeuxPacman;

import GraphicEngine.GraphicEngine;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        String nameScene = "scenePacMan";
        String nameMap   = "map";

        // Creation de la fenetre
        GraphicEngine test = new GraphicEngine("PacMan");

        // Creation de la scene
        test.addScene(nameScene);
        test.setSizeScene(nameScene, 544,600);

        // Ajout de l'image de la carte
        test.addImage        (nameScene, nameMap, "./src/Images/Map/map.png");
        test.setPositionImage(nameScene, nameMap, 0,0);
        test.displayImage    (nameScene, nameMap);

        displayPacman pacman = new displayPacman(test, nameScene);

        pacman.displayPacManRight(50,10);

        // Affichage de la scene
        test.displayScene(nameScene);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
