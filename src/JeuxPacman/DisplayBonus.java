package JeuxPacman;

import GraphicEngine.GraphicEngine;

public abstract class DisplayBonus {

    final String scene;
    final GraphicEngine window;
    final String imageLabel;

    public DisplayBonus(GraphicEngine window, String scene, String imageLabel) {
        this.scene = scene;
        this.window = window;
        this.imageLabel = imageLabel;
    }

    public void display(Position pos) throws Exception {
        window.setPositionImage(scene,imageLabel,pos.x, pos.y, true);
        window.displayObject(scene,imageLabel);
    }

    public void hide() throws Exception {
        window.hideObject(scene,imageLabel);
    }

}
