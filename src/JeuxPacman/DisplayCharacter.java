package JeuxPacman;

import GraphicEngine.GraphicEngine;

public abstract class DisplayCharacter {
    final String scene;
    final GraphicEngine window;
    String imageCurrent;
    final String[] imageLabels;

    protected DisplayCharacter(GraphicEngine window, String scene, String[] imageLabels) {
        this.scene = scene;
        this.window = window;
        this.imageLabels = imageLabels;
        this.imageCurrent = "";
    }

    public void display(Direction dir, Position pos) throws Exception {
        String label;
        if (dir == Direction.NONE)
            return;
        label = switch (dir) {
            case EAST -> imageLabels[1];
            case WEST -> imageLabels[0];
            case NORTH -> imageLabels[2];
            case SOUTH -> imageLabels[3];
            default -> "";
        };
        hide();
        window.setPositionImage(this.scene, label, pos.x, pos.y, true);
        window.displayObject(this.scene, label);

        imageCurrent = label;
    }

    public void hide() throws Exception {
        if (imageCurrent.equals(""))
            return;
        window.hideObject(this.scene, imageCurrent);
        imageCurrent = "";
    }

}
