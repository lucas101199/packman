package JeuxPacman;

import Outils.Position;

public class Wall extends Entity {

    public Wall(Position pos, int height, int width){
        super(pos, height, width);
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
