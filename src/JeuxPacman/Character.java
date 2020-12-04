package JeuxPacman;

import PhysicMotor.Entity;
import PhysicMotor.*;
import java.util.ArrayList;

public abstract class Character extends Entity{

    private final int _speed;
    protected ArrayList<Entity> _collisions;
    protected Direction _direction;
    protected Position _position;

    public Direction get_direction() {return _direction;}

    public Character(Position position, int height, int width, int speed, GPCollisionResolution gpCollisionRes){
        super(position.x, position.y, PhysicReaction.SOLID, gpCollisionRes, 50);
        setCollisionArea(new RectCollisionArea(width, height, this));
        _speed = speed;
        _position = position;
        _direction = Direction.NONE;
    }

    @Override
    public int getX() {
        return _position.x;
    }
    @Override
    public int getY(){ return _position.y;}

    public void setPosition(int x, int y){
        _position = new Position(x, y);
    }

    public Position getNextPosition(){
        return  nextPos();
    }

    public void move(Direction direction){
        _direction = direction;

    }

    public Position nextPos(){
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
            case WEST:
                nextPos.x -= _speed;
                break;
        }
        return nextPos;
    }

    public abstract void respawn();


}
