package PhysicMotor;

import java.util.ArrayList;

public abstract class CollisionArea {

    public  boolean hasCollision(Entity entityInvolved){
        var points = entityInvolved.getCollisionArea().getCharacteristicPoints();
        return pointsAreInside(points) || entityInvolved.getCollisionArea().pointsAreInside(getCharacteristicPoints());
    }

    protected abstract ArrayList<Point> getCharacteristicPoints();
    protected abstract boolean pointsAreInside(ArrayList<Point> points);

}