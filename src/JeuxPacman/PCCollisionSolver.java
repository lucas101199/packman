package JeuxPacman;
import PhysicMotor.Entity;
import PhysicMotor.GPCollisionResolution;

public class PCCollisionSolver implements GPCollisionResolution{
    private Pacman _pc;

    public PCCollisionSolver(){
    }

    public void setPacman(Pacman pc){
        _pc = pc;
    }

    @Override
    public void resolve(Entity e) {
            if (e instanceof Ghost) {
                if(_pc.canEatGhost()) {
                    ((Ghost) e).die();
                    var newPos= _pc.nextPos();
                    _pc.setPosition(newPos.x, newPos.y);
                }
                else{
                    _pc.setIsDead(true);
                    _pc.setNeedRespawn(true);
                    _pc.setDeathDate(System.currentTimeMillis());
                }
            }
            else if (e instanceof ScoringBonus) {
                System.out.println("Oh a PacGum !");
                _pc.setLastEatenItem((Bonus)e);
                var pos = _pc.nextPos();
                _pc.setPosition(pos.x, pos.y);
            }
            else if(e instanceof SuperPacGum){
                System.out.println("Oh a super PacGum");
                _pc.setCanEatGhost(true);
                _pc.setLastEatenItem((Bonus)e);
                var pos = _pc.nextPos();
                _pc.setPosition(pos.x, pos.y);
            }
            else if(e instanceof  Wall)
                System.out.println("Pacman rencontre un mur !");
        }

    @Override
    public void defaultBehavior() {/*
        var pos = _pc.nextPos();
        _pc.setPosition(pos.x, pos.y);*/
    }
}
