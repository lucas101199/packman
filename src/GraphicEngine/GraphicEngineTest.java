package GraphicEngine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GraphicEngineTest extends Application {

    void setSizeScene() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.setSizeScene("scene",100,50);
        engine.displayScene("scene");
        if (engine.window.getScene().getWidth() != 100) throw new AssertionError("setSizeScene");
        if (engine.window.getScene().getHeight() != 50) throw new AssertionError("setSizeScene");
        engine.setSizeScene("scene",200,70);
        if (engine.window.getScene().getWidth() != 200) throw new AssertionError("setSizeScene");
        if (engine.window.getScene().getHeight() != 70) throw new AssertionError("setSizeScene");
    }

    void addScene() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene1");
        engine.addScene("scene3");
        if (!engine.isPresent("scene1")) throw new AssertionError("addScene");
        if (engine.isPresent("scene2")) throw new AssertionError("addScene");
        if (!engine.isPresent("scene3")) throw new AssertionError("addScene");
    }

    void displayScene() throws Exception {
        GraphicEngine engine =new GraphicEngine("window");
        engine.addScene("scene1");
        engine.addScene("scene3");
        engine.displayScene("scene3");
        if (!engine.window.getScene().equals(engine.getScene("scene3").getScene())) throw new AssertionError("displayScene");
        if (engine.getScene("scene1").isDisplay()) throw new AssertionError("displayScene");
        if (!engine.getScene("scene3").isDisplay()) throw new AssertionError("displayScene");
    }

    void hideScene() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.addScene("scene2");
        engine.addImage("scene","image","./src/Image/pacmanLeft.gif");
        engine.displayScene("scene");
        engine.displayImage("scene","image");
        engine.displayScene("scene2");
        if (!engine.getScene("scene").getImage("image").isVisible()) throw new AssertionError("hideScene");
    }

    void deleteScene() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.deleteScene("scene");
        if (engine.window.getScene() != null) throw new AssertionError("deleteScene");
        if (engine.isPresent("scene")) throw new AssertionError("deleteScene");
    }

    void addImage() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.addImage("scene","image","./src/Image/pacmanLeft.gif");
        if (!engine.getScene("scene").isPresent("image")) throw new AssertionError("addImage");
    }

    void setPositionImage() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.addImage("scene","image","./src/Image/pacmanLeft.gif");
        Scene scene = engine.getScene("scene");
        engine.setPositionImage("scene","image",100,50);
        if (scene.getImage("image").getX() != 100) throw new AssertionError("setPositionImage");
        if (scene.getImage("image").getY() != 50) throw new AssertionError("setPositionImage");
        engine.setPositionImage("scene","image",200,70);
        if (scene.getImage("image").getX() != 200) throw new AssertionError("setPositionImage");
        if (scene.getImage("image").getY() != 70) throw new AssertionError("setPositionImage");
    }

    void resizeImage() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.addImage("scene","image","./src/Image/pacmanLeft.gif");
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

    void rotateImage() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.addImage("scene","image","./src/Image/pacmanLeft.gif");
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

    void displayImage() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.addImage("scene","image","./src/Image/pacmanLeft.gif");
        engine.displayImage("scene","image");
        ImageView imv = engine.getScene("scene").getImage("image");
        if (!imv.isVisible()) throw new AssertionError("displayImage");
    }

    void hideImage() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.addImage("scene","image","./src/Image/pacmanLeft.gif");
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

    void deleteImage() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        engine.addImage("scene","image","./src/Image/pacmanLeft.gif");
        engine.deleteImage("scene","image");
        if (engine.getScene("scene").isPresent("image")) throw new AssertionError("deleteImage");
    }

    void isPresent() throws Exception {
        GraphicEngine engine = new GraphicEngine("window");
        engine.addScene("scene");
        if (engine.isPresent("scene2")) throw new AssertionError("isPresent");
        if (!engine.isPresent("scene")) throw new AssertionError("isPresent");
    }

    @Override
    public void start(Stage stage) throws Exception {
        setSizeScene();
        addScene();
        displayScene();
        hideScene();
        deleteScene();
        addImage();
        setPositionImage();
        resizeImage();
        rotateImage();
        displayImage();
        hideImage();
        deleteImage();
        isPresent();
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
