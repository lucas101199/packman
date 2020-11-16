package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class displayPacman {

    private final String scene;
    private final GraphicEngine windows;
    private final String[] imageLabels = {"PacMan_left","PacMan_right","PacMan_up","PacMan_down","PacMan_death"};


    public displayPacman(GraphicEngine windows, String nameScene) throws Exception {
        this.windows = windows;
        this.scene   = nameScene;

        windows.addImage(this.scene, imageLabels[0], "./src/sample/Images/PacMan/pacman_left.gif");
        windows.addImage(this.scene, imageLabels[1], "./src/sample/Images/PacMan/pacman_right.gif");
        windows.addImage(this.scene, imageLabels[2], "./src/sample/Images/PacMan/pacman_up.gif");
        windows.addImage(this.scene, imageLabels[3], "./src/sample/Images/PacMan/pacman_down.gif");
        windows.addImage(this.scene, imageLabels[4], "./src/sample/Images/PacMan/pacman_death.gif");
    }

    // Display Pac-Man
    public void displayPacManLeft (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, imageLabels[0], x, y);
        windows.displayImage    (this.scene, imageLabels[0]);
    }

    public void displayPacManRight (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, imageLabels[1], x, y);
        windows.displayImage    (this.scene, imageLabels[1]);
    }

    public void displayPacManUp (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, imageLabels[2], x, y);
        windows.displayImage    (this.scene, imageLabels[2]);
    }

    public void displayPacManDown (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, imageLabels[3], x, y);
        windows.displayImage    (this.scene, imageLabels[3]);
    }

    public void displayPacManDeath (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, imageLabels[4], x, y);
        windows.displayImage    (this.scene, imageLabels[4]);
    }

    // Hide Pac-Man
    public void hidePacManLeft () throws Exception {
        windows.hideImage(this.scene, imageLabels[0]);
    }

    public void hidePacManRight () throws Exception {
        windows.hideImage(this.scene, imageLabels[1]);
    }

    public void hidePacManUp() throws Exception {
        windows.hideImage(this.scene, imageLabels[2]);
    }

    public void hidePacManDown() throws Exception {
        windows.hideImage(this.scene, imageLabels[3]);
    }

    public void hidePacManDeath() throws Exception {
        windows.hideImage(this.scene, imageLabels[4]);
    }
}
