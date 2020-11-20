package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.ArrayList;

public class Game implements Interfaces.Game {

    private ArrayList<Entity> _items;
    private ArrayList<Ghost> _ennemies;
    private  Pacman _pc;
    private DisplayPacman _pcDisplay;
    private int _score;
    private final double speed = 10;
    private GraphicEngine _graphic;

    public void init() {
        try {
            _graphic.addScene("maze");
            _graphic.setSizeScene("maze",544,600);
            _graphic.displayScene("maze");
            _graphic.addImage("maze","map","./src/Images/Map/map.png");
            _graphic.resizeImage("maze","map",600,544);
            _graphic.setPositionImage("maze","map",272,300);
            _graphic.displayImage("maze","map");
            _items = new ArrayList<>();
            _ennemies = new ArrayList<>();
            _pc = new Pacman(new Position(272,454), 31,27,10);
            _pcDisplay = new DisplayPacman(_graphic,"maze");
            _score = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            _pcDisplay.displayPacMan("Pacman_left",_pc.getPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        _pc.move();
        try {
            _pcDisplay.displayPacMan("Pacman_left",_pc.getPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getSpeed() {
        return 0;
    }

    public void set_graphic(GraphicEngine _graphic) {
        this._graphic = _graphic;
    }
}
