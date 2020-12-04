package JeuxPacman;

import PhysicMotor.Entity;
import PhysicMotor.GPCollisionResolution;

public class GhostCollisionSolver implements GPCollisionResolution {
    private  Ghost _ghost;

    @Override
    public void resolve(Entity e) {
        if( e instanceof Pacman){
            var pc = (Pacman)e;
            if(!pc.canEatGhost() || _ghost.get_direction() != _ghost.getOppositeDir(pc._direction)) {
                var pos =  _ghost.nextPos();
                _ghost.setPosition(pos.x, pos.y);
            }
            else if(_ghost.get_direction() == _ghost.getOppositeDir(pc._direction))
                _ghost.die();
            System.out.println("Pacman Collision");
        }
        else if(!(e instanceof Wall)) {
            var pos = _ghost.nextPos();
            _ghost.setPosition(pos.x, pos.y);
        }
        else
            System.out.println("ghost encounter Mur !");
    }

    @Override
    public void defaultBehavior() {
        var pos = _ghost.nextPos();
        _ghost.setPosition(pos.x, pos.y);
    }

    public void setGhost(Ghost g){
        _ghost = g;
    }
}
