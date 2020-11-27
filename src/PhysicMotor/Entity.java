import java.util.ArrayList;

public class Entity {
    protected int _x, _y;
    protected  CollisionArea _collisionArea;
    protected PhysicReaction _physic;
    public GPCollisionResolution _gpResolution;
    private ArrayList<Force> _forces;
    private int _mass;

    protected Entity(int x, int y, PhysicReaction physic, GPCollisionResolution gpResol, int mass){
        _x = x;
        _y = y;
        _collisionArea = new RectCollisionArea(0,0,this);
        _physic = physic;
        _gpResolution = gpResol;
        _mass = mass;
        _forces = new ArrayList<>();
    }

    public void addForce(Force f){
        _forces.add(f);
    }

    public ArrayList<Force> getForces(){
        return _forces;
    }

    public int getMass(){
        return _mass;
    }

    public void setCollisionArea(CollisionArea c){
        _collisionArea = c;
    }

    public CollisionArea getCollisionArea(){
        return _collisionArea;
    }

    public PhysicReaction getPhysic(){
        return _physic;
    }

    public int getX(){
        return _x;
    }

    public int getY(){
        return _y;
    }

    public void setPosition(int x, int y){
        _x = x;
        _y = y;
    }
}