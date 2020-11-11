import java.util.ArrayList;

public class CollisionChecker {
    private final ArrayList<Entity> _entities;

    public CollisionChecker(ArrayList<Entity> entities){
        _entities = entities;
    }

    public boolean hasCollisionWith(Entity entity, Position nextPos){
        for(var e : _entities){
            if(Math.abs(nextPos.x - e.getPosition().x) <= (entity.getWidth() + e.getWidth()) / 2 &&
               Math.abs(nextPos.y - e.getPosition().y) <= (entity.getHeight() + e.getHeight()) / 2)
                return true;
        }
        return false;
    }

}
