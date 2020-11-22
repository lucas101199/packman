package GraphicEngine;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class GraphicEngineTest {

    public static void main(String[] args) {
        GameInterface test = new TestGame();
        GraphicEngine.launcher(test,"Test");
    }

    static class TestGame implements GameInterface {

        static GraphicEngine engine;

        @Override
        public void init() {
            try {
                setSizeScene();
                engine.construct("Test");
                addScene();
                engine.construct("Test");
                displayScene();
                engine.construct("Test");
                hideScene();
                engine.construct("Test");
                deleteScene();
                engine.construct("Test");
                addImage();
                engine.construct("Test");
                setPositionImage();
                engine.construct("Test");
                resizeImage();
                engine.construct("Test");
                rotateImage();
                engine.construct("Test");
                displayImage();
                engine.construct("Test");
                hideImage();
                engine.construct("Test");
                deleteImage();
                engine.construct("Test");
                isPresent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void start() {
            Platform.exit();
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
            engine = graphic;
        }

        static void setSizeScene() throws Exception {
            engine.addScene("scene");
            engine.setSizeScene("scene",100,50);
            engine.displayScene("scene");
            if (engine.window.getScene().getWidth() != 100) throw new AssertionError("setSizeScene");
            if (engine.window.getScene().getHeight() != 50) throw new AssertionError("setSizeScene");
            engine.setSizeScene("scene",200,70);
            if (engine.window.getScene().getWidth() != 200) throw new AssertionError("setSizeScene");
            if (engine.window.getScene().getHeight() != 70) throw new AssertionError("setSizeScene");
        }

        static void addScene() throws Exception {
            engine.addScene("scene1");
            engine.addScene("scene3");
            if (!engine.isPresent("scene1")) throw new AssertionError("addScene");
            if (engine.isPresent("scene2")) throw new AssertionError("addScene");
            if (!engine.isPresent("scene3")) throw new AssertionError("addScene");
        }

        static void displayScene() throws Exception {
            engine.addScene("scene1");
            engine.addScene("scene3");
            engine.displayScene("scene3");
            if (!engine.window.getScene().equals(engine.getScene("scene3").getScene())) throw new AssertionError("displayScene");
            if (engine.getScene("scene1").isDisplay()) throw new AssertionError("displayScene");
            if (!engine.getScene("scene3").isDisplay()) throw new AssertionError("displayScene");
        }

        static void hideScene() throws Exception {
            engine.addScene("scene");
            engine.addScene("scene2");
            engine.addImage("scene","image","./src/Images/PacMan/pacman_left.gif");
            engine.displayScene("scene");
            engine.displayImage("scene","image");
            engine.displayScene("scene2");
            if (!engine.getScene("scene").getImage("image").isVisible()) throw new AssertionError("hideScene");
        }

        static void deleteScene() throws Exception {
            engine.addScene("scene");
            engine.deleteScene("scene");
            if (engine.window.getScene() != null) throw new AssertionError("deleteScene");
            if (engine.isPresent("scene")) throw new AssertionError("deleteScene");
        }

        static void addImage() throws Exception {
            engine.addScene("scene");
            engine.addImage("scene","image","./src/Images/PacMan/pacman_left.gif");
            if (!engine.getScene("scene").isPresent("image")) throw new AssertionError("addImage");
        }

        static void setPositionImage() throws Exception {
            engine.addScene("scene");
            engine.addImage("scene","image","./src/Images/PacMan/pacman_left.gif");
            Scene scene = engine.getScene("scene");
            engine.setPositionImage("scene","image",100,50);
            if (scene.getImageX("image") != 100) throw new AssertionError("setPositionImage");
            if (scene.getImageY("image") != 50) throw new AssertionError("setPositionImage");
            engine.setPositionImage("scene","image",200,70);
            if (scene.getImageX("image") != 200) throw new AssertionError("setPositionImage");
            if (scene.getImageY("image") != 70) throw new AssertionError("setPositionImage");
        }

        static void resizeImage() throws Exception {
            engine.addScene("scene");
            engine.addImage("scene","image","./src/Images/PacMan/pacman_left.gif");
            Scene scene = engine.getScene("scene");
            engine.resizeImage("scene","image",100,50);
            ImageView imv = scene.getImage("image");
            if (imv.getFitHeight() != 100) throw new AssertionError("resizeImage");
            if (imv.getFitWidth() != 50) throw new AssertionError("resizeImage");
            engine.resizeImage("scene","image",70,80);
            imv = scene.getImage("image");
            if (imv.getFitHeight() != 70) throw new AssertionError("resizeImage");
            if (imv.getFitWidth() != 80) throw new AssertionError("resizeImage");
        }

        static void rotateImage() throws Exception {
            engine.addScene("scene");
            engine.addImage("scene","image","./src/Images/PacMan/pacman_left.gif");
            Scene scene = engine.getScene("scene");
            engine.rotateImage("scene","image",90);
            ImageView imv = scene.getImage("image");
            if (imv.getRotate() != 90) throw new AssertionError("rotateImage");
            engine.rotateImage("scene","image",90);
            imv = scene.getImage("image");
            if (imv.getRotate() != 180) throw new AssertionError("rotateImage");
            engine.rotateImage("scene","image",90);
            imv = scene.getImage("image");
            if (imv.getRotate() != 270) throw new AssertionError("rotateImage");
            engine.rotateImage("scene","image",90);
            imv = scene.getImage("image");
            if (imv.getRotate() != 0) throw new AssertionError("rotateImage");
        }

        static void displayImage() throws Exception {
            engine.addScene("scene");
            engine.addImage("scene","image","./src/Images/PacMan/pacman_left.gif");
            engine.displayImage("scene","image");
            ImageView imv = engine.getScene("scene").getImage("image");
            if (!imv.isVisible()) throw new AssertionError("displayImage");
        }

        static void hideImage() throws Exception {
            engine.addScene("scene");
            engine.addImage("scene","image","./src/Images/PacMan/pacman_left.gif");
            engine.displayImage("scene","image");
            engine.hideImage("scene","image");
            ImageView imv = engine.getScene("scene").getImage("image");
            if (imv.isVisible()) throw new AssertionError("hideImage");
            engine.displayScene("scene");
            engine.addScene("scene2");
            engine.displayScene("scene2");
            engine.displayScene("scene");
            imv = engine.getScene("scene").getImage("image");
            if (imv.isVisible()) throw new AssertionError("hideImage");
        }

        static void deleteImage() throws Exception {
            engine.addScene("scene");
            engine.addImage("scene","image","./src/Images/PacMan/pacman_left.gif");
            engine.deleteImage("scene","image");
            if (engine.getScene("scene").isPresent("image")) throw new AssertionError("deleteImage");
        }

        static void isPresent() throws Exception {
            engine.addScene("scene");
            if (engine.isPresent("scene2")) throw new AssertionError("isPresent");
            if (!engine.isPresent("scene")) throw new AssertionError("isPresent");
        }

    }

}
