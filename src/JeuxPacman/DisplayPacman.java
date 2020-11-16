package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.Arrays;

public class DisplayPacman {

    private final String scene;
    private final GraphicEngine windows;
    private final String[] imageLabels = {"Pacman_left","Pacman_right","Pacman_up","Pacman_down","Pacman_death"};

    private String imageCurrent= "";


    public DisplayPacman(GraphicEngine windows, String nameScene) throws Exception {
        this.windows = windows;
        this.scene   = nameScene;

        windows.addImage(this.scene, imageLabels[0], "./src/sample/Images/PacMan/pacman_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/sample/Images/PacMan/pacman_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/sample/Images/PacMan/pacman_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/sample/Images/PacMan/pacman_down.gif");
        windows.addImage(this.scene, imageLabels[4], "./src/sample/Images/PacMan/pacman_death.gif");
    }

    // Display Pac-Man
    public void displayPacMan(String label, int x, int y) throws Exception {
        if (Arrays.asList(imageLabels).contains(label) || label == "") {

            if (Arrays.asList(imageLabels).contains(imageCurrent)) {
                windows.hideImage(this.scene, imageCurrent);
            }

            windows.setPositionImage(this.scene, label, x, y);
            windows.displayImage(this.scene, label);

            imageCurrent = label;
        }
        else {
            System.out.println("Mauvais label dans DisplayPacman");
        }
    }

    // Hide Pac-Man
    public void hidePacMan () throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            windows.hideImage(this.scene, imageCurrent);
    }

}
