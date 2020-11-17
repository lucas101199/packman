package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.Arrays;

public class displayBlinky {
    private final String scene;
    private final GraphicEngine windows;
    private final String[] imageLabels = {"Blinky_left","Blinky_right","Blinky_up","Blinky_down","Blinky_edible",
                                          "Blinky_eyes_left","Blinky_eyes_right","Blinky_eyes_up","Blinky_eyes_down"};
    private String imageCurrent= "";

    public displayBlinky(GraphicEngine windows, String nameScene) throws Exception {
        this.windows = windows;
        this.scene   = nameScene;

        windows.addImage(this.scene, imageLabels[0], "./src/sample/Images/Ghosts/red/ghost_red_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/sample/Images/Ghosts/red/ghost_red_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/sample/Images/Ghosts/red/ghost_red_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/sample/Images/Ghosts/red/ghost_red_down.gif");

        windows.addImage(this.scene, imageLabels[4], "./src/sample/Images/Ghosts/edible/ghost_edible.gif");

        windows.addImage(this.scene, imageLabels[5], "./src/sample/Images/Ghosts/eyes/eyes_left.png");
        windows.addImage(this.scene, imageLabels[6], "./src/sample/Images/Ghosts/eyes/eyes_right.png");
        windows.addImage(this.scene, imageLabels[7], "./src/sample/Images/Ghosts/eyes/eyes_up.png");
        windows.addImage(this.scene, imageLabels[8], "./src/sample/Images/Ghosts/eyes/eyes_down.png");
    }

    // Display Blinky
    public void displayBlinky(String label, int x, int y) throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent) && Arrays.asList(imageLabels).contains(label)) {
            windows.hideImage(this.scene, imageCurrent);
            windows.setPositionImage(this.scene, label, x, y);
            windows.displayImage(this.scene, label);

            imageCurrent = label;

            return;
        }
        if (imageCurrent == "" && Arrays.asList(imageLabels).contains(label)) {
            windows.setPositionImage(this.scene, label, x, y);
            windows.displayImage(this.scene, label);

            imageCurrent = label;
        } else {
            System.out.println("Erreur dans le displayBlink");
        }
    }

    // Hide Blinky
    public void hideBlinky () throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            windows.hideImage(this.scene, imageCurrent);
    }
}
