package JeuxPacman;

public abstract class Entity {

    protected Position _position;
    private final Position _initialPosition;
    private final int _height;
    private final int _width;

    public Entity(Position pos, int height, int width){
        _position = pos;
        _initialPosition = new Position(pos.x, pos.y);
        _height = height;
        _width = width;
    }

    public Position get_initialPosition() {
        return _initialPosition;
    }

    public abstract boolean isActive();

    public void respawn(){
        _position = _initialPosition;
    }

    public Position getPosition(){
        return _position;
    }

    int getHeight(){
        return _height;
    }

    int getWidth(){
        return _width;
    }

    public void restart(){
        respawn();
    }

}

