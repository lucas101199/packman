package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class DisplayPinky extends DisplayCharacter{

    public DisplayPinky(GraphicEngine window, String nameScene) throws Exception {
        super(window,nameScene,new String[]{"pinky_left","pinky_right","pinky_up","pinky_down","pinky_edible",
                "eyes_left","eyes_right","eyes_up","eyes_down"});

        window.addImage(scene, imageLabels[0], "./src/Images/Ghosts/pink/ghost_pink_left.gif");
        window.addImage(scene, imageLabels[1], "./src/Images/Ghosts/pink/ghost_pink_right.gif");
        window.addImage(scene, imageLabels[2], "./src/Images/Ghosts/pink/ghost_pink_up.gif");
        window.addImage(scene, imageLabels[3], "./src/Images/Ghosts/pink/ghost_pink_down.gif");

        window.addImage(scene, imageLabels[4], "./src/Images/Ghosts/ghost_edible.gif");

        //window.addImage(scene, imageLabels[5], "./src/sample/Images/Ghosts/eyes/eyes_left.png");
        //window.addImage(scene, imageLabels[6], "./src/sample/Images/Ghosts/eyes/eyes_right.png");
        //window.addImage(scene, imageLabels[7], "./src/sample/Images/Ghosts/eyes/eyes_up.png");
        //window.addImage(scene, imageLabels[8], "./src/sample/Images/Ghosts/eyes/eyes_down.png");
    }
}
