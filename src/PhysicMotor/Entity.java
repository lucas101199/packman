public class Entity {
    protected int _x, _y;
    protected  CollisionArea _collisionArea;

    protected Entity(int x, int y){
        _x = x;
        _y = y;
        _collisionArea = new RectCollisionArea(0,0,this);
    }

    public void setCollisionArea(CollisionArea c){
        _collisionArea = c;
    }

    public CollisionArea getCollisionArea(){
        return _collisionArea;
    }

    public int getX(){
        return _x;
    }

    public int getY(){
        return _y;
    }
}