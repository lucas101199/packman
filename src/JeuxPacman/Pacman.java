package JeuxPacman;

import javafx.geometry.Pos;

public class Pacman extends Character{

    private Bonus _lastEatenItem;
    private  boolean _canEatGhost;
    private Position _initPos;
    public boolean _isDead;
    public boolean _needRespawn;
    public long _deathDate;


    public Pacman(Position position, int height, int width, int speed, PCCollisionSolver pcCollisionSolv){
        super(position, height, width, speed, pcCollisionSolv);
        _canEatGhost = false;
        //_direction = nu;
        _initPos = new Position(position.x, position.y);
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

    public void setEatGhost(boolean canEat){
        _canEatGhost = canEat;
    }

    public boolean isDead(){
        return _isDead;
    }

    public void setIsDead(boolean isDead){
        _isDead = isDead;
    }

    public boolean needRespawn(){
        return _needRespawn;
    }
    public void setNeedRespawn(boolean needRespawn){
        _needRespawn = needRespawn;
    }

    public long getDeathDate(){
        return _deathDate;
    }

    public void setDeathDate(long deathDate){
        _deathDate = deathDate;
    }


    public void cancelSpPacGumEffect(){
        _canEatGhost = false;
    }

    public void setLastEatenItem(Bonus b){
        _lastEatenItem = b;
    }

    public void setCanEatGhost(boolean val){
        _canEatGhost = true;
    }



    public void checkPosition() {
        if (_position.x < -14)
            _position.x = _position.x + 572;
        if (_position.x > 558)
            _position.x = _position.x - 572;
    }

    @Override
    public void respawn() {
        setPosition(_initPos.x, _initPos.y);
        _direction = Direction.NONE;
    }


}
