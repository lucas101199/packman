package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class DisplayPacman extends DisplayCharacter{

    public DisplayPacman(GraphicEngine graphic, String sceneLabel) throws Exception {
        super(graphic,sceneLabel,new String[]{"Pacman_left","Pacman_right","Pacman_up","Pacman_down","Pacman_death","Pacman_start"});

        window.addImage(scene, imageLabels[0], "./src/Images/PacMan/pacman_left.gif");
        window.addImage(scene, imageLabels[1], "./src/Images/PacMan/pacman_right.gif");
        window.addImage(scene, imageLabels[2], "./src/Images/PacMan/pacman_up.gif");
        window.addImage(scene, imageLabels[3], "./src/Images/PacMan/pacman_down.gif");
        window.addImage(scene, imageLabels[4], "./src/Images/PacMan/pacman_death.gif");
        window.addImage(scene, imageLabels[5],"./src/Images/PacMan/pacman_start.gif");
    }

    public void displayPacmanStart(Position pos) throws Exception {
        if (imageCurrent.equals(imageLabels[4])) {
            window.hideObject("maze", imageLabels[4]);
            imageCurrent = "";
        } else if (!imageCurrent.equals(""))
            hide();
        window.setPositionImage(scene, imageLabels[5], pos.x, pos.y, true);
        window.displayObject(scene, imageLabels[5]);
        imageCurrent = imageLabels[5];
    }

    public void displayPacManDeath(Position pos, Direction _direction) throws Exception {
        window.changeImage(scene, imageLabels[4], "./src/Images/PacMan/pacman_death.gif");
        hide();
        window.setPositionImage(scene, imageLabels[4], pos.x, pos.y, true);
        window.displayObject(scene, imageLabels[4]);
        window.resetRotationObject(scene,imageLabels[4]);
        switch (_direction){
            case EAST -> window.rotateObject(scene,imageLabels[4],90);
            case SOUTH -> window.rotateObject(scene,imageLabels[4],180);
            case WEST -> window.rotateObject(scene,imageLabels[4],270);
        }
        imageCurrent = imageLabels[4];
    }

}
