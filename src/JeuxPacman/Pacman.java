public class Pacman extends Character{
    private int _nbOfLife;
    private Bonus _lastEatenItem;
    private  boolean _canEatGhost;

    public Pacman(Position position, int height, int width, int speed, int nbOfLife){
        super(position, height, width, speed);
        _nbOfLife = nbOfLife;
        _canEatGhost = false;
    }

    public int getRemainingLife(){
        return _nbOfLife;
    }

    public Bonus lastEatenItem(){
        return _lastEatenItem;
    }

    public boolean isActive(){
        return _nbOfLife != 0;
    }

    public boolean canEatGhost(){
        return _canEatGhost;
    }

    public void receiveDamage(){
        _nbOfLife--;
    }

    public void cancelSpPacGumEffect(){
        _canEatGhost = false;
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
                    _nbOfLife--;
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
