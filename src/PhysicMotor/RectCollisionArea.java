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
        characteristicP.add(new Point(_entity.getX() + _width/2, _entity.getY() + _height/2));
        characteristicP.add(new Point(_entity.getX() + _width/2, _entity.getY() - _height/2));
        characteristicP.add(new Point(_entity.getX() - _width/2, _entity.getY() + _height/2));

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
        return Math.abs(pt.x - _entity.getX()) <= _width && Math.abs(pt.y - _entity.getY()) <= _height;
    }
}
