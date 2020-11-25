package GraphicEngine;

import Interfaces.GameInterface;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;

public class GraphicEngine extends Application {

    private static GameInterface game;
    private static String gameName;

    public List<Scene> sceneList;
    public Stage window;

    public void construct(String windowLabel) {
        this.sceneList = new LinkedList<>();
        this.window = new Stage();
        this.window.setTitle(windowLabel);
        this.window.show();
    }

    /**
     * Add a {@code scene} to the list of scene
     * @param sceneLabel the name of the scene
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void addScene(String sceneLabel) throws Exception {
        if (!isPresent(sceneLabel)) {
            Scene scene = new Scene(sceneLabel);
            sceneList.add(scene);
        } else {
            throw new Exception("Already define a scene with this name");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void displayScene(String sceneLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) {
                    scene.setDisplay(true);
                    this.window.setScene(scene.getScene());
                }
                else
                    scene.setDisplay(false);
            }
            this.window.show();
        } else {
            throw new Exception("the scene : " + sceneLabel + " has not been create");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void deleteScene(String sceneLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) {
                    if (scene.isDisplay()) {
                        window.setScene(null);
                        scene.setDisplay(false);
                    }
                    sceneList.remove(scene);
                }
            }
        } else {
            throw new Exception("the scene " + sceneLabel + " does not exist");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @param imageFile the URL of the file
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void addImage(String sceneLabel, String imageLabel, String imageFile) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.addImage(imageLabel,imageFile);
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @return the scene or null if it doesn't exist
     */

    public Scene getScene(String sceneLabel) {
        for (Scene scene : sceneList) {
            if (scene.getLabel().equals(sceneLabel)) return scene;
        }
        return null;
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @param x the new position of the object's center in the x axis
     * @param y the new position of the object's center in the y axis
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void setPositionImage(String sceneLabel, String imageLabel, double x, double y) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.setPositionImage(imageLabel,x,y);
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @param height the new height of the object
     * @param width the new width of the object
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void resizeImage(String sceneLabel, String imageLabel, double height, double width) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.resizeImage(imageLabel, height, width);
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @param angle how much degre is the rotation
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void rotateImage(String sceneLabel, String imageLabel, double angle) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.rotateNode(imageLabel, angle);
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void displayImage(String sceneLabel, String imageLabel) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.displayNode(imageLabel);
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void hideImage(String sceneLabel, String imageLabel) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.hideNode(imageLabel);
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void deleteImage(String sceneLabel, String imageLabel) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.deleteNode(imageLabel);
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    /**
     *
     * @param sceneLabel the name of the scene
     * @return {@code True} if the scene is present in the window otherwise {@code False}
     */
    public boolean isPresent(String sceneLabel) {
        for (Scene scene : sceneList) {
            if (scene.getLabel().equals(sceneLabel)) return true;
        }
        return false;
    }

    /**
     * Change the size of the scene
     * @param sceneLabel the name of the scene to resize
     * @param width width for the scene
     * @param height height for the scene
     * @throws Exception if the scene with the given name is not found
     */

    public void setSizeScene(String sceneLabel, double width, double height) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.setSize(width,height);
            if (scene.isDisplay())
                this.window.setScene(scene.getScene());
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    public void addTextButton(String sceneLabel,String buttonLabel, String text) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.addTextButton(buttonLabel,text);
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    @Override
    public void start(Stage stage) {

        this.construct(gameName);

        game.set_graphic(this);

        game.init();
        game.start();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> game.update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void launcher(GameInterface game, String gameName){
        GraphicEngine.game = game;
        GraphicEngine.gameName = gameName;
        Application.launch(GraphicEngine.class);
    }

}
