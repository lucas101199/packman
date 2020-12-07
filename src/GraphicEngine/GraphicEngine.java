package GraphicEngine;

import Interfaces.GameInterface;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;

public class GraphicEngine extends Application {

    private static GameInterface game;
    private static String gameName;

    public List<Scene> sceneList;
    public Stage window;
    private String currentSceneLabel;

    /**
     * Initialize the list {@code sceneList} of {@code Scene} and the {@code window}
     * @param windowLabel Name display at the top of the window
     */
    public void construct(String windowLabel) {
        this.sceneList = new LinkedList<>();
        this.window = new Stage();
        this.window.setTitle(windowLabel);
        this.window.show();
        currentSceneLabel = "";
    }

    /**
     * Add a new {@code Scene} to {@code sceneList}
     * @param sceneLabel Name of the {@code Scene}
     * @throws Exception if a {@code Scene} with the given {@code sceneLabel} already exists
     */
    public void addScene(String sceneLabel) throws Exception {
        if (!isPresent(sceneLabel)) {
            Scene scene = new Scene(sceneLabel,game);
            sceneList.add(scene);
        } else {
            throw new Exception("Scene "+sceneLabel+" already exists");
        }
    }

    /**
     * Change the size of the {@code Scene}
     * @param sceneLabel Name of the {@code Scene} to resize
     * @param width Width for the {@code Scene}
     * @param height Height for the {@code Scene}
     * @throws Exception if the {@code Scene} with the given {@code sceneLabel} doesn't exist
     */
    public void setSizeScene(String sceneLabel, double width, double height) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.setSize(width,height);
            if (scene.isDisplay())
                this.window.setScene(scene.getScene());
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }


    /**
     *
     * @return The label of the scene currently displayed
     */
    public String currentScene() {
        return currentSceneLabel;
    }

    /**
     * Display the {@code Scene} in the {@code window}
     * @param sceneLabel Name of the {@code Scene}
     * @throws Exception if the {@code Scene} with the given {@code sceneLabel} doesn't exist
     */
    public void displayScene(String sceneLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) {
                    scene.setDisplay(true);
                    this.window.setScene(scene.getScene());
                    currentSceneLabel = sceneLabel;
                }
                else
                    scene.setDisplay(false);
            }
            this.window.show();
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Delete the {@code Scene} from {@code sceneList}
     * @param sceneLabel Name of the {@code Scene}
     * @throws Exception if the {@code Scene} with the given {@code sceneLabel} doesn't exist
     */
    public void deleteScene(String sceneLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) {
                    if (scene.isDisplay()) {
                        window.setScene(null);
                        scene.setDisplay(false);
                        currentSceneLabel = "";
                    }
                    sceneList.remove(scene);
                }
            }
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     *
     * @param sceneLabel Name of the {@code Scene}
     * @return the {@code Scene} or null if it doesn't exist
     */
    public Scene getScene(String sceneLabel) {
        for (Scene scene : sceneList) {
            if (scene.getLabel().equals(sceneLabel)) return scene;
        }
        return null;
    }

    /**
     *
     * @param sceneLabel Name of the scene
     * @return {@code True} if the scene is present in the window otherwise {@code False}
     */
    public boolean isPresent(String sceneLabel) {
        for (Scene scene : sceneList) {
            if (scene.getLabel().equals(sceneLabel)) return true;
        }
        return false;
    }

    /**
     * Add a new image to the scene
     * @param sceneLabel Name of the scene
     * @param imageLabel Name of the image
     * @param imageFile URL of the file
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void addImage(String sceneLabel, String imageLabel, String imageFile) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.addImage(imageLabel,imageFile);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Add a new button to the scene
     * @param sceneLabel Name of the scene
     * @param buttonLabel Name of the button
     * @param text Text display on the button
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void addTextButton(String sceneLabel, String buttonLabel, String text) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.addTextButton(buttonLabel,text);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Add a new button to the scene
     * @param sceneLabel Name of the scene
     * @param buttonLabel Name of the button
     * @param file URL of the file
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void addImageButton(String sceneLabel, String buttonLabel, String file) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.addImageButton(buttonLabel,file);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Move the image in the scene
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @param x the new position of the image's center in the x axis
     * @param y the new position of the image's center in the y axis
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void setPositionImage(String sceneLabel, String imageLabel, double x, double y, boolean center) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.setPositionImage(imageLabel,x,y,center);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Move the button in the scene
     * @param sceneLabel the name of the scene
     * @param buttonLabel the name of the button
     * @param x the new position of the button's center in the x axis
     * @param y the new position of the button's center in the y axis
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void setPositionTextButton(String sceneLabel, String buttonLabel, double x, double y) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.setPositionButton(buttonLabel,x,y);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Move the button in the scene
     * @param sceneLabel the name of the scene
     * @param buttonLabel the name of the button
     * @param x the new position of the button's center in the x axis
     * @param y the new position of the button's center in the y axis
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void setPositionImageButton(String sceneLabel, String buttonLabel, double x, double y, boolean center) throws Exception {
        setPositionImage(sceneLabel,buttonLabel,x,y,center);
    }

    /**
     * Change the size of the image in the scene
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @param height the new height of the image
     * @param width the new width of the image
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void resizeImage(String sceneLabel, String imageLabel, double height, double width) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.resizeImage(imageLabel, height, width);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Change the size of the button in the scene
     * @param sceneLabel the name of the scene
     * @param buttonLabel the name of the button
     * @param height the new height of the button
     * @param width the new width of the button
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void resizeTextButton(String sceneLabel, String buttonLabel, double height, double width) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.resizeButton(buttonLabel,height,width);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Change the size of the button in the scene
     * @param sceneLabel Name of the scene
     * @param buttonLabel Name of the button
     * @param height Height of the image
     * @param width Width of the image
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void resizeImageButton(String sceneLabel, String buttonLabel, double height, double width) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.resizeImage(buttonLabel,height,width);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Rotate the objet in the scene
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the objet
     * @param angle the angle of the rotation
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void rotateObject(String sceneLabel, String imageLabel, double angle) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.rotateNode(imageLabel, angle);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Display the object in the scene
     * @param sceneLabel the name of the scene
     * @param objectLabel the name of the object
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void displayObject(String sceneLabel, String objectLabel) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.displayNode(objectLabel);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Hide the object in the scene
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the object
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void hideObject(String sceneLabel, String imageLabel) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.hideNode(imageLabel);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    /**
     * Delete the image from the scene
     * @param sceneLabel the name of the scene
     * @param imageLabel the name of the image
     * @throws Exception if the scene with the given {@code sceneLabel} is not found
     */
    public void deleteObject(String sceneLabel, String imageLabel) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.deleteNode(imageLabel);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    public void changeImage(String sceneLabel, String imageLabel, String file) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.changeImage(imageLabel,file);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    public void resetRotationObject(String sceneLabel, String objectLabel) throws Exception {
        Scene scene = getScene(sceneLabel);
        if (scene != null) {
            scene.resetRotationObject(objectLabel);
        } else {
            throw new Exception("Scene "+sceneLabel+" doesn't exist");
        }
    }

    public void stop() {
        Platform.exit();
    }

    /**
     * Main function which initializes, starts and updates the gameplay
     * @param stage Stage automatically created when the application is launched
     */
    @Override
    public void start(Stage stage) {

        this.construct(gameName);

        game.set_graphic(this);

        game.init();
        game.start();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/game.getSpeed()), actionEvent -> game.update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Static function to set the instance of the {@code game} and launch the graphic engine
     * @param game instance of the a game which implements {@code GameInterface}
     * @param gameName the name of the game which will be display at the top of the window
     */
    public static void launcher(GameInterface game, String gameName){
        GraphicEngine.game = game;
        GraphicEngine.gameName = gameName;
        Application.launch(GraphicEngine.class);
    }

}
