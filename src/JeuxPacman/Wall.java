public class Wall implements Entity{
    private  Position _position;
    private int _width;
    private int _height;

    public Wall(Position pos, int height, int width){
        _position = pos;
        _height = height;
        _width = width;
    }

    @Override
    public Position getPosition() {
        return _position;
    }

    @Override
    public int getHeight() {
        return _height;
    }

    @Override
    public int getWidth() {
        return _width;
    }
}
