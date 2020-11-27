package GraphicEngine;

/*TODO fix the superposition of 2 scene cause we have a bug when
   we create the first then display it then create a second one
   then display it then if we want to display the first one
   it doesn't work and display just a white screen instead
*/


import Interfaces.GameInterface;

public class Main {

    public static void main(String[] args) {

        GameInterface game = new GameMain();
        GraphicEngine.launcher(game,"Test");

    }

    static class GameMain implements GameInterface {

        GraphicEngine graphic;

        @Override
        public void init() {
        }

        @Override
        public void start() {
            try {
                graphic.addScene("scene");
                graphic.displayScene("scene");
                graphic.addImage("scene", "image", "./src/Images/PacMan/pacman_up.gif");
                graphic.displayObject("scene", "image");
                graphic.setPositionImage("scene", "image", 200, 200);
                graphic.setSizeScene("scene", 400, 400);
                graphic.rotateObject("scene", "image", 45);
                graphic.addTextButton("scene", "button", "rotate");
                graphic.displayObject("scene", "button");
                graphic.setPositionTextButton("scene", "button", 100, 300);
                graphic.resizeTextButton("scene", "button", 30, 100);
                graphic.addImageButton("scene", "blue", "./src/Images/Ghosts/blue/ghost_blue_down.gif");
                graphic.displayObject("scene", "blue");
                graphic.setPositionImageButton("scene", "blue", 200, 300);
                graphic.resizeImageButton("scene", "blue", 30, 30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void update() {
            graphic.window.show();
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public void set_graphic(GraphicEngine graphic) {
            this.graphic = graphic;
        }

        @Override
        public void handleKey(String key) {
            try {
                if (key.equals("button"))
                    graphic.rotateObject("scene", "image", 45);
                if (key.equals("blue"))
                    graphic.rotateObject("scene", "image", -45);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
