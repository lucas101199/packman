package PhysicMotor;

import java.util.ArrayList;

public abstract class CollisionArea {

    public  boolean hasCollision(Entity entityInvolved){
        var points = getCharacteristicPoints();
        return entityInvolved.getCollisionArea().pointsAreInside(points);
    }

    protected abstract ArrayList<Point> getCharacteristicPoints();
    protected abstract boolean pointsAreInside(ArrayList<Point> points);

}