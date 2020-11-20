package JeuxPacman;

import Outils.Position;

import java.util.ArrayList;

public class CollisionChecker {
    private final ArrayList<Entity> _entities;

    public CollisionChecker(ArrayList<Entity> entities){
        _entities = entities;
    }

    public ArrayList<Entity> hasCollisionsWith(Entity entity, Position nextPos){
        var entitiesInvolved = new ArrayList<Entity>();
        for(var e : _entities){
            if(Math.abs(nextPos.x - e.getPosition().x) <= (entity.getWidth() + e.getWidth()) / 2 &&
               Math.abs(nextPos.y - e.getPosition().y) <= (entity.getHeight() + e.getHeight()) / 2 && e != entity)
                entitiesInvolved.add(e);
        }
        return entitiesInvolved;
    }

}
