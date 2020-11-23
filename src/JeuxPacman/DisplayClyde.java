package JeuxPacman;

import GraphicEngine.GraphicEngine;

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

        windows.addImage(this.scene, imageLabels[0], "./src/Images/Ghosts/orange/ghost_orange_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/Images/Ghosts/orange/ghost_orange_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/Images/Ghosts/orange/ghost_orange_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/Images/Ghosts/orange/ghost_orange_down.gif");

        //windows.addImage(this.scene, imageLabels[4], "./srcImages/Ghosts/edible/ghost_edible.gif");

        //windows.addImage(this.scene, imageLabels[5], "./src/Images/Ghosts/eyes/eyes_left.png");
        //windows.addImage(this.scene, imageLabels[6], "./src/Images/Ghosts/eyes/eyes_right.png");
        //windows.addImage(this.scene, imageLabels[7], "./src/Images/Ghosts/eyes/eyes_up.png");
        //windows.addImage(this.scene, imageLabels[8], "./src/Images/Ghosts/eyes/eyes_down.png");
    }

    // Display Clyde
    public void displayClyde(Direction dir, Position pos) throws Exception {
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

            hideClyde();

            windows.setPositionImage(this.scene, label, pos.x, pos.y);
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
