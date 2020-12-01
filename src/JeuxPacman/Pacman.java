package JeuxPacman;

public class Pacman extends Character{

    private Bonus _lastEatenItem;
    private  boolean _canEatGhost;
    public boolean isDead;
    public long deathDate;

    private final DisplayPacman display;

    public Pacman(Position position, int height, int width, int speed, DisplayPacman display) throws Exception {
        super(position, height, width, speed, display);
        this.display = display;
        _canEatGhost = false;
        _direction = null;
        isDead = false;
        display.displayPacmanStart(_position);
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
        checkPosition();
        try {
            display.display(_direction,_position);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        isDead = false;
        _direction = null;
        try {
            display.displayPacmanStart(_position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void die() {
        isDead = true;
        deathDate = System.currentTimeMillis();
        Direction olddir = _direction;
        _direction = getOppositeDir(_direction);
        nextPos();
        _direction = olddir;
        try {
            System.out.println("Death");
            display.displayPacManDeath(_position);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    die();
                }
            }
            else if (e instanceof Wall)
                return;
            else if (e instanceof ScoringBonus) {
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
