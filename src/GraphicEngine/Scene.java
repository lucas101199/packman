package GraphicEngine;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
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

    public String getLabel() {
        return label;
    }

    public void setDisplay(boolean display) {
        root.setVisible(display);
        isDisplay = display;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public javafx.scene.Scene getScene() {
        return scene;
    }

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

    public void resizeImage(String imageLabel, double height, double width) throws Exception {
        if (isPresent(imageLabel)) {
            for (Node node : content) {
                if (node.getId().equals(imageLabel)) {
                    node.resize(width, height);
                }
            }
        } else {
            throw new Exception("no node with the label in this scene");
        }
    }

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

    public boolean isPresent(String Label) {
        for (Node node : content) {
            if (node.getId().equals(Label)) return true;
        }
        return false;
    }
}