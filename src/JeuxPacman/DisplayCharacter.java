package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.Arrays;

public abstract class DisplayCharacter {
    final String scene;
    final GraphicEngine window;
    String imageCurrent= "";
    final String[] imageLabels;

    protected DisplayCharacter(GraphicEngine window, String scene, String[] imageLabels) {
        this.scene = scene;
        this.window = window;
        this.imageLabels = imageLabels;
    }

    public void display(Direction dir, Position pos) throws Exception {
        String label;
        if (dir == null)
            return;
        switch (dir) {
            case EAST:
                label = imageLabels[1];
                break;
            case WEST:
                label = imageLabels[0];
                break;
            case NORTH:
                label = imageLabels[2];
                break;
            case SOUTH:
                label = imageLabels[3];
                break;
            default :
                label = "";
        }
        if (Arrays.asList(imageLabels).contains(label) || label.equals("")) {

            hide();

            window.setPositionImage(this.scene, label, pos.x, pos.y, true);
            window.displayObject(this.scene, label);

            imageCurrent = label;
        }
        else {
            System.out.println("Mauvais label dans Display");
        }
    }

    public void hide() throws Exception {
        if (Arrays.asList(imageLabels).contains(imageCurrent))
            window.hideObject(this.scene, imageCurrent);
    }

}
