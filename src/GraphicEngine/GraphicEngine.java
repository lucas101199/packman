package GraphicEngine;

import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class GraphicEngine {
    public List<Scene> sceneList;
    public Stage window;

    public GraphicEngine(String windowLabel) {
        this.sceneList = new LinkedList<>();
        this.window = new Stage();
        this.window.setTitle(windowLabel);
        this.window.show();
    }

    /**
     * Set the size of the window with the given parameter
     * @param height the height of the window
     * @param width the width of the window
     */
    public void setWindow(double height, double width) {
        this.window.setHeight(height);
        this.window.setWidth(width);
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

    private Scene getScene(String sceneLabel) {
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
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.resizeImage(imageLabel, height, width);
            }
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
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.rotateImage(imageLabel, angle);
            }
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
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.displayImage(imageLabel);
            }
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
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.hideImage(imageLabel);
            }
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
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.deleteImage(imageLabel);
            }
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
}
