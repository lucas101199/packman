package JeuxPacman;
import  PhysicMotor.Entity;
import PhysicMotor.*;

public class Bonus extends Entity{
    int _score;
    boolean _isActive;
    int _duration;
    int _x, _y;

    protected Bonus(Position pos, int height, int width, int duration, int score) {
        super(pos.x, pos.y, PhysicReaction.SOLID, null, 5);
        setCollisionArea(new RectCollisionArea(width, height, this));

        _isActive = true;
        _duration = duration;
        _score = score;
    }

    public int getScore(){
        return _score;
    }
    
    public void consume(){
        _isActive = false;
        _duration--;
    }

    public boolean isFullyConsumed(){
        return _duration == 0;
    }

    @Override
    public boolean isActive() {
        return _isActive;
    }

    @Override
    public Position getNextPosition() {
        return new Position(_x, _y);
    }

    @Override
    public int getX() {
        return _x;
    }

    @Override
    public int getY() {
        return _y;
    }

    @Override
    public void setPosition(int x, int y) {
        _x = x;
        _y = y;
    }
}
