package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class DisplayBrown extends DisplayGhost {

    public DisplayBrown(GraphicEngine window, String nameScene) throws Exception {
        super(window,nameScene,new String[]{"brown_left","brown_right","brown_up","brown_down","brown_edible",
                "eyes_left","eyes_right","eyes_up","eyes_down"});

        window.addImage(scene, imageLabels[0], "./src/Images/Ghosts/brown/ghost_brown_left.gif");
        window.addImage(scene, imageLabels[1], "./src/Images/Ghosts/brown/ghost_brown_right.gif");
        window.addImage(scene, imageLabels[2], "./src/Images/Ghosts/brown/ghost_brown_up.gif");
        window.addImage(scene, imageLabels[3], "./src/Images/Ghosts/brown/ghost_brown_down.gif");

        window.addImage(this.scene, imageLabels[4], "./src/Images/Ghosts/ghost_edible.gif");

        //windows.addImage(this.scene, imageLabels[5], "./src/Images/Ghosts/eyes/eyes_left.png");
        //windows.addImage(this.scene, imageLabels[6], "./src/Images/Ghosts/eyes/eyes_right.png");
        //windows.addImage(this.scene, imageLabels[7], "./src/Images/Ghosts/eyes/eyes_up.png");
        //windows.addImage(this.scene, imageLabels[8], "./src/Images/Ghosts/eyes/eyes_down.png");
    }
}
