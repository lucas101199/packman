package GraphicEngine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class SceneTest extends Application {

    void setDisplay() {
        Scene scene = new Scene("scene");
        scene.setDisplay(true);
        if ((!scene.isDisplay())) throw new AssertionError("setDisplay");
        scene.setDisplay(false);
        if ((scene.isDisplay())) throw new AssertionError("setDisplay");
    }

    void addImage() throws Exception {
        Scene scene = new Scene("scene");
        String imageLabel = "imageLabel";
        String imageLabel1 = "imageLabel1";
        String imageFile = "./src/Image/blueghost.gif";
        scene.addImage(imageLabel,imageFile);
        if (scene.getImage(imageLabel).isVisible()) throw new AssertionError("addImage");
        if (!scene.getImage(imageLabel).getId().equals(imageLabel)) throw new AssertionError("addImage");
        scene.addImage(imageLabel1,imageFile);
        if (scene.getImage(imageLabel1).isVisible()) throw new AssertionError("addImage");
        if (!scene.getImage(imageLabel1).getId().equals(imageLabel1)) throw new AssertionError("addImage");
    }

    void setPositionImage() throws Exception {
        Scene scene = new Scene("scene");
        String imageFile = "./src/Image/blueghost.gif";
        scene.addImage("imageLabel",imageFile);
        scene.setPositionImage("imageLabel",100,50);
        if (scene.getImage("imageLabel").getX() != 100) throw new AssertionError("setPositionImage");
        if (scene.getImage("imageLabel").getY() != 50) throw new AssertionError("setPositionImage");
        scene.setPositionImage("imageLabel",200,70);
        if (scene.getImage("imageLabel").getX() != 200) throw new AssertionError("setPositionImage");
        if (scene.getImage("imageLabel").getY() != 70) throw new AssertionError("setPositionImage");
    }

    void resizeImage() throws Exception {
        Scene scene = new Scene("scene");
        scene.addImage("image","./src/Image/blueghost.gif");
        scene.resizeImage("image",100,50);
        ImageView imv = scene.getImage("image");
        if (imv.getFitHeight() != 100) throw new AssertionError("resizeImage");
        if (imv.getFitWidth() != 50) throw new AssertionError("resizeImage");
        scene.resizeImage("image",70,80);
        imv = scene.getImage("image");
        if (imv.getFitHeight() != 70) throw new AssertionError("resizeImage");
        if (imv.getFitWidth() != 80) throw new AssertionError("resizeImage");
    }

    void rotateImage() throws Exception {
        Scene scene = new Scene("scene");
        scene.addImage("image","./src/Image/blueghost.gif");
        scene.rotateNode("image",90);
        ImageView imv = scene.getImage("image");
        if (imv.getRotate() != 90) throw new AssertionError("rotateNode");
        scene.rotateNode("image",90);
        imv = scene.getImage("image");
        if (imv.getRotate() != 180) throw new AssertionError("rotateNode");
        scene.rotateNode("image",90);
        imv = scene.getImage("image");
        if (imv.getRotate() != 270) throw new AssertionError("rotateNode");
        scene.rotateNode("image",90);
        imv = scene.getImage("image");
        if (imv.getRotate() != 0) throw new AssertionError("rotateNode");
    }

    void displayImage() throws Exception {
        Scene scene = new Scene("scene");
        scene.addImage("image","./src/Image/blueghost.gif");
        scene.displayNode("image");
        ImageView imv = scene.getImage("image");
        if (!imv.isVisible()) throw new AssertionError("displayNode");
    }

    void hideImage() throws Exception {
        Scene scene = new Scene("scene");
        scene.addImage("image","./src/Image/blueghost.gif");
        scene.displayNode("image");
        scene.hideNode("image");
        ImageView imv = scene.getImage("image");
        if (imv.isVisible()) throw new AssertionError("hideNode");
    }

    void deleteImage() throws Exception {
        Scene scene = new Scene("scene");
        scene.addImage("image","./src/Image/blueghost.gif");
        scene.deleteNode("image");
        if (scene.getImage("image") != null) throw new AssertionError("deleteNode");
    }

    void isPresent() throws Exception {
        Scene scene = new Scene("scene");
        scene.addImage("image","./src/Image/blueghost.gif");
        if (!scene.isPresent("image")) throw new AssertionError("isPresent");
        if (scene.isPresent("test")) throw new AssertionError("isPresent");
    }

    @Override
    public void start(Stage stage) throws Exception {
        setDisplay();
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