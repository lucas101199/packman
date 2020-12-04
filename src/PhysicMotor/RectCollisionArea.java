package PhysicMotor;

import java.util.ArrayList;

public class RectCollisionArea extends CollisionArea {
    private int _width;
    private int _height;
    private  Entity _entity;

    public RectCollisionArea(int width, int height, Entity entity){
        _height = height;
        _width = width;
        _entity = entity;
    }

    @Override
    protected ArrayList<Point> getCharacteristicPoints() {
        var characteristicP = new ArrayList<Point>();
        var nextPos = _entity.getNextPosition();
        characteristicP.add(new Point(nextPos.x + _width/2, nextPos.y + _height/2));
        characteristicP.add(new Point(nextPos.x + _width/2, nextPos.y - _height/2));
        characteristicP.add(new Point(nextPos.x - _width/2, nextPos.y + _height/2));

        return characteristicP;
    }

    @Override
    protected boolean pointsAreInside(ArrayList<Point> points) {
        for(var pt : points)
        {
            if(isInsideRect(pt))
                return true;
        }
        return false;
    }

    private boolean isInsideRect(Point pt){
        var nextPos = _entity.getNextPosition();
        return Math.abs(pt.x - nextPos.x) <= _width/2 && Math.abs(pt.y - nextPos.y) <= _height/2;
    }
}
