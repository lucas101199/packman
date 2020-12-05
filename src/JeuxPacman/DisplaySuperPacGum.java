package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class DisplaySuperPacGum extends DisplayBonus{
    public DisplaySuperPacGum(GraphicEngine window, String scene, String imageLabel, int num) throws Exception {
        super(window, scene, "superpacgum"+num);
        window.addImage(scene,"superpacgum"+num,"./src/Images/Items/superPacGomme.png");
    }
}
