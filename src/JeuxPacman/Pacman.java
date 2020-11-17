package JeuxPacman;

public class Pacman extends Character{

    private Bonus _lastEatenItem;
    private  boolean _canEatGhost;

    public Pacman(Position position, int height, int width, int speed){
        super(position, height, width, speed);
        _canEatGhost = false;
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

    public void move() {
        _position = new Position(_position.x + 1, _position.y);
    }

    @Override
    protected void reactAfterCollision() {
        for(var e : _collisions) {
            if (e instanceof Ghost) {
                System.out.println("Oh a Ghost !");
                if(_canEatGhost) {
                    ((Ghost) e).die();
                    _position = nextPos();
                }
                else{
                    respawn();
                }
            }
            else if (e instanceof Wall)
                System.out.println("Oh a wall !");
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
