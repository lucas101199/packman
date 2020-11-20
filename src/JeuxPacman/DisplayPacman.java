package JeuxPacman;

import GraphicEngine.GraphicEngine;
import Outils.Position;

import java.util.Arrays;

public class DisplayPacman {

    private final String _sceneLabel;
    private final GraphicEngine _graphic;
    private final String[] imageLabels = {"Pacman_up","Pacman_right","Pacman_down","Pacman_left","Pacman_death"};

    private String imageCurrent= "";


    public DisplayPacman(GraphicEngine graphic, String sceneLabel) throws Exception {
        _graphic = graphic;
        _sceneLabel = sceneLabel;

        _graphic.addImage(_sceneLabel, imageLabels[3], "./src/Images/PacMan/pacman_left.gif");
        _graphic.addImage(_sceneLabel, imageLabels[1], "./src/Images/PacMan/pacman_right.gif");
        _graphic.addImage(_sceneLabel, imageLabels[0], "./src/Images/PacMan/pacman_up.gif");
        _graphic.addImage(_sceneLabel, imageLabels[2], "./src/Images/PacMan/pacman_down.gif");
        _graphic.addImage(_sceneLabel, imageLabels[4], "./src/Images/PacMan/pacman_death.gif");
    }

    // Display Pac-Man
    public void displayPacMan(String label, Position pos) throws Exception {
        if (Arrays.asList(imageLabels).contains(label) || label.equals("")) {

            hidePacMan();

            _graphic.setPositionImage(_sceneLabel, label, pos.x, pos.y);
            _graphic.displayImage(_sceneLabel, label);

            imageCurrent = label;
        }
        else {
            System.out.println("Mauvais label dans DisplayPacman");
        }
    }

    // Hide Pac-Man
    public void hidePacMan () throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            _graphic.hideImage(_sceneLabel, imageCurrent);
    }

}
