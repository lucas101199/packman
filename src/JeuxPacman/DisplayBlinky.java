package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.Arrays;

public class DisplayBlinky {
    private final String scene;
    private final GraphicEngine windows;
    private final String[] imageLabels = {"blinky_left","blinky_right","blinky_up","blinky_down","blinky_edible",
            "eyes_left","eyes_right","eyes_up","eyes_down"};
    private String imageCurrent= "";

    public DisplayBlinky(GraphicEngine windows, String nameScene) throws Exception {
        this.windows = windows;
        this.scene   = nameScene;

        windows.addImage(this.scene, imageLabels[0], "./src/Images/Ghosts/red/ghost_red_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/Images/Ghosts/red/ghost_red_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/Images/Ghosts/red/ghost_red_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/Images/Ghosts/red/ghost_red_down.gif");

        //windows.addImage(this.scene, imageLabels[4], "./src/Images/Ghosts/edible/ghost_edible.gif");

        //windows.addImage(this.scene, imageLabels[5], "./src/Images/Ghosts/eyes/eyes_left.png");
        //windows.addImage(this.scene, imageLabels[6], "./src/Images/Ghosts/eyes/eyes_right.png");
        //windows.addImage(this.scene, imageLabels[7], "./src/Images/Ghosts/eyes/eyes_up.png");
        //windows.addImage(this.scene, imageLabels[8], "./src/Images/Ghosts/eyes/eyes_down.png");
    }

    // Display Blinky
    public void displayBlinky(String label, int x, int y) throws Exception {
        if (Arrays.asList(imageLabels).contains(label) || label.equals("")) {

            hideBlinky();

            windows.setPositionImage(this.scene, label, x, y);
            windows.displayImage(this.scene, label);

            imageCurrent = label;
        }
        else {
            System.out.println("Mauvais label dans DisplayOrange");
        }
    }

    // Hide Blinky
    public void hideBlinky () throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            windows.hideImage(this.scene, imageCurrent);
    }
}
