package InputEngine;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class InputEngine {
    Scene scene;
    List<KeyEvent> events;
    List<KeyCode> keys;

    public InputEngine(Scene scene) {
        this.scene = scene;
        this.events = new ArrayList<>();
        this.keys = new ArrayList<>();
    }

    public void addKey(char key) {
        keys.add(KeyCode.getKeyCode(String.valueOf(key)));
    }

    public void triggerAction() {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                //Call the function or interface locate in the gameplay
                //this function will then call the function getLastKey
                //to get the last input of the user.
            }
        });
    }

    public String getLastKey() {
        return keys.get(keys.size()-1).getChar();
    }
}

/*

 */
