package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.Arrays;

public class DisplayInky {
    private final String scene;
    private final GraphicEngine windows;
    private final String[] imageLabels = {"inky_left","inky_right","inky_up","inky_down","inky_edible",
            "eyes_left","eyes_right","eyes_up","eyes_down"};

    private String imageCurrent= "";

    public DisplayInky(GraphicEngine windows, String nameScene) throws Exception {
        this.windows = windows;
        this.scene   = nameScene;

        windows.addImage(this.scene, imageLabels[0], "./src/sample/Images/Ghosts/blue/ghost_blue_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/sample/Images/Ghosts/blue/ghost_blue_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/sample/Images/Ghosts/blue/ghost_blue_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/sample/Images/Ghosts/blue/ghost_blue_down.gif");

        windows.addImage(this.scene, imageLabels[4], "./src/sample/Images/Ghosts/edible/ghost_edible.gif");

        windows.addImage(this.scene, imageLabels[5], "./src/sample/Images/Ghosts/eyes/eyes_left.png");
        windows.addImage(this.scene, imageLabels[6], "./src/sample/Images/Ghosts/eyes/eyes_right.png");
        windows.addImage(this.scene, imageLabels[7], "./src/sample/Images/Ghosts/eyes/eyes_up.png");
        windows.addImage(this.scene, imageLabels[8], "./src/sample/Images/Ghosts/eyes/eyes_down.png");
    }

    // Display Inky
    public void displayInky(String label, int x, int y) throws Exception {
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

    // Hide Inky
    public void hideClyde() throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            windows.hideImage(this.scene, imageCurrent);
    }
}
