package JeuxPacman;

public class Pacman extends Character{

    private Bonus _lastEatenItem;
    private  boolean _canEatGhost;
    public boolean isDead;
    public boolean needRespawn;
    public long deathDate;

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

    @Override
    public void move(Direction direction) {
        if (direction == null)
            return;
        super.move(direction);
    }

    public void checkPosition() {
        if (_position.x < -14)
            _position.x = _position.x + 572;
        if (_position.x > 558)
            _position.x = _position.x - 572;
    }

    @Override
    public void respawn() {
        super.respawn();
        _direction = null;
    }

    @Override
    protected void reactAfterCollision() {
        for(var e : _collisions) {
            if (e instanceof Ghost) {
                if(_canEatGhost) {
                    ((Ghost) e).die();
                    _position = nextPos();
                }
                else{
                    isDead = true;
                    needRespawn = true;
                    deathDate = System.currentTimeMillis();
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
