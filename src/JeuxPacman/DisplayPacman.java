package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.Arrays;

public class DisplayPacman {

    private final String _sceneLabel;
    private final GraphicEngine _graphic;
    private final String[] imageLabels = {"Pacman_up","Pacman_right","Pacman_down","Pacman_left","Pacman_death","Pacman_start"};

    private String imageCurrent= "";


    public DisplayPacman(GraphicEngine graphic, String sceneLabel) throws Exception {
        _graphic = graphic;
        _sceneLabel = sceneLabel;

        _graphic.addImage(_sceneLabel, imageLabels[3], "./src/Images/PacMan/pacman_left.gif");
        _graphic.addImage(_sceneLabel, imageLabels[1], "./src/Images/PacMan/pacman_right.gif");
        _graphic.addImage(_sceneLabel, imageLabels[0], "./src/Images/PacMan/pacman_up.gif");
        _graphic.addImage(_sceneLabel, imageLabels[2], "./src/Images/PacMan/pacman_down.gif");
        _graphic.addImage(_sceneLabel, imageLabels[5],"./src/Images/PacMan/pacman_start.gif");
    }

    // Display Pac-Man
    public void displayPacMan(Direction dir, Position pos) throws Exception {
        String label;
        if (dir == null)
            return;
        switch (dir) {
            case EAST:
                label = imageLabels[1];
                break;
            case WEST:
                label = imageLabels[3];
                break;
            case NORTH:
                label = imageLabels[0];
                break;
            case SOUTH:
                label = imageLabels[2];
                break;
            default :
                label = "";
        }
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

    public void displayPacmanStart(Position pos) throws Exception {
        if (_graphic.getScene("maze").isPresent("Pacman_death")) {
            _graphic.deleteImage("maze", "Pacman_death");
            if (imageCurrent.equals("Pacman_death"))
                imageCurrent = "";
        }
        hidePacMan();
        _graphic.setPositionImage(_sceneLabel, "Pacman_start", pos.x, pos.y);
        _graphic.displayImage(_sceneLabel, "Pacman_start");
        imageCurrent = "Pacman_start";
    }

    public void displayPacManDeath(Position pos) throws Exception {
        _graphic.addImage(_sceneLabel, imageLabels[4], "./src/Images/PacMan/pacman_death.gif");
        hidePacMan();
        _graphic.setPositionImage(_sceneLabel, "Pacman_death", pos.x, pos.y);
        _graphic.displayImage(_sceneLabel, "Pacman_death");
        imageCurrent = "Pacman_death";
    }

    // Hide Pac-Man
    public void hidePacMan () throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            _graphic.hideImage(_sceneLabel, imageCurrent);
    }

}
