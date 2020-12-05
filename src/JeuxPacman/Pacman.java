package JeuxPacman;

public class Pacman extends Character{

    private Bonus _lastEatenItem;
    private  boolean _canEatGhost;
    public boolean isDead;
    public long deathDate;
    public int score;

    private final DisplayPacman display;

    public Pacman(Position position, int height, int width, int speed, DisplayPacman display) throws Exception {
        super(position, height, width, speed, display);
        this.display = display;
        _lastEatenItem = null;
        _canEatGhost = false;
        _direction = null;
        isDead = false;
        score = 0;
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
        if (direction == Direction.NONE)
            return;
        Position oldpos = _position;
        super.move(direction);
        checkPosition();
        if (_lastEatenItem != null) {
            if (_canEatGhost && _lastEatenItem.isFullyConsumed()) {
                cancelSpPacGumEffect();
            }
            else if (!_lastEatenItem.isFullyConsumed()) {
                try {
                    _lastEatenItem.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            if (!_position.equals(oldpos))
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
        _direction = Direction.NONE;
        try {
            display.displayPacmanStart(_position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restart() {
        super.restart();
        _canEatGhost = false;
        score = 0;
    }

    public void die() {
        isDead = true;
        deathDate = System.currentTimeMillis();
        Direction olddir = _direction;
        _direction = getOppositeDir(_direction);
        nextPos();
        _direction = olddir;
        try {
            display.displayPacManDeath(_position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void reactAfterCollision() {
        for(var e : _collisions) {
            if (e instanceof Ghost) {
                if (e.isActive()) {
                    if (_canEatGhost) {
                        ((Ghost) e).die();
                    } else {
                        die();
                        return;
                    }
                }
            }
            else if (e instanceof Wall)
                return;
            else if (e instanceof PacGum) {
                if (((PacGum) e)._isActive) {
                    score += ((PacGum) e)._score;
                    try {
                        ((PacGum)e).consume();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            else if(e instanceof SuperPacGum){
                if (((SuperPacGum) e)._isActive) {
                    _canEatGhost = true;
                    _lastEatenItem = (Bonus) e;
                    try {
                        _lastEatenItem.consume();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
        _position = nextPos();
    }

}
