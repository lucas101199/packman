public class Ghost extends Character{
    public Ghost(Position position, int height, int width, int speed) {
        super(position, height, width, speed);
    }

    @Override
    public void reactAfterCollision() {

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
