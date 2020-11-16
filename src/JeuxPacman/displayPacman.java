package sample;

import sample.GraphicEngine.GraphicEngine;

public class displayPacman {

    private final String curDir = System.getProperty("user.dir");
    private final String scene;
    private final GraphicEngine windows;

    public displayPacman(GraphicEngine windows, String nameScene) throws Exception {
        this.windows = windows;
        this.scene = nameScene;

        windows.addImage(this.scene, "pacManLeft", curDir + "/src/sample/Images/PacMan/pacman_left.gif");
        windows.addImage(this.scene, "pacManRight", curDir + "/src/sample/Images/PacMan/pacman_right.gif");
        windows.addImage(this.scene, "pacManUp", curDir + "/src/sample/Images/PacMan/pacman_up.gif");
        windows.addImage(this.scene, "pacManDown", curDir + "/src/sample/Images/PacMan/pacman_down.gif");
        windows.addImage(this.scene, "pacManDeath", curDir + "/src/sample/Images/PacMan/pacman_death.gif");
    }

    public void displayPacManRight (int x, int y) throws Exception {
        windows.setPositionImage(this.scene,"pacManRight",x,y);
        windows.displayImage(this.scene,"pacManRight");
    }

    public void displayPacManLeft (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, "pacManLeft", x, y);
        windows.displayImage(this.scene, "pacManLeft");
    }

    public void displayPacManUp (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, "pacManUp", x, y);
        windows.displayImage(this.scene, "pacManUp");
    }

    public void displayPacManDown (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, "pacManDown", x, y);
        windows.displayImage(this.scene, "pacManDown");
    }

    public void displayPacManDeath (int x, int y) throws Exception {
        windows.setPositionImage(this.scene, "pacManDeath", x, y);
        windows.displayImage(this.scene, "pacManDeath");
    }
}
