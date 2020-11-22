package GraphicEngine;

/*TODO fix the superposition of 2 scene cause we have a bug when
   we create the first then display it then create a second one
   then display it then if we want to display the first one
   it doesn't work and display just a white screen instead
*/


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
                graphic.addScene("menu");
                graphic.displayScene("menu");
                graphic.addImage("menu", "pacman", "./src/Images/PacMan/pacman_up.gif");
                graphic.displayImage("menu", "pacman");
                graphic.setPositionImage("menu", "pacman", 200, 200);
                graphic.rotateImage("menu","pacman",45);
                graphic.setSizeScene("menu",400,400);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void update() {
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public void set_graphic(GraphicEngine graphic) {
            this.graphic = graphic;
        }
    }

}
