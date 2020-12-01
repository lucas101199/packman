package JeuxPacman;

import java.util.ArrayList;

public abstract class Character extends Entity{

    private int _speed;
    protected ArrayList<Entity> _collisions;
    protected Direction _direction;
    private static CollisionChecker _collisonChecker;
    protected final DisplayCharacter display;


    public Direction get_direction() {return _direction;}

    public static void setCollisionChecker(CollisionChecker checker){
        _collisonChecker = checker;
    }

    public Character(Position position, int height, int width, int speed, DisplayCharacter display){
        super(position, height, width);
        _speed = speed;
        this.display = display;
        _direction = Direction.NORTH;
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

    public static Direction getOppositeDir(Direction dir){
        if(dir == Direction.NORTH)
            return Direction.SOUTH;
        else if(dir == Direction.EAST)
            return Direction.WEST;
        else if(dir == Direction.SOUTH)
            return Direction.NORTH;
        else
            return Direction.EAST;
    }

    protected Position nextPos(){
        var nextPos = new Position(_position.x, _position.y);
        switch(_direction){
            case NORTH:
                nextPos.y -= _speed;
                break;
            case EAST:
                nextPos.x += _speed;
                break;
            case SOUTH:
                nextPos.y += _speed;
                break;
            default:
                nextPos.x -= _speed;
        }
        return nextPos;
    }

    protected abstract void reactAfterCollision();

}
