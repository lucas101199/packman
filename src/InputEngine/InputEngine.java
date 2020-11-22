package InputEngine;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class InputEngine {
    Scene scene;
    List<KeyEvent> events;
    List<String> keys;

    public InputEngine(Scene scene) {
        this.scene = scene;
        this.events = new ArrayList<>();
        this.keys = new ArrayList<>();
    }

    public void addKey(String key) {
        keys.add(key);
    }

    public void triggerAction() {
        scene.setOnKeyPressed(e -> {
            if (keys.contains(e.getCode().getChar())) {
                System.out.println("rr");
                events.add(e);
                //function here
            }
        });
    }

    public String getLastKey() {
        return events.get(keys.size()-1).getCharacter();
    }
}

/*

 */
