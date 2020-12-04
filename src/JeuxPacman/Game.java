package JeuxPacman;

import GraphicEngine.GraphicEngine;
import InputEngine.InputEngine;
import Interfaces.GameInterface;
import PhysicMotor.Entity;
import PhysicMotor.PhysicMotor;

import java.util.ArrayList;

public class Game implements GameInterface {

    private ArrayList<Entity> _items;
    private ArrayList<Ghost> _ennemies;
    private  Pacman _pc;
    private DisplayPacman _pcDisplay;
    private int _score;
    private final double speed = 10;
    private GraphicEngine _graphic;
    private InputEngine _input;
    private CollisionChecker checker;
    private DisplayClyde _clydeDisplay;
    private PhysicMotor _physicMotor;
    public void init() {
        try {
            _graphic.addScene("maze");
            _graphic.setSizeScene("maze",544,600);
            _graphic.displayScene("maze");
            _graphic.addImage("maze","map","./src/Images/Map/map.png");
            _graphic.resizeImage("maze","map",600,544);
            _graphic.setPositionImage("maze","map",272,300);
            _graphic.displayObject("maze","map");
            _ennemies = new ArrayList<>();
            _items = new ArrayList<>();
            _physicMotor = new PhysicMotor();
            var collisionS = new PCCollisionSolver();
            _pc = new Pacman(new Position(272,454), 27,31,1, collisionS);
            System.out.println(_pc._direction);
            collisionS.setPacman(_pc);
            _physicMotor.registerMoveableEntity(_pc);

            _pcDisplay = new DisplayPacman(_graphic,"maze");
            var collisionGhos = new GhostCollisionSolver();
            var ghost = new Ghost(new Position(272,225),27,31,2, collisionGhos);
            collisionGhos.setGhost( ghost);
            _ennemies.add(ghost);
            _physicMotor.registerMoveableEntity(ghost);
            _ennemies.get(0)._direction = Direction.SOUTH;
            _clydeDisplay = new DisplayClyde(_graphic,"maze");
            _score = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            _pcDisplay.displayPacmanStart(new Position(_pc.getX(), _pc.getY()));
            _clydeDisplay.displayClyde(_ennemies.get(0)._direction,_ennemies.get(0)._position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        _input = new InputEngine(_graphic.getScene("maze").getScene(),this);
        _input.addKey("Z");
        _input.addKey("Q");
        _input.addKey("S");
        _input.addKey("D");
        _input.triggerAction();
        _items.add(new Wall(new Position(272,5),10,544));   //Top Wall
        _items.add(new Wall(new Position(272,595),12,544)); //Bottom Wall
        _items.add(new Wall(new Position(273,43),86,26));   //TopMiddle Wall
        _items.add(new Wall(new Position(80,68),36,60));    //TopLeft First Bloc
        _items.add(new Wall(new Position(8,100),200,16));   //LeftTop Wall
        _items.add(new Wall(new Position(8,482),236,16));   //LeftBottom Wall
        _items.add(new Wall(new Position(186,68),36,78));   //TopLeft Second Bloc
        _items.add(new Wall(new Position(360,68),36,78));   //TopRight Second Bloc
        _items.add(new Wall(new Position(467,68),36,60));   //TopRight First Bloc
        _items.add(new Wall(new Position(538,100),200,14)); //RightTop Wall
        _items.add(new Wall(new Position(538,482),200,14)); //RightBottom Wall
        _items.add(new Wall(new Position(80,136),16,60));   //LeftTop Bloc
        _items.add(new Wall(new Position(467,136),16,60));  //RightTop Bloc
        _items.add(new Wall(new Position(273,136),16,136)); //Top T Bloc part1
        _items.add(new Wall(new Position(273,166),70,26));  //Top T Bloc part2
        _items.add(new Wall(new Position(158,194),132,22)); //TopLeft T Bloc part1
        _items.add(new Wall(new Position(186,192),18,78));  //TopLeft T Bloc part2
        _items.add(new Wall(new Position(388,194),132,22)); //TopRight T Bloc part1
        _items.add(new Wall(new Position(360,192),18,78));  //TopRight T Bloc part2
        _items.add(new Wall(new Position(55,222),76,110));  //LeftTop Tunnel
        _items.add(new Wall(new Position(492,222),76,110)); //RightTop Tunnel
        _items.add(new Wall(new Position(55,338),76,110));  //LeftBottom Tunnel
        _items.add(new Wall(new Position(492,338),76,110)); //RightBottom Tunnel
        _items.add(new Wall(new Position(273,280),76,136)); //Center
        _items.add(new Wall(new Position(158,338),76,22));  //Left Bloc
        _items.add(new Wall(new Position(388,338),76,22));  //Right Bloc
        _items.add(new Wall(new Position(273,368),16,136)); //BottomTop T Bloc part1
        _items.add(new Wall(new Position(273,398),70,26));  //BottomTop T Bloc part2
        _items.add(new Wall(new Position(273,483),16,136)); //BottomBottom T Bloc part1
        _items.add(new Wall(new Position(273,513),70,26));  //BottomBottom T Bloc part2
        _items.add(new Wall(new Position(26,482),18,52));   //LeftBottomMiddle Wall
        _items.add(new Wall(new Position(520,482),18,52));  //RightBottomMiddle Wall
        _items.add(new Wall(new Position(186,425),16,78));  //LeftBottom Bloc
        _items.add(new Wall(new Position(360,425),16,78));  //RightBottom Bloc
        _items.add(new Wall(new Position(80,425),16,60));   //BottomLeft L part1
        _items.add(new Wall(new Position(99,455),72,22));   //BottomLeft L part2
        _items.add(new Wall(new Position(467,425),16,60));  //BottomRight L part1
        _items.add(new Wall(new Position(448,455),72,22));  //BottomRight L part2
        _items.add(new Wall(new Position(138,541),14,174)); //BottomLeft InverseT part1
        _items.add(new Wall(new Position(158,511),72,22));  //BottomLeft InverseT part2
        _items.add(new Wall(new Position(408,541),14,174)); //BottomRight InverseT part1
        _items.add(new Wall(new Position(388,511),72,22));  //BottomRight InverseT part2

        for(int i = 2; i < _items.size(); i++)
            _physicMotor.registerEntity(_items.get(i));

    }

    public void update() {
        System.out.println("Pc pos : " + _pc.getX() + ";" + _pc.getY());
        if (_pc.isDead()) {
            try {
                _pcDisplay.displayPacManDeath(new Position(_pc.getX(), _pc.getY()));
                _pc.setIsDead(false);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (_pc.needRespawn()) {
            try {
                if (System.currentTimeMillis() - _pc.getDeathDate() < 3900)
                    return;
                _pc.setNeedRespawn(false);
                _pc.respawn();
                _pcDisplay.displayPacmanStart(new Position(_pc.getX(), _pc.getY()));
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //_pc.move();
        _pc.checkPosition();
        try {
            _pcDisplay.displayPacMan(_pc.get_direction(), new Position(_pc.getX(), _pc.getY()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Direction = " + _pc._direction);
        _physicMotor.update();
    }

    @Override
    public double getSpeed() {
        return 0;
    }

    public void set_graphic(GraphicEngine _graphic) {
        this._graphic = _graphic;
    }

    @Override
    public void handleKey(String key) {
        Direction olddir = _pc._direction;
        ArrayList<Entity> collideWith;
        try {
            switch (key) {
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

            _pcDisplay.displayPacMan(_pc._direction, new Position(_pc.getX(), _pc.getY()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
