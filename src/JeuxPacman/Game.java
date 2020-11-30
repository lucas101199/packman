package JeuxPacman;

import GraphicEngine.GraphicEngine;
import InputEngine.InputEngine;
import Interfaces.GameInterface;

import java.util.ArrayList;

public class Game implements GameInterface {

    private ArrayList<Entity> _items;
    private ArrayList<Ghost> _ennemies;
    private  Pacman _pc;
    private DisplayPacman _pcDisplay;
    private GraphicEngine _graphic;
    private CollisionChecker checker;
    private DisplayClyde _clydeDisplay;
    private String lastKey;
    private int keyTime;
    private boolean gameStart;
    private DisplayBlinky _blinkyDisplay;
    private DisplayPinky _pinkyDisplay;

    public void init() {
        try {
            _graphic.addScene("maze");
            _graphic.setSizeScene("maze",544,600);
            _graphic.displayScene("maze");
            _graphic.addImage("maze","map","./src/Images/Map/map.png");
            _graphic.resizeImage("maze","map",600,544);
            _graphic.setPositionImage("maze","map",272,300, true);
            _graphic.displayObject("maze","map");
            _ennemies = new ArrayList<>();
            _items = new ArrayList<>();
            _pc = new Pacman(new Position(272,454), 29,29,2);
            _pcDisplay = new DisplayPacman(_graphic,"maze");
            _ennemies.add(new Ghost(new Position(272,224),29,29,2));
            _ennemies.get(0)._direction = Direction.SOUTH;
            _clydeDisplay = new DisplayClyde(_graphic,"maze");
            //_ennemies.add(new Ghost(new Position(240,224),29,29,2));
            //_ennemies.get(1)._direction = Direction.SOUTH;
            //_blinkyDisplay = new DisplayBlinky(_graphic,"maze");
            //_ennemies.add(new Ghost(new Position(304,224),29,29,2));
            //_ennemies.get(2)._direction = Direction.SOUTH;
            //_pinkyDisplay = new DisplayPinky(_graphic,"maze");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            _pcDisplay.displayPacmanStart(_pc.getPosition());
            _clydeDisplay.displayClyde(_ennemies.get(0)._direction,_ennemies.get(0)._position);
            //_blinkyDisplay.displayBlinky(_ennemies.get(1)._direction,_ennemies.get(1)._position);
            //_pinkyDisplay.displayPinky(_ennemies.get(2)._direction,_ennemies.get(2)._position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputEngine _input = new InputEngine(this);
        _input.addKey("Z");
        _input.addKey("Q");
        _input.addKey("S");
        _input.addKey("D");
        _input.setScene(_graphic.getScene("maze").getScene());
        _items.add(new Wall(new Position(80.5,69.5),45,65));   // Rectangle haut gauche 1

        _items.add(new Wall(new Position(186, 69.5),45,84));   // Rectangle haut gauche 2

        _items.add(new Wall(new Position(360, 69.5),45,84));   	// Rectangle haut droite 1

        _items.add(new Wall(new Position(465.5, 69.5),45,65));  // Rectangle haut droite 2

        _items.add(new Wall(new Position(80.5, 136.5),27,65)); 	// Rectangle long horizontal haut gauche

        _items.add(new Wall(new Position(465.5, 136.5),27,65)); // Rectangle long horizontal haut droite

        _items.add(new Wall(new Position(185.5, 425.5),27,83)); // Rectangle long horizontal bas gauche

        _items.add(new Wall(new Position(360.5, 425.5),27,83)); // Rectangle long horizontal bas droite


        _items.add(new Wall(new Position(157.5, 339),84,27));   // Rectangle long vertical gauche

        _items.add(new Wall(new Position(388.5, 339),84,27));   // Rectangle long vertical droite


        _items.add(new Wall(new Position(273, 135),24,142));   	// T central 1
        _items.add(new Wall(new Position(273, 178.5),61,30));   // T central 1

        _items.add(new Wall(new Position(273, 368),26,142));   	// T central 2
        _items.add(new Wall(new Position(273, 410.5),57,30));   // T central 2

        _items.add(new Wall(new Position(273, 483.5),27,140));  // T central 3
        _items.add(new Wall(new Position(273, 526),56,30));   	// T central 3


        _items.add(new Wall(new Position(157.5, 194.5),143,27));// T haut gauche
        _items.add(new Wall(new Position(199.5, 193.5),31,55)); // T haut gauche

        _items.add(new Wall(new Position(388.5, 194.5),143,27));// T haut droite
        _items.add(new Wall(new Position(346.5, 193.5),31,55)); // T haut droite

        _items.add(new Wall(new Position(137, 541),26,180));   	// T bas gauche
        _items.add(new Wall(new Position(158, 498.5),57,28));   // T bas gauche

        _items.add(new Wall(new Position(409, 541),26,180));   	// T bas droite
        _items.add(new Wall(new Position(388, 498.5),57,28));   // T bas droite


        _items.add(new Wall(new Position(273, 282),84,142));   	// Foyer fantômes


        _items.add(new Wall(new Position(54, 223.5),85,115));   	// Grand carre haut gauche

        _items.add(new Wall(new Position(490.5, 223.5),85,114));// Grand carre haut droit

        _items.add(new Wall(new Position(55, 339),84,114));   	// Grand carre bas gauche

        _items.add(new Wall(new Position(491, 339),84,114));  // Grand carre bas droite


        _items.add(new Wall(new Position(273, 8.5),15,510));   	// Bord haut

        _items.add(new Wall(new Position(273, 592.5),15,512));  // Bord bas


        _items.add(new Wall(new Position(9, 90.5),179,16));   	// Bord haut gauche

        _items.add(new Wall(new Position(536.5, 90.5),179,15)); // Bord haut droite

        _items.add(new Wall(new Position(8.5, 491),218,15));   	// Bord bas gauche

        _items.add(new Wall(new Position(537, 491),218,14));   	// Bord bas droite


        _items.add(new Wall(new Position(273, 54.5),75,28));   	// Tige haut


        _items.add(new Wall(new Position(35.5, 483),28,37));   	// Tige bas gauche

        _items.add(new Wall(new Position(510.5, 483.5),27,37)); // Tige bas droite


        _items.add(new Wall(new Position(80, 425),26,66));   	// L inversé gauche
        _items.add(new Wall(new Position(99, 468),58,28));   	// L inversé gauche

        _items.add(new Wall(new Position(466, 425.5),27,66));   // L inversé droite
        _items.add(new Wall(new Position(447, 468.5),57,28));   // L inversé droite
        ArrayList<Entity> entities = new ArrayList<>(_items);
        entities.addAll(_ennemies);
        checker = new CollisionChecker(entities);
        Character.setCollisionChecker(checker);
        gameStart = false;
    }

    public void update() {
        if (_pc._direction == null && !gameStart)
            return;
        if (_pc.isDead) {
            try {
                _pcDisplay.displayPacManDeath(_pc.getPosition());
                _pc.isDead = false;
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (_pc.needRespawn) {
            try {
                if (System.currentTimeMillis() - _pc.deathDate < 3900)
                    return;
                _pc.needRespawn = false;
                _pc.respawn();
                for (Ghost g : _ennemies)
                    g.respawn();
                _pcDisplay.displayPacmanStart(_pc.getPosition());
                _clydeDisplay.displayClyde(Direction.SOUTH,_ennemies.get(0)._position);
                //_pinkyDisplay.displayPinky(Direction.SOUTH,_ennemies.get(2)._position);
                //_blinkyDisplay.displayBlinky(Direction.SOUTH,_ennemies.get(1)._position);
                gameStart = false;
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        handleLastKey();
        _pc.move(_pc._direction);
        _pc.checkPosition();
        ArrayList<Position> oldpos = new ArrayList<>();
        for (Ghost g : _ennemies) {
            oldpos.add(g._position);
            g.move(g._direction);
        }
        try {
            _pcDisplay.displayPacMan(_pc.get_direction(), _pc.getPosition());
            if (oldpos.get(0) != _ennemies.get(0)._position)
                _clydeDisplay.displayClyde(_ennemies.get(0)._direction,_ennemies.get(0)._position);
            //if (oldpos.get(1) != _ennemies.get(1)._position)
            //    _blinkyDisplay.displayBlinky(_ennemies.get(1)._direction,_ennemies.get(1)._position);
            //if (oldpos.get(2) != _ennemies.get(2)._position)
            //    _pinkyDisplay.displayPinky(_ennemies.get(2)._direction,_ennemies.get(2)._position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getSpeed() {
        return 42;
    }

    public void set_graphic(GraphicEngine _graphic) {
        this._graphic = _graphic;
    }

    @Override
    public void handleKey(String key) {
        lastKey = key;
        keyTime = 14;
        if (!gameStart && (key.equals("D") || key.equals("Q")))
            gameStart = true;
    }

    private void handleLastKey() {
        if (keyTime > 0) {
            keyTime--;
            Direction olddir = _pc._direction;
            ArrayList<Entity> collideWith;
            try {
                switch (this.lastKey) {
                    case "Z":
                        _pc._direction = Direction.NORTH;
                        break;
                    case "D":
                        _pc._direction = Direction.EAST;
                        break;
                    case "S":
                        _pc._direction = Direction.SOUTH;
                        break;
                    case "Q":
                        _pc._direction = Direction.WEST;
                        break;
                    default:
                        break;
                }
                collideWith = checker.hasCollisionsWith(_pc,_pc.nextPos());
                for (Entity e : collideWith) {
                    if (e instanceof Wall) {
                        _pc._direction = olddir;
                        break;
                    }
                }
                if (_pc._direction != olddir) {
                    _pcDisplay.displayPacMan(_pc._direction, _pc.getPosition());
                    keyTime = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
