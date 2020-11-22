package JeuxPacman;

import GraphicEngine.GraphicEngine;
import GraphicEngine.GameInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class Game implements GameInterface {

    private ArrayList<Entity> _items;
    private ArrayList<Ghost> _ennemies;
    private  Pacman _pc;
    private DisplayPacman _pcDisplay;
    private int _score;
    private final double speed = 10;
    private GraphicEngine _graphic;
    private Scanner keyboard = new Scanner(System.in);

    public void init() {
        try {
            _graphic.addScene("maze");
            _graphic.setSizeScene("maze",544,600);
            _graphic.displayScene("maze");
            _graphic.addImage("maze","map","./src/Images/Map/map.png");
            _graphic.resizeImage("maze","map",600,544);
            _graphic.setPositionImage("maze","map",272,300);
            _graphic.displayImage("maze","map");
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
            _pcDisplay.displayPacmanStart(_pc.getPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (_pc.get_direction() == null) {
            try {
                char c = keyboard.next(".").charAt(0);
                switch (c) {
                    case 'z':
                        _pc._direction = Direction.NORTH;
                        _pcDisplay.displayPacMan(Direction.NORTH, _pc.getPosition());
                        break;
                    case 'd':
                        _pc._direction = Direction.EAST;
                        _pcDisplay.displayPacMan(Direction.EAST, _pc.getPosition());
                        break;
                    case 's':
                        _pc._direction = Direction.SOUTH;
                        _pcDisplay.displayPacMan(Direction.SOUTH, _pc.getPosition());
                        break;
                    case 'q':
                        _pc._direction = Direction.WEST;
                        _pcDisplay.displayPacMan(Direction.WEST, _pc.getPosition());
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            _pc.move();
            try {
                _pcDisplay.displayPacMan(_pc.get_direction(), _pc.getPosition());
            } catch (Exception e) {
                e.printStackTrace();
            }
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
