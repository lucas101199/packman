public class Bonus extends Entity{
    int _score;
    boolean _isActive;
    int _duration;

    protected Bonus(Position pos, int height, int width, int duration, int score) {
        super(pos, height, width);
        _isActive = true;
        _duration = duration;
        _score = score;
    }

    public int getScore(){
        return _score;
    }
    
    public void consume(){
        _isActive = false;
        _duration--;
    }

    public boolean isFullyConsumed(){
        return _duration == 0;
    }

    @Override
    public boolean isActive() {
        return _isActive;
    }

}
