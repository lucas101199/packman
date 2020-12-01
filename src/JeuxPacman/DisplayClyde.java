package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class DisplayClyde extends DisplayCharacter {

    public DisplayClyde(GraphicEngine window, String nameScene) throws Exception {
        super(window,nameScene,new String[]{"clyde_left","clyde_right","clyde_up","clyde_down","clyde_edible",
                "eyes_left","eyes_right","eyes_up","eyes_down"});

        window.addImage(scene, imageLabels[0], "./src/Images/Ghosts/orange/ghost_orange_left.gif");
        window.addImage(scene, imageLabels[1], "./src/Images/Ghosts/orange/ghost_orange_right.gif");
        window.addImage(scene, imageLabels[2], "./src/Images/Ghosts/orange/ghost_orange_up.gif");
        window.addImage(scene, imageLabels[3], "./src/Images/Ghosts/orange/ghost_orange_down.gif");

        window.addImage(this.scene, imageLabels[4], "./src/Images/Ghosts/ghost_edible.gif");

        //windows.addImage(this.scene, imageLabels[5], "./src/Images/Ghosts/eyes/eyes_left.png");
        //windows.addImage(this.scene, imageLabels[6], "./src/Images/Ghosts/eyes/eyes_right.png");
        //windows.addImage(this.scene, imageLabels[7], "./src/Images/Ghosts/eyes/eyes_up.png");
        //windows.addImage(this.scene, imageLabels[8], "./src/Images/Ghosts/eyes/eyes_down.png");
    }
}
