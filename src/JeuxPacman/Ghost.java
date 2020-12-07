package JeuxPacman;

public class Ghost extends Character{

    private boolean _isDead;
    public boolean _edible;

    public Ghost(Position position, int height, int width, int speed, DisplayGhost display) throws Exception {
        super(position, height, width, speed, display);
        _direction = Direction.SOUTH;
        _edible = false;
        _isDead = false;
        display.display(_direction,_position,false,false);
    }

    @Override
    public void move(Direction direction) {
        Position oldpos = _position;
        if (positionIsCenter()) {
            _direction = Direction.NORTH;
            _position = nextPos();
        } else {
            super.move(_direction);
        }
        try {
            if (!_position.equals(oldpos))
                ((DisplayGhost)display).display(_direction,_position,_edible,_isDead);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restart() {
        super.restart();
    }

    @Override
    public void respawn() {
        super.respawn();
        _edible = false;
        _isDead = false;
        _direction = Direction.SOUTH;
        try {
            ((DisplayGhost)display).display(_direction,_position, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private boolean positionIsCenter() {
        return (_position.x >= 272 && _position.x <= 274 && _position.y <= 333 && _position.y >= 331);
    }

    @Override
    public void reactAfterCollision() {
        boolean wall = false;
        for (var e : _collisions){
            if ( e instanceof Pacman && !_isDead){
                var pc = (Pacman)e;
                if (pc.canEatGhost()) {
                    die();
                } else {
                    if (!pc.isDead)
                        pc.die();
                }
                return;
            }
            else if (e instanceof Wall) {
                if (e instanceof Niche)
                    _isDead = false;
                else if (!(e instanceof Door))
                    wall = true;
            }
        }
        if (wall) {
            _direction = getRandomDir();
        } else {
            _position = nextPos();
        }
    }

    public void die(){
        _isDead = true;
        _edible = false;
        try {
            ((DisplayGhost)display).display(_direction,_position,false,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean set_isDead() {
        return _isDead;
    }

    @Override
    public boolean isActive() {
        return !_isDead;
    }

}
