package GraphicEngine;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Scene {

    private boolean isDisplay;
    private final String label;
    private final javafx.scene.Scene scene;
    private final Group root;
    private final List<Node> content;

    public Scene(String label) {
        this.label = label;
        this.isDisplay = false;
        this.root = new Group();
        this.scene = new javafx.scene.Scene(root);
        this.content = new LinkedList<>();
        root.setId(label);
        root.setVisible(false);
    }


    /**
     *
     * @return the name {@code label} of the scene
     */
    public String getLabel() {
        return label;
    }

    /**
     * {@code True} if we want the scene to be displayed {@code false} otherwise
     * Always initialize to false
     * @param display the value of the display
     */
    public void setDisplay(boolean display) {
        root.setVisible(display);
        isDisplay = display;
    }

    /**
     *
     * @return the value of the boolean {@code isDisplay}
     */
    public boolean isDisplay() {
        return isDisplay;
    }

    /**
     *
     * @return the JavaFx scene associate to this class Scene
     */
    public javafx.scene.Scene getScene() {
        return scene;
    }

    /**
     * Add an image to the scene and to the list {@code content} which has all the {@code Node} in the scene
     * @param imageLabel the name to identified the image
     * @param imageFile the URL of the file
     * @throws Exception if an image has already the name {@code imageLabel}
     */
    public void addImage(String imageLabel, String imageFile) throws Exception {
        for (Node image : content) {
            if (image.getId().equals(imageLabel)) throw new Exception("Image with the same name already exist");
        }
        Image image = new Image(new FileInputStream(imageFile));
        ImageView imageView = new ImageView(image);
        imageView.setId(imageLabel);
        imageView.setVisible(false);
        content.add(imageView);
        root.getChildren().add(imageView);
    }

    /**
     * Set the position of the object in the scene
     * @param imageLabel the name to identified the image
     * @param height the height of the image
     * @param width the width of the image
     * @throws Exception if the object is missing
     */
    public void setPositionImage(String imageLabel, double height, double width) throws Exception {
        if (isPresent(imageLabel)) {
            for (Node image : content) {
                if (image.getId().equals(imageLabel)) {
                    image.setLayoutX(width);
                    image.setLayoutY(height);
                }
            }
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     * Resize the image with the given value
     * @param imageLabel the name to identified the image
     * @param height the height of the image
     * @param width the width of the image
     * @throws Exception if the object is missing
     */
    public void resizeImage(String imageLabel, double height, double width) throws Exception {
        if (isPresent(imageLabel)) {
            for (Node node : content) {
                if (node.getId().equals(imageLabel)) {
                    ImageView e = (ImageView) root.getChildren().get(root.getChildren().indexOf(node));
                    e.setFitHeight(height);
                    e.setFitWidth(width);
                }
            }
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     * Rotate the object {@code Node} clockwise with the given {@code angle} in degrees
     * @param imageLabel the name to identified the image
     * @param angle in degrees
     * @throws Exception if the object is missing
     */
    public void rotateImage(String imageLabel, double angle) throws Exception {
        if (isPresent(imageLabel)) {
            for (Node node : content) {
                if (node.getId().equals(imageLabel)) {
                    node.setRotate(angle);
                }
            }
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     *
     * @param imageLabel the name to identified the image
     * @throws Exception if the object is missing
     */
    public void displayImage(String imageLabel) throws Exception {
        if (isPresent(imageLabel)) {
            for (Node node : content) {
                if (node.getId().equals(imageLabel)) {
                    node.setVisible(true);
                }
            }
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     *
     * @param imageLabel the name to identified the image
     * @throws Exception if the object is missing
     */
    public void hideImage(String imageLabel) throws Exception {
        if (isPresent(imageLabel)) {
            for (Node node : content) {
                if (node.getId().equals(imageLabel)) {
                    node.setVisible(false);
                }
            }
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     * Delete the image from the list {@code content} and from the Group {@code root}
     * @param imageLabel the name to identified the image
     * @throws Exception if the object is missing
     */
    public void deleteImage(String imageLabel) throws Exception {
        if (isPresent(imageLabel)) {
            for (Node node : content) {
                if (node.getId().equals(imageLabel)) {
                    root.getChildren().remove(node);
                    content.remove(node);
                }
            }
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     *
     * @param Label the name of the object to be seeking
     * @return {@code True} if an object with the name {@code Label} is present
     *  in the scene
     */
    public boolean isPresent(String Label) {
        for (Node node : content) {
            if (node.getId().equals(Label)) return true;
        }
        return false;
    }
}