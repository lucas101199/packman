package GraphicEngine;

import javafx.application.Application;
import javafx.stage.Stage;

/*TODO fix the superposition of 2 scene cause we have a bug when
   we create the first then display it then create a second one
   then display it then if we want to display the first one
   it doesn't work and display just a white screen instead
*/


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GraphicEngine graphicEngine = new GraphicEngine("Pacman");
        graphicEngine.addScene("menu");
        graphicEngine.setWindow(400,400);
        graphicEngine.displayScene("menu");
        graphicEngine.addImage("menu", "pacman", "./src/Image/pacmanRight.gif");
        graphicEngine.displayImage("menu", "pacman");
        graphicEngine.setPositionImage("menu", "pacman", 100, 100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
