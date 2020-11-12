public class Bonus extends Entity{
    boolean _isSuperPacGum;
    int _score;
    boolean _isActive;

    public Bonus(Position pos, int height, int width, boolean isSuperPacGum, int score) {
        super(pos, height, width);
        _isSuperPacGum = isSuperPacGum;
        _isActive = true;
        _score = score;
    }
    public boolean isSuperPacGum(){
        return _isSuperPacGum;
    }

    public int getScore(){
        return _score;
    }

    @Override
    public boolean isActive() {
        return _isActive;
    }

    public void eat(){
        _isActive =false;
    }
}
