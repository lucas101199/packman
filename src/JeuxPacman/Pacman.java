public class Pacman extends Character{
    private int _nbOfLife;
    private Bonus _lastEatenItem;

    public Pacman(Position position, int height, int width, int speed, int nbOfLife){
        super(position, height, width, speed);
        _nbOfLife = nbOfLife;

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

    @Override
    protected void reactAfterCollision() {
        for(var e : _collisions) {
            if (e instanceof Ghost) {
                System.out.println("Oh a Ghost !");
                _nbOfLife--;
                respawn();
            }
            else if (e instanceof Wall)
                System.out.println("Oh a wall !");
            else if (e instanceof Bonus) {
                System.out.println("Oh a PacGum !");
                _lastEatenItem = (Bonus)e;
            }
            else
                System.out.println("Oh a super PacGum");
        }
    }

}
