package JeuxPacman;

import GraphicEngine.GraphicEngine;

public class DisplayPacGum extends DisplayBonus{

    public DisplayPacGum(GraphicEngine window, String scene, int num) throws Exception {
        super(window, scene, "pacgum"+num);
        window.addImage(scene,"pacgum"+num,"./src/Images/Items/pacGomme.png");
    }
}
