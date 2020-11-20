package JeuxPacman;

import Outils.Position;

import java.util.ArrayList;

public abstract class Character extends Entity{

    private final int _speed;
    protected ArrayList<Entity> _collisions;
    protected Direction _direction;
    private static CollisionChecker _collisonChecker;


    public static void setCollisionChecker(CollisionChecker checker){
        _collisonChecker = checker;
    }
    public Character(Position position, int height, int width, int speed){
        super(position, height, width);
        _speed = speed;
        _direction = Direction.NORTH;
    }

    @Override
    public Position getPosition() {
        return _position;
    }

    public void move(Direction direction){
        _direction = direction;
        var nextPosition = nextPos();
        _collisions = _collisonChecker.hasCollisionsWith(this, nextPosition);
        if(_collisions.isEmpty())
            _position = nextPosition;
        else {
            reactAfterCollision();
        }
    }

    protected Position nextPos(){
        var nextPos = new Position(_position.x, _position.y);
        switch(_direction){
            case NORTH:
                nextPos.y += _speed;
                break;
            case EAST:
                nextPos.x += _speed;
                break;
            case SOUTH:
                nextPos.y -= _speed;
                break;
            default:
                nextPos.x -= _speed;
        }
        return nextPos;
    }

    protected abstract void reactAfterCollision();

}
