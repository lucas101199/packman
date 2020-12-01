package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class DisplayInky extends DisplayCharacter{

    public DisplayInky(GraphicEngine window, String nameScene) throws Exception {
        super(window,nameScene,new String[]{"inky_left","inky_right","inky_up","inky_down","inky_edible",
                "eyes_left","eyes_right","eyes_up","eyes_down"});

        window.addImage(this.scene, imageLabels[0], "./src/Images/Ghosts/blue/ghost_blue_left.gif");
        window.addImage(this.scene, imageLabels[1], "./src/Images/Ghosts/blue/ghost_blue_right.gif");
        window.addImage(this.scene, imageLabels[2], "./src/Images/Ghosts/blue/ghost_blue_up.gif");
        window.addImage(this.scene, imageLabels[3], "./src/Images/Ghosts/blue/ghost_blue_down.gif");

        window.addImage(this.scene, imageLabels[4], "./src/Images/Ghosts/ghost_edible.gif");

        //window.addImage(this.scene, imageLabels[5], "./src/sample/Images/Ghosts/eyes/eyes_left.png");
        //window.addImage(this.scene, imageLabels[6], "./src/sample/Images/Ghosts/eyes/eyes_right.png");
        //window.addImage(this.scene, imageLabels[7], "./src/sample/Images/Ghosts/eyes/eyes_up.png");
        //window.addImage(this.scene, imageLabels[8], "./src/sample/Images/Ghosts/eyes/eyes_down.png");
    }
}
