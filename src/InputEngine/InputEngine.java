package InputEngine;

import Interfaces.GameInterface;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class InputEngine {

    GameInterface game;

    Scene scene;
    List<KeyEvent> events;
    List<KeyCode> keys;

    public InputEngine(Scene scene, GameInterface game) {
        this.scene = scene;
        this.events = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.game = game;
    }

    public void addKey(char key) {
        keys.add(KeyCode.getKeyCode(String.valueOf(key)));
    }

    public void triggerAction() {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                if (/*keys.contains(e.getCode())*/true) {
                    events.add(e);
                    System.out.println("triggerAction");
                    game.handleKey(getLastKey());
                }
            }
        });
    }

    public String getLastKey() {
        return events.get(keys.size()-1).getCharacter();
    }
}

/*

 */
