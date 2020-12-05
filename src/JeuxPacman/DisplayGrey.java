package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class DisplayGrey extends DisplayCharacter {

    public DisplayGrey(GraphicEngine window, String nameScene) throws Exception {
        super(window,nameScene,new String[]{"grey_left","grey_right","grey_up","grey_down","grey_edible",
                "eyes_left","eyes_right","eyes_up","eyes_down"});

        window.addImage(scene, imageLabels[0], "./src/Images/Ghosts/grey/ghost_grey_left.gif");
        window.addImage(scene, imageLabels[1], "./src/Images/Ghosts/grey/ghost_grey_right.gif");
        window.addImage(scene, imageLabels[2], "./src/Images/Ghosts/grey/ghost_grey_up.gif");
        window.addImage(scene, imageLabels[3], "./src/Images/Ghosts/grey/ghost_grey_down.gif");

        window.addImage(this.scene, imageLabels[4], "./src/Images/Ghosts/ghost_edible.gif");

        //windows.addImage(this.scene, imageLabels[5], "./src/Images/Ghosts/eyes/eyes_left.png");
        //windows.addImage(this.scene, imageLabels[6], "./src/Images/Ghosts/eyes/eyes_right.png");
        //windows.addImage(this.scene, imageLabels[7], "./src/Images/Ghosts/eyes/eyes_up.png");
        //windows.addImage(this.scene, imageLabels[8], "./src/Images/Ghosts/eyes/eyes_down.png");
    }
}
