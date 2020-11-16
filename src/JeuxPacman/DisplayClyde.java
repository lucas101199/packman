package sample;

import sample.GraphicEngine.GraphicEngine;

import java.util.Arrays;

public class DisplayClyde {
    private final String scene;
    private final GraphicEngine windows;
    private final String[] imageLabels = {"clyde_left","clyde_right","clyde_up","clyde_down","clyde_edible",
            "eyes_left","eyes_right","eyes_up","eyes_down"};

    private String imageCurrent= "";

    public DisplayClyde(GraphicEngine windows, String nameScene) throws Exception {
        this.windows = windows;
        this.scene   = nameScene;

        windows.addImage(this.scene, imageLabels[0], "./src/sample/Images/Ghosts/orange/ghost_orange_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/sample/Images/Ghosts/orange/ghost_orange_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/sample/Images/Ghosts/orange/ghost_orange_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/sample/Images/Ghosts/orange/ghost_orange_down.gif");

        windows.addImage(this.scene, imageLabels[4], "./src/sample/Images/Ghosts/edible/ghost_edible.gif");

        windows.addImage(this.scene, imageLabels[5], "./src/sample/Images/Ghosts/eyes/eyes_left.png");
        windows.addImage(this.scene, imageLabels[6], "./src/sample/Images/Ghosts/eyes/eyes_right.png");
        windows.addImage(this.scene, imageLabels[7], "./src/sample/Images/Ghosts/eyes/eyes_up.png");
        windows.addImage(this.scene, imageLabels[8], "./src/sample/Images/Ghosts/eyes/eyes_down.png");
    }

    // Display Clyde
    public void displayClyde(String label, int x, int y) throws Exception {
        if (Arrays.asList(imageLabels).contains(label) || label == "") {

            if (Arrays.asList(imageLabels).contains(imageCurrent)) {
                windows.hideImage(this.scene, imageCurrent);
            }

            windows.setPositionImage(this.scene, label, x, y);
            windows.displayImage(this.scene, label);

            imageCurrent = label;
        }
        else {
            System.out.println("Mauvais label dans DisplayOrange");
        }
    }

    // Hide Clyde
    public void hideClyde() throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            windows.hideImage(this.scene, imageCurrent);
    }
}
