package JeuxPacman;

import java.util.Random;

public class Ghost extends Character{
    private boolean _isDead;

    public Ghost(Position position, int height, int width, int speed) {
        super(position, height, width, speed);
    }

    @Override
    public void move(Direction direction) {
        changeDirection();
        super.move(direction);
    }

    @Override
    public void reactAfterCollision() {
        for(var e : _collisions){
            if( e instanceof Pacman){
                var pc = (Pacman)e;
                if(!pc.canEatGhost() || _direction != getOppositeDir(pc._direction)) {
                    _position = nextPos();
                }
                else if(_direction == getOppositeDir(pc._direction))
                    die();
            }
            else if(!(e instanceof Wall)) {
                _position = nextPos();
            }
            else {
                Random random = new Random();
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
        Random random = new Random();
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

    private Direction getOppositeDir(Direction dir){
        if(dir == Direction.NORTH)
            return Direction.SOUTH;
        else if(dir == Direction.EAST)
            return Direction.WEST;
        else if(dir == Direction.SOUTH)
            return Direction.NORTH;
        else
            return Direction.EAST;
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
