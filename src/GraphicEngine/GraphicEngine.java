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

    public void setWindow(double height, double width) {
        this.window.setHeight(height);
        this.window.setWidth(width);
    }

    public void addScene(String sceneLabel) throws Exception {
        if (!isPresent(sceneLabel)) {
            Scene scene = new Scene(sceneLabel);
            sceneList.add(scene);
            this.window.setScene(scene.getScene());
        } else {
            throw new Exception("Already define a scene with this name");
        }
    }

    public void displayScene(String sceneLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) {
                    if (!scene.isDisplay()) {
                        scene.setDisplay(true);
                    }
                }
            }
            this.window.show();
        } else {
            throw new Exception("the scene : " + sceneLabel + " has not been create");
        }
    }

    public void deleteScene(String sceneLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) {
                    scene.setDisplay(false);
                    sceneList.remove(scene);
                }
            }
        } else {
            throw new Exception("the scene " + sceneLabel + " does not exist");
        }
    }

    public void hideScene(String sceneLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.setDisplay(false);
            }
        } else {
            throw new Exception("the scene " + sceneLabel + " does not exist");
        }
    }

    public void addImage(String sceneLabel, String imageLabel, String imageFile) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.addImage(imageLabel, imageFile);
            }
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    public void setPositionImage(String sceneLabel, String imageLabel, double x, double y) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.setPositionImage(imageLabel, y, x);
            }
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    public void resizeImage(String sceneLabel, String imageLabel, double height, double width) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.resizeImage(imageLabel, height, width);
            }
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    public void rotateImage(String sceneLabel, String imageLabel, double angle) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.rotateImage(imageLabel, angle);
            }
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    public void displayImage(String sceneLabel, String imageLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.displayImage(imageLabel);
            }
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    public void hideImage(String sceneLabel, String imageLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.hideImage(imageLabel);
            }
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    public void deleteImage(String sceneLabel, String imageLabel) throws Exception {
        if (isPresent(sceneLabel)) {
            for (Scene scene : sceneList) {
                if (scene.getLabel().equals(sceneLabel)) scene.deleteImage(imageLabel);
            }
        } else {
            throw new Exception("no scene with this name is present");
        }
    }

    public boolean isPresent(String sceneLabel) {
        for (Scene scene : sceneList) {
            if (scene.getLabel().equals(sceneLabel)) return true;
        }
        return false;
    }
}
