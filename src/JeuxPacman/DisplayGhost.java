package JeuxPacman;

import GraphicEngine.GraphicEngine;

public abstract class DisplayGhost extends DisplayCharacter{

    private static int id = 0;
    private final int ghostid;

    protected DisplayGhost(GraphicEngine window, String scene, String[] imageLabels) throws Exception {
        super(window, scene, imageLabels);

        ghostid = id;
        id++;

        window.addImage(scene,"ghostRun"+ghostid,"./src/Images/Ghosts/ghost_edible.gif");

    }

    public void display(Direction dir, Position pos, boolean edible, boolean dead) throws Exception {
        if (edible) {
            hide();
            window.setPositionImage(this.scene, "ghostRun"+ghostid, pos.x, pos.y, true);
            window.displayObject(this.scene, "ghostRun"+ghostid);

            imageCurrent = "ghostRun"+ghostid;
        } else if (dead) {
            hide();
            window.setPositionImage(this.scene, "ghostDead"+ghostid, pos.x, pos.y, true);
            window.displayObject(this.scene, "ghostDead"+ghostid);

            imageCurrent = "ghostDead"+ghostid;
        }
            super.display(dir, pos);
    }
}
