package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.Arrays;

public class DisplayPinky {

    private final String scene;
    private final GraphicEngine windows;
    private final String[] imageLabels = {"pinky_left","pinky_right","pinky_up","pinky_down","pinky_edible",
            "eyes_left","eyes_right","eyes_up","eyes_down"};

    private String imageCurrent= "";

    public DisplayPinky(GraphicEngine windows, String nameScene) throws Exception {
        this.windows = windows;
        this.scene   = nameScene;

        windows.addImage(this.scene, imageLabels[0], "./src/sample/Images/Ghosts/pink/ghost_pink_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/sample/Images/Ghosts/pink/ghost_pink_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/sample/Images/Ghosts/pink/ghost_pink_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/sample/Images/Ghosts/pink/ghost_pink_down.gif");

        windows.addImage(this.scene, imageLabels[4], "./src/sample/Images/Ghosts/edible/ghost_edible.gif");

        windows.addImage(this.scene, imageLabels[5], "./src/sample/Images/Ghosts/eyes/eyes_left.png");
        windows.addImage(this.scene, imageLabels[6], "./src/sample/Images/Ghosts/eyes/eyes_right.png");
        windows.addImage(this.scene, imageLabels[7], "./src/sample/Images/Ghosts/eyes/eyes_up.png");
        windows.addImage(this.scene, imageLabels[8], "./src/sample/Images/Ghosts/eyes/eyes_down.png");
    }

    // Display Pinky
    public void displayPinky(String label, int x, int y) throws Exception {
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

    // Hide Pinky
    public void hidePinky () throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            windows.hideImage(this.scene, imageCurrent);
    }
}
