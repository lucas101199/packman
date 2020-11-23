package JeuxPacman;

import javafx.geometry.Pos;

import java.util.ArrayList;

public class Pacman extends Character{

    private Bonus _lastEatenItem;
    private  boolean _canEatGhost;
    CollisionChecker checker;

    public void setChecker(CollisionChecker checker) {
        this.checker = checker;
    }

    public Pacman(Position position, int height, int width, int speed){
        super(position, height, width, speed);
        _canEatGhost = false;
        _direction = null;
    }

    public Bonus lastEatenItem(){
        return _lastEatenItem;
    }

    public boolean isActive(){
        return true;
    }

    public boolean canEatGhost(){
        return _canEatGhost;
    }

    public void cancelSpPacGumEffect(){
        _canEatGhost = false;
    }

    public void move() {
        if (_direction == null)
            return;
        _collisions = checker.hasCollisionsWith(this,nextPos());
        reactAfterCollision();
    }

    public void nextPosDir(Direction dir) {

    }

    public void checkPosition() {
        if (_position.x < -14)
            _position.x = _position.x + 572;
        if (_position.x > 558)
            _position.x = _position.x - 572;
    }

    @Override
    protected void reactAfterCollision() {
        if (_collisions.size() == 0)
            _position = nextPos();
        for(var e : _collisions) {
            if (e instanceof Ghost) {
                System.out.println("Oh a Ghost !");
                if(_canEatGhost) {
                    ((Ghost) e).die();
                    _position = nextPos();
                }
                else{
                    respawn();
                }
            }
            else if (e instanceof Wall)
                return;
            else if (e instanceof ScoringBonus) {
                System.out.println("Oh a PacGum !");
                _lastEatenItem = (Bonus)e;
                _position = nextPos();
            }
            else if(e instanceof SuperPacGum){
                System.out.println("Oh a super PacGum");
                _canEatGhost = true;
                _lastEatenItem = (Bonus)e;
                _position = nextPos();
            }
        }
    }

}
