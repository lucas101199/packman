package Graphique;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PacManView extends Group {

    private final ImageView PacManUp;
    private final ImageView PacManDown;
    private final ImageView PacManRight;
    private final ImageView PacManLeft;

    public PacManView() throws FileNotFoundException {
        String curDir = System.getProperty("user.dir");
        
        PacManUp = new ImageView(new Image(new FileInputStream(curDir + "/src/Image/pacmanUp.gif")));
        PacManDown = new ImageView(new Image(new FileInputStream(curDir + "/src/Image/pacmanDown.gif")));
        PacManRight = new ImageView(new Image(new FileInputStream(curDir + "/src/Image/pacmanRight.gif")));
        PacManLeft = new ImageView(new Image(new FileInputStream(curDir + "/src/Image/pacmanLeft.gif")));
    }

    public ImageView getPacManUp() {
        return PacManUp;
    }

    public ImageView getPacManDown() {
        return PacManDown;
    }

    public ImageView getPacManRight() {
        return PacManRight;
    }

    public ImageView getPacManLeft() {
        return PacManLeft;
    }
}
