package JeuxPacman;

public class Bonus extends Entity{

    int _score;
    boolean _isActive;
    int _duration;
    final DisplayBonus display;

    protected Bonus(Position pos, int height, int width, int duration, int score, DisplayBonus display) throws Exception {
        super(pos, height, width);
        _isActive = true;
        _duration = duration;
        _score = score;
        this.display = display;
        display.display(_position);
    }

    public int getScore(){
        return _score;
    }
    
    public void consume() throws Exception {
        if (_isActive)
            display.hide();
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
