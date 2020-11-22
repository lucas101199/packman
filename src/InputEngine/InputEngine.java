package InputEngine;

import Interfaces.GameInterface;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class InputEngine {

    GameInterface game;

    Scene scene;
    List<KeyEvent> events;
    List<String> keys;

    public InputEngine(Scene scene, GameInterface game) {
        this.scene = scene;
        this.events = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.game = game;
    }

    public void addKey(String key) {
        keys.add(key);
    }

    public void triggerAction() {
        scene.setOnKeyPressed(e -> {
            if (keys.contains(e.getCode().getChar())) {
                events.add(e);
                game.handleKey(getLastKey());
            }
        });
    }

    public String getLastKey() {
        return events.get(events.size()-1).getCode().getChar();
    }
}

/*

 */
