public class Character implements Entity{
    private int _speed;
    private Position _position;
    private int  _height;
    private int _width;
    private static CollisionChecker _collisonChecker;

    public static void setCollisionChecker(CollisionChecker checker){
        _collisonChecker = checker;
    }
    public Character(Position position, int height, int width, int speed){
        _position = position;
        _speed = speed;
        _width = width;
        _height = height;
    }
    @Override
    public Position getPosition() {
        return _position;
    }

    public void move(Direction direction){
        var nextPosition = nextPos(direction);
        if(!_collisonChecker.hasCollisionWith(this, nextPosition))
            _position = nextPosition;
        else
            System.out.println("Can't move !");
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

    @Override
    public int getHeight() {
        return _width;
    }

    @Override
    public int getWidth() {
        return _height;
    }
}
