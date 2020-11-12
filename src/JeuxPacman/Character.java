import java.util.ArrayList;

public abstract class Character extends Entity{
    private int _speed;
    protected ArrayList<Entity> _collisions;
    private static CollisionChecker _collisonChecker;

    public static void setCollisionChecker(CollisionChecker checker){
        _collisonChecker = checker;
    }
    public Character(Position position, int height, int width, int speed){
        super(position, height, width);
        _speed = speed;
    }

    @Override
    public Position getPosition() {
        return _position;
    }

    public void move(Direction direction){
        var nextPosition = nextPos(direction);
        _collisions = _collisonChecker.hasCollisionsWith(this, nextPosition);
        if(_collisions.isEmpty())
            _position = nextPosition;
        else {
            System.out.println("Can't move !");
            reactAfterCollision();
        }
    }

    public Position nextPos(Direction direction){
        var nextPos = new Position(_position.x, _position.y);
        switch(direction){
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
