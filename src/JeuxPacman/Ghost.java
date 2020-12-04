package JeuxPacman;

import javafx.geometry.Pos;

public class Ghost extends Character{
    private boolean _isDead;
    private Position _initPos;
    public Ghost(Position position, int height, int width, int speed, GhostCollisionSolver gCollSolv) {
        super(position, height, width, speed, gCollSolv);
        _initPos = new Position(position.x, position.y);
    }

    @Override
    public void respawn() {
        setPosition(_initPos.x, _initPos.y);
    }



    Direction getOppositeDir(Direction dir){
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

