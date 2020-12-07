package JeuxPacman;

public class Ghost extends Character{

    private boolean _isDead;
    public boolean _edible;
    private boolean _exit;

    public Ghost(Position position, int height, int width, int speed, DisplayGhost display) throws Exception {
        super(position, height, width, speed, display);
        _direction = Direction.SOUTH;
        _edible = false;
        _isDead = false;
        _exit = false;
        display.display(_direction,_position,false,false);
    }

    @Override
    public void move(Direction direction) {
        Position oldpos = _position;
        if (positionIsCenter() && _isDead) {
            _position = new Position(273, 332);
        }
        if (positionIsCenter()) {
            _direction = Direction.NORTH;
            _isDead = false;
            _position = nextPos();
        } else if (_isDead) {
            moveToCenter();
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

    private void moveToCenter() {
        double x = _position.x;
        double y = _position.y;
        if (x < 272)
            x += 1;
        else if (x > 274)
            x -= 1;
        if (y < 331)
            y += 1;
        else if (y > 333)
            y -= 1;
        _position = new Position(x,y);
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
                else if (e instanceof Door) {
                    _exit = true;
                    _direction = Direction.NORTH;
                }
                else wall = true;
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

    @Override
    public boolean isActive() {
        return !_isDead;
    }

    @Override
    public Direction getRandomDir() {
        Direction dir = super.getRandomDir();
        if (_exit) {
            _exit = false;
            while (!(dir.equals(Direction.EAST) || dir.equals(Direction.WEST)))
                dir = super.getRandomDir();
        }
        return dir;
    }
}
