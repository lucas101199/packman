package GraphicEngine;

import Interfaces.GameInterface;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Scene {

    private javafx.scene.Scene scene;
    private Group root;

    private final GameInterface game;

    public Scene(String label, GameInterface game) {
        this.root = new Group();
        this.game = game;
        this.scene = new javafx.scene.Scene(this.root);
        root.setId(label);
        root.setVisible(false);
    }

    /**
     *
     * @return the name {@code label} of the {@code scene}
     */
    public String getLabel() { return root.getId(); }

    /**
     * {@code True} if we want the scene to be displayed, {@code False} otherwise
     * Always initialize to {@code False}
     * @param display Value of the display
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
     * @return the JavaFx {@code scene} associate to this class {@code Scene}
     */
    public javafx.scene.Scene getScene() {
        return scene;
    }

    /**
     * Change the size of the {@code scene}
     * @param width Width of the {@code scene}
     * @param height Height for the {@code scene}
     */
    public void setSize(double width, double height) {
        Group newroot = new Group(this.root.getChildren());
        javafx.scene.Scene newscene = new javafx.scene.Scene(newroot,width,height);
        newroot.setId(this.root.getId());
        newroot.setVisible(this.root.isVisible());
        this.root = newroot;
        this.scene = newscene;
    }

    /**
     * Add an image to the {@code scene}
     * @param imageLabel Name of the image
     * @param imageFile URL of the file
     * @throws Exception if an object has already the name {@code imageLabel}
     */
    public void addImage(String imageLabel, String imageFile) throws Exception {
        if (isPresent(imageLabel))
            throw new Exception("Object named "+imageLabel+" already exists");
        Image image = new Image(new FileInputStream(imageFile));
        ImageView imageView = new ImageView(image);
        imageView.setId(imageLabel);
        imageView.setVisible(false);
        root.getChildren().add(imageView);
    }

    /**
     * Add a button to the {@code scene}
     * @param buttonLabel Name of the button
     * @param text Text display on the button
     * @throws Exception if an object has already the name {@code buttonLabel}
     */
    public void addTextButton(String buttonLabel, String text) throws Exception {
        if (isPresent(buttonLabel))
            throw new Exception("Object named "+buttonLabel+" already exists");
        Button button = new Button(text);
        button.setId(buttonLabel);
        button.setVisible(false);
        root.getChildren().add(button);
        button.setOnAction(actionEvent -> game.handleKey(buttonLabel));
    }

    /**
     * Add a button to the {@code scene}
     * @param buttonLabel Name of the button
     * @param imageFile Image in the background of the button
     * @throws Exception if an object has already the name {@code buttonLabel}
     */
    public void addImageButton(String buttonLabel, String imageFile) throws Exception {
        addImage(buttonLabel,imageFile);
        ImageView button = getImage(buttonLabel);
        button.setOnMouseClicked(actionEvent -> game.handleKey(buttonLabel));
        button.setOnMouseEntered(mouseDragEvent -> {
            Glow glow = new Glow();
            glow.setLevel(1);
            button.setEffect(glow);
        });
        button.setOnMouseExited(mouseDragEvent -> button.setEffect(null));
    }

    /**
     * Set the position of the image in the {@code scene}
     * @param imageLabel Name of the image
     * @param x First coordinate of the image
     * @param y Second coordinate of the image
     * @throws Exception if the image with the given {@code imageLabel} is not found
     */
    public void setPositionImage(String imageLabel, double x, double y, boolean center) throws Exception {
        ImageView image = getImage(imageLabel);
        if (image != null) {
            if (center) {
                image.setX(x - (image.getImage().getWidth() / 2));
                image.setY(y - (image.getImage().getHeight() / 2));
            } else {
                image.setX(x);
                image.setY(y);
            }
        } else {
            throw new Exception("Image named "+imageLabel+" doesn't exist");
        }
    }

    /**
     * Set the position of the button in the {@code scene}
     * @param buttonLabel Name of the button
     * @param x First coordinate of the button
     * @param y Second coordinate of the button
     * @throws Exception if the button with the given {@code buttonLabel} is not found
     */
    public void setPositionButton(String buttonLabel, double x, double y) throws Exception {
        Button button = getButton(buttonLabel);
        if (button != null) {
            button.setLayoutX(x - (button.getWidth()/2));
            button.setLayoutY(y - (button.getHeight()/2));
        } else {
            throw new Exception("Button named "+buttonLabel+" doesn't exist");
        }
    }

    /**
     *
     * @param imageLabel Name of the image
     * @return the first coordinate of the image
     * @throws Exception if the image with the given {@code imageLabel} is not found
     */
    public double getImageX(String imageLabel) throws Exception {
        ImageView image = getImage(imageLabel);
        if (image != null) {
            return image.getX() + (image.getImage().getWidth()/2);
        } else {
            throw new Exception("Image named "+imageLabel+" doesn't exist");
        }
    }

    /**
     *
     * @param buttonLabel Name of the button
     * @return the first coordinate of the button
     * @throws Exception if the button with the given {@code buttonLabel} is not found
     */
    public double getButtonX(String buttonLabel) throws Exception {
        Button button = getButton(buttonLabel);
        if (button != null) {
            return button.getLayoutX() + (button.getWidth()/2);
        } else {
            throw new Exception("Button named "+buttonLabel+" doesn't exist");
        }
    }

    /**
     *
     * @param imageLabel Name of the image
     * @return the second coordinate of the image
     * @throws Exception if the image with the given {@code imageLabel} is not found
     */
    public double getImageY(String imageLabel) throws Exception {
        ImageView image = getImage(imageLabel);
        if (image != null) {
            return image.getY() + (image.getImage().getHeight()/2);
        } else {
            throw new Exception("Image named "+imageLabel+" doesn't exist");
        }
    }

    /**
     *
     * @param buttonLabel Name of the button
     * @return the second coordinate of the button
     * @throws Exception if the button with the given {@code buttonLabel} is not found
     */
    public double getButtonY(String buttonLabel) throws Exception {
        Button button = getButton(buttonLabel);
        if (button != null) {
            return button.getLayoutY() + (button.getHeight()/2);
        } else {
            throw new Exception("Button named "+buttonLabel+" doesn't exist");
        }
    }

    /**
     * Resize the image
     * @param imageLabel Name of the image
     * @param height Height of the image
     * @param width Width of the image
     * @throws Exception if the image with the given {@code imageLabel} is not found
     */
    public void resizeImage(String imageLabel, double height, double width) throws Exception {
        ImageView image = getImage(imageLabel);
        if (image != null) {
            image.setFitHeight(height);
            image.setFitWidth(width);
        } else {
            throw new Exception("Image named "+imageLabel+" doesn't exist");
        }
    }

    /**
     * Resize the button
     * @param buttonLabel Name of the button
     * @param height Height of the button
     * @param width Width of the button
     * @throws Exception if the button with the given {@code buttonLabel} is not found
     */
    public void resizeButton(String buttonLabel, double height, double width) throws Exception {
        Button button = getButton(buttonLabel);
        if (button != null) {
            button.setMinSize(width,height);
            button.setMaxSize(width,height);
        } else {
            throw new Exception("Button named "+buttonLabel+" doesn't exist");
        }
    }

    /**
     * Rotate the object {@code Node} clockwise with the given angle in degrees
     * @param nodeLabel Name of the {@code Node}
     * @param angle Angle in degrees
     * @throws Exception if the {@code Node} with the given {@code nodeLabel} is not found
     */
    public void rotateNode(String nodeLabel, double angle) throws Exception {
        Node node = getNode(nodeLabel);
        if (node != null) {
            node.setRotate((node.getRotate() + angle) % 360);
        } else {
            throw new Exception("Node named "+nodeLabel+" doesn't exist");
        }
    }

    /**
     *
     * @param nodeLabel Name of the {@code Node}
     * @throws Exception if the {@code Node} with the given {@code nodeLabel} is not found
     */
    public void displayNode(String nodeLabel) throws Exception {
        Node node = getNode(nodeLabel);
        if (node != null) {
            node.setVisible(true);
        } else {
            throw new Exception("Node named "+nodeLabel+" doesn't exist");
        }
    }

    /**
     *
     * @param nodeLabel the name to identified the image
     * @throws Exception if the {@code Node} with the given {@code nodeLabel} is not found
     */
    public void hideNode(String nodeLabel) throws Exception {
        Node node = getNode(nodeLabel);
        if (node != null) {
            node.setVisible(false);
        } else {
            throw new Exception("Node named "+nodeLabel+" doesn't exist");
        }
    }

    /**
     * Delete the {@code Node}
     * @param nodeLabel Name of the {@code Node}
     * @throws Exception if the {@code Node} with the given {@code nodeLabel} is not found
     */
    public void deleteNode(String nodeLabel) throws Exception {
        ImageView image = getImage(nodeLabel);
        if (image != null) {
            root.getChildren().remove(image);
        } else {
            throw new Exception("Node named "+nodeLabel+" doesn't exist");
        }
    }

    /**
     *
     * @param imageLabel Name of the image
     * @return the image or null if it doesn't exist
     */
    public ImageView getImage(String imageLabel) {
        Node node = getNode(imageLabel);
        if (node != null)
            return (ImageView) node;
        return null;
    }

    /**
     *
     * @param buttonLabel Name of the button
     * @return the button or null if it doesn't exist
     */
    public Button getButton(String buttonLabel) {
        Node node = getNode(buttonLabel);
        if (node != null)
            return (Button) node;
        return null;
    }

    /**
     *
     * @param label Name of the {@code Node}
     * @return the {@code Node} or null if it doesn't exist
     */
    public Node getNode(String label) {
        for (Node node : root.getChildren()) {
            if (node.getId().equals(label))
                return node;
        }
        return null;
    }

    /**
     *
     * @param Label Name of the node
     * @return {@code True} if a {@code Node} with the name {@code Label} is present
     *  in the {@code scene}
     */
    public boolean isPresent(String Label) {
        for (Node node : root.getChildren()) {
            if (node.getId().equals(Label)) return true;
        }
        return false;
    }

    public void changeImage(String imageLabel, String imageFile) throws Exception {
        if (!isPresent(imageLabel))
            throw new Exception("Object named "+imageLabel+" doesn't exist");
        Image image = new Image(new FileInputStream(imageFile));
        ImageView imageView = getImage(imageLabel);
        imageView.setImage(image);
    }

}