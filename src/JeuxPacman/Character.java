package JeuxPacman;

import java.util.ArrayList;
import java.util.Random;

public abstract class Character extends Entity{

    private final int _speed;
    protected ArrayList<Entity> _collisions;
    protected Direction _direction;
    private static CollisionChecker _collisonChecker;
    protected final DisplayCharacter display;
    private final Random random = new Random();


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

    public Direction getRandomDir() {
        int i = random.nextInt(4);
        return switch (i) {
            case 0 -> Direction.SOUTH;
            case 1 -> Direction.WEST;
            case 2 -> Direction.EAST;
            default -> Direction.NORTH;
        };
    }

    protected Position nextPos(){
        var nextPos = new Position(_position.x, _position.y);
        switch (_direction) {
            case NORTH -> nextPos.y -= _speed;
            case EAST -> nextPos.x += _speed;
            case SOUTH -> nextPos.y += _speed;
            default -> nextPos.x -= _speed;
        }
        return nextPos;
    }

    protected abstract void reactAfterCollision();

}
