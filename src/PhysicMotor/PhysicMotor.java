package PhysicMotor;

import java.util.ArrayList;
import java.util.HashMap;

public class PhysicMotor {
    private ArrayList<Entity> _entities;
    private ArrayList<Entity> _moveableEntities;
    private HashMap<Entity, Point> _previousEntitiesPos;
    private HashMap<Entity, double[]> _accelerationEntities;
    private  HashMap<Entity, double[]> _speedEntities;
    private Entity _currentEntity;

    public PhysicMotor(){
        _entities = new ArrayList<>();
        _moveableEntities = new ArrayList<>();
        _previousEntitiesPos = new HashMap<>();
        _accelerationEntities = new HashMap<>();
        _speedEntities = new HashMap<>();
    }

    public void registerEntity(Entity e){
        _entities.add(e);
    }

    public void registerMoveableEntity(Entity e){
        _moveableEntities.add(e);
        _entities.add(e);
        _previousEntitiesPos.put(e, new Point(e.getX(), e.getY()));
        _accelerationEntities.put(e, new double[]{.0, .0});
        _speedEntities.put(e, new double[]{.0, .0});
    }

    public void update(){
       for(var movable : _moveableEntities){
           _currentEntity = movable;
           applyForce();
           handleCollisions();
           _previousEntitiesPos.put(_currentEntity, new Point(_currentEntity.getX(), _currentEntity.getY()));
       }
    }

    private void applyForce(){
        var forces = _currentEntity.getForces();
        var newXAcc = _accelerationEntities.get(_currentEntity)[0];
        var newYAcc = _accelerationEntities.get(_currentEntity)[1];
        for(var force : forces){
            newXAcc += force._xComponent;
            newYAcc += force._yComponent;
        }
        newXAcc /= _currentEntity.getMass();
        newYAcc /= _currentEntity.getMass();
        System.out.println("Xacc :" + newXAcc + ";" + "Yacc :" + newYAcc);
        _accelerationEntities.put(_currentEntity, new double[]{newXAcc, newYAcc});
        var speed = _speedEntities.get(_currentEntity);
        var newXSpeed = speed[0] + _accelerationEntities.get(_currentEntity)[0];
        var newYSpeed = speed[1] + _accelerationEntities.get(_currentEntity)[1];
        _speedEntities.put(_currentEntity, new double[]{newXSpeed , newYSpeed});
        _currentEntity.setPosition(_currentEntity.getX() + (int)newXSpeed, _currentEntity.getY() + (int)newYSpeed);
    }

    private void handleCollisions(){
        for(var e : _entities) {
            if (e != _currentEntity && _currentEntity.getCollisionArea().hasCollision(e)){
                handlePhysicCollisionResolution(e);
                if(_currentEntity._gpResolution != null)
                    _currentEntity._gpResolution.resolve(e);
            }
        }
    }

    private  void handlePhysicCollisionResolution(Entity entityInvolved){
        if(_currentEntity.getPhysic() == PhysicReaction.SOLID && entityInvolved.getPhysic() == PhysicReaction.SOLID) {
            System.out.println("Solid collision !");
            if (currentEntityIsInMotion()) {
                var previousPos = _previousEntitiesPos.get(_currentEntity);
                System.out.println("Return  to pos (" + previousPos.x + ";" + previousPos.y + ")" );
                _currentEntity.setPosition(previousPos.x, previousPos.y);
            }
        }
    }

    private boolean currentEntityIsInMotion(){
        var previousPos = _previousEntitiesPos.get(_currentEntity);
        return _currentEntity.getX() != previousPos.x || _currentEntity.getY() != previousPos.y;
    }
}