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
        this.scene = null;
        this.events = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.game = game;
    }

    /**
     * Set the new scene that is currently display by the GraphicEngine
     * @param scene Scene display
     */
    public void setScene(Scene scene){
        this.scene = scene;
        triggerAction();
    }

    /**
     * Add a new key from de keybord to survey
     * @param key Key from the keybord
     */
    public void addKey(String key) {
        keys.add(key);
        triggerAction();
    }

    public void triggerAction() {
        if (scene != null) {
            scene.setOnKeyPressed(e -> {
                if (keys.contains(e.getCode().getName())) {
                    events.add(e);
                    game.handleKey(getLastKey());
                }
            });
        }
    }

    public String getLastKey() {
        return events.get(events.size()-1).getCode().getName();
    }
}

/*

 */
