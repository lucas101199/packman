package JeuxPacman;
import PhysicMotor.Entity;
import PhysicMotor.PhysicReaction;
import PhysicMotor.RectCollisionArea;

public class Wall extends Entity {
    private Position _pos;

    public Wall(Position pos, int height, int width){
        super(pos.x, pos.y, PhysicReaction.SOLID, null, 50);
        _pos = pos;
        setCollisionArea(new RectCollisionArea(width, height, this));
    }

    public Position getNextPosition(){
        return _pos;
    }

    @Override
    public int getX() {
        return _pos.x;
    }

    @Override
    public int getY() {
        return _pos.y;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setPosition(int x, int y) {
        _pos = new Position(x, y);
    }

}
