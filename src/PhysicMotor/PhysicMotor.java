import java.util.ArrayList;

public class PhysicMotor {
    private ArrayList<Entity> _entities;
    private ArrayList<Entity> _moveableEntities;

    public PhysicMotor(){
        _entities = new ArrayList<Entity>();
        _moveableEntities = new ArrayList<Entity>();
    }

    public void registerEntity(Entity e){
        _entities.add(e);
    }

    public void registerMoveableEntity(Entity e){
        _moveableEntities.add(e);
        _entities.add(e);
    }

    public void update(){
       for(var moveable : _moveableEntities){
           for(var e : _entities) {
               if (e != moveable && moveable.getCollisionArea().hasCollision(e))
                   System.out.println("Collision !");
           }
       }
       System.out.println("No Collision !");
    }
}