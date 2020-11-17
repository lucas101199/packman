package GraphicEngine;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Scene {

    private javafx.scene.Scene scene;
    private Group root;

    public Scene(String label) {
        this.root = new Group();
        this.scene = new javafx.scene.Scene(this.root);
        root.setId(label);
        root.setVisible(false);
    }


    /**
     *
     * @return the name {@code label} of the scene
     */
    public String getLabel() { return root.getId(); }

    /**
     * {@code True} if we want the scene to be displayed {@code false} otherwise
     * Always initialize to false
     * @param display the value of the display
     */
    public void setDisplay(boolean display) {
        root.setVisible(display);
    }

    /**
     *
     * @return the value of the boolean {@code isDisplay}
     */
    public boolean isDisplay() { return root.isVisible(); }

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
        if (isPresent(imageLabel))
            throw new Exception("Image with the same name already exist");
        Image image = new Image(new FileInputStream(imageFile));
        ImageView imageView = new ImageView(image);
        imageView.setId(imageLabel);
        imageView.setVisible(false);
        root.getChildren().add(imageView);
    }

    /**
     * Set the position of the object in the scene
     * @param imageLabel the name to identified the image
     * @param x the first coordinate of the image
     * @param y the second coordinate of the image
     * @throws Exception if the object is missing
     */
    public void setPositionImage(String imageLabel, double x, double y) throws Exception {
        ImageView image = getImage(imageLabel);
        if (image != null) {
            image.setX(x - (image.getImage().getWidth()/2));
            image.setY(y - (image.getImage().getHeight()/2));
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     *
     * @param imageLabel the name of the image
     * @return the image or null if it doesn't exist
     */

    public ImageView getImage(String imageLabel) {
        for (Node image : root.getChildren()) {
            if (image.getId().equals(imageLabel))
                return (ImageView) image;
        }
        return null;
    }

    /**
     * Resize the image with the given value
     * @param imageLabel the name to identified the image
     * @param height the height of the image
     * @param width the width of the image
     * @throws Exception if the object is missing
     */
    public void resizeImage(String imageLabel, double height, double width) throws Exception {
        ImageView image = getImage(imageLabel);
        if (image != null) {
            ImageView e = (ImageView) root.getChildren().get(root.getChildren().indexOf(image));
            e.setFitHeight(height);
            e.setFitWidth(width);
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
        ImageView image = getImage(imageLabel);
        if (image != null) {
            image.setRotate((image.getRotate() + angle) % 360);
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
        ImageView image = getImage(imageLabel);
        if (image != null) {
            image.setVisible(true);
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
        ImageView image = getImage(imageLabel);
        if (image != null) {
            image.setVisible(false);
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
        ImageView image = getImage(imageLabel);
        if (image != null) {
            root.getChildren().remove(image);
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
        for (Node node : root.getChildren()) {
            if (node.getId().equals(Label)) return true;
        }
        return false;
    }

    public void setSize(double width, double height) {
        Group newroot = new Group(this.root);
        javafx.scene.Scene newscene = new javafx.scene.Scene(newroot,width,height);
        newroot.setId(this.root.getId());
        newroot.setVisible(this.root.isVisible());
        this.root = newroot;
        this.scene = newscene;
    }
}