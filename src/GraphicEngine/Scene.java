package GraphicEngine;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    public void setPositionButton(String buttonLabel, double x, double y) throws Exception {
        Button button = getButton(buttonLabel);
        if (button != null) {
            button.setLayoutX(x - (button.getWidth()/2));
            button.setLayoutY(y - (button.getHeight()/2));
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    public double getImageX(String imageLabel) throws Exception {
        ImageView image = getImage(imageLabel);
        if (image != null) {
            return image.getX() + (image.getImage().getWidth()/2);
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    public double getButtonX(String buttonLabel) throws Exception {
        Button button = getButton(buttonLabel);
        if (button != null) {
            return button.getLayoutX() + (button.getWidth()/2);
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    public double getImageY(String imageLabel) throws Exception {
        ImageView image = getImage(imageLabel);
        if (image != null) {
            return image.getY() + (image.getImage().getHeight()/2);
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    public double getButtonY(String buttonLabel) throws Exception {
        Button button = getButton(buttonLabel);
        if (button != null) {
            return button.getLayoutY() + (button.getHeight()/2);
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

    public Button getButton(String buttonLabel) {
        for (Node button : root.getChildren()) {
            if (button.getId().equals(buttonLabel))
                return (Button) button;
        }
        return null;
    }

    public Node getNode(String label) {
        for (Node node : root.getChildren()) {
            if (node.getId().equals(label))
                return node;
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
            image.setFitHeight(height);
            image.setFitWidth(width);
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    public void resizeButton(String buttonLabel, double height, double width) throws Exception {
        Button button = getButton(buttonLabel);
        if (button != null) {
            button.setPrefHeight(height);
            button.setPrefWidth(width);
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     * Rotate the object {@code Node} clockwise with the given {@code angle} in degrees
     * @param nodeLabel the name to identified the image
     * @param angle in degrees
     * @throws Exception if the object is missing
     */
    public void rotateNode(String nodeLabel, double angle) throws Exception {
        Node node = getNode(nodeLabel);
        if (node != null) {
            node.setRotate((node.getRotate() + angle) % 360);
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     *
     * @param nodeLabel the name to identified the image
     * @throws Exception if the object is missing
     */
    public void displayNode(String nodeLabel) throws Exception {
        Node node = getNode(nodeLabel);
        if (node != null) {
            node.setVisible(true);
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     *
     * @param nodeLabel the name to identified the image
     * @throws Exception if the object is missing
     */
    public void hideNode(String nodeLabel) throws Exception {
        Node node = getNode(nodeLabel);
        if (node != null) {
            node.setVisible(false);
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

    /**
     * Delete the image from the list {@code content} and from the Group {@code root}
     * @param imageLabel the name to identified the image
     * @throws Exception if the object is missing
     */
    public void deleteNode(String imageLabel) throws Exception {
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
    
    public void addTextButton(String buttonLabel, String text) throws Exception {
        if (isPresent(buttonLabel))
            throw new Exception("Button with the same name already exist");
        Button button = new Button(text);
        button.setId(buttonLabel);
        button.setVisible(false);
        root.getChildren().add(button);
    }

    public void addImageButton(String buttonLabel, String text, String imageFile) throws Exception {
        if (isPresent(buttonLabel))
            throw new Exception("Button with the same name already exist");
        Image image = new Image(new FileInputStream(imageFile));
        ImageView imageView = new ImageView(image);
        Button button = new Button(text,imageView);
        button.setId(buttonLabel);
        button.setVisible(false);
        root.getChildren().add(button);
    }

}