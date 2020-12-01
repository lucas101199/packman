package JeuxPacman;

public class Ghost extends Character{

    private boolean _isDead;

    public Ghost(Position position, int height, int width, int speed, DisplayCharacter display) throws Exception {
        super(position, height, width, speed, display);
        _direction = Direction.SOUTH;
        display.display(_direction,_position);
    }

    @Override
    public void move(Direction direction) {
        Position oldpos = _position;
        super.move(_direction);
        try {
            if (!_position.equals(oldpos))
                display.display(_direction,_position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void respawn() {
        super.respawn();
        _direction = Direction.SOUTH;
        try {
            display.display(_direction,_position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reactAfterCollision() {
        boolean wall = false;
        for (var e : _collisions){
            if ( e instanceof Pacman){
                var pc = (Pacman)e;
                if (pc.canEatGhost()) {
                    die();
                } else {
                    if (!pc.isDead)
                        pc.die();
                }
                return;
            }
            else if (e instanceof Wall)
                wall = true;
        }
        if (wall) {
            _direction = getRandomDir();
        }
        else {
            _position = nextPos();
        }
    }

    public void die(){
        System.out.println("Ghost killed");
        _isDead = true;
    }

    @Override
    public boolean isActive() {
        return !_isDead;
    }
}
