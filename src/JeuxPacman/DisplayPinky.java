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

        windows.addImage(this.scene, imageLabels[0], "./src/Images/Ghosts/pink/ghost_pink_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/Images/Ghosts/pink/ghost_pink_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/Images/Ghosts/pink/ghost_pink_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/Images/Ghosts/pink/ghost_pink_down.gif");

        //windows.addImage(this.scene, imageLabels[4], "./src/sample/Images/Ghosts/edible/ghost_edible.gif");

        //windows.addImage(this.scene, imageLabels[5], "./src/sample/Images/Ghosts/eyes/eyes_left.png");
        //windows.addImage(this.scene, imageLabels[6], "./src/sample/Images/Ghosts/eyes/eyes_right.png");
        //windows.addImage(this.scene, imageLabels[7], "./src/sample/Images/Ghosts/eyes/eyes_up.png");
        //windows.addImage(this.scene, imageLabels[8], "./src/sample/Images/Ghosts/eyes/eyes_down.png");
    }

    // Display Pinky
    public void displayPinky(Direction dir, Position pos) throws Exception {
        String label;
        if (dir == null)
            return;
        switch (dir) {
            case EAST:
                label = imageLabels[1];
                break;
            case WEST:
                label = imageLabels[0];
                break;
            case NORTH:
                label = imageLabels[2];
                break;
            case SOUTH:
                label = imageLabels[3];
                break;
            default :
                label = "";
        }
        if (Arrays.asList(imageLabels).contains(label) || label.equals("")) {

            hidePinky();

            windows.setPositionImage(this.scene, label, pos.x, pos.y, true);
            windows.displayObject(this.scene, label);

            imageCurrent = label;
        }
        else {
            System.out.println("Mauvais label dans DisplayOrange");
        }
    }

    // Hide Pinky
    public void hidePinky () throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            windows.hideObject(this.scene, imageCurrent);
    }
}
