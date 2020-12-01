package JeuxPacman;

import java.util.Random;

public class Ghost extends Character{

    private boolean _isDead;
    private final Random random = new Random();

    public Ghost(Position position, int height, int width, int speed, DisplayCharacter display) throws Exception {
        super(position, height, width, speed, display);
        _direction = Direction.SOUTH;
        display.display(_direction,_position);
    }

    @Override
    public void move(Direction direction) {
        changeDirection();
        super.move(direction);
        try {
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
        for (var e : _collisions){
            if ( e instanceof Pacman){
                var pc = (Pacman)e;
                if (pc.canEatGhost()) {
                    die();
                } else {
                    if (!pc.isDead)
                        pc.die();
                }
            }
            else if(!(e instanceof Wall)) {
                _position = nextPos();
            }
            else {
                int i = random.nextInt(4);
                switch (i) {
                    case 0:
                        _direction = Direction.SOUTH;
                        break;
                    case 1:
                        _direction = Direction.WEST;
                        break;
                    case 2:
                        _direction = Direction.EAST;
                        break;
                    case 3:
                        _direction = Direction.NORTH;
                        break;
                    default:
                        break;
                }
            }

        }
    }

    private void changeDirection() {
        int i = random.nextInt(4);
        switch (i) {
            case 0 :
                _direction = Direction.SOUTH;
                break;
            case 1 :
                _direction = Direction.WEST;
                break;
            case 2 :
                _direction = Direction.EAST;
                break;
            case 3 :
                _direction = Direction.NORTH;
                break;
            default :
                break;
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
