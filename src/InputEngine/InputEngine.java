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

    public InputEngine(GameInterface game) {
        this.events = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.game = game;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void addKey(String key) {
        keys.add(key);
        triggerAction();
    }

    public void triggerAction() {
        scene.setOnKeyPressed(e -> {
            if (keys.contains(e.getCode().getName())) {
                events.add(e);
                game.handleKey(getLastKey());
            }
        });
    }

    public String getLastKey() {
        return events.get(events.size()-1).getCode().getName();
    }
}

/*

 */
