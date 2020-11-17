package JeuxPacman;

import java.util.ArrayList;

public class GameStatus {
    private Pacman _pc;
    private ArrayList<Ghost> _ennemies;

    public GameStatus(Pacman pc, ArrayList<Ghost> ennemies){
        _pc = pc;
        _ennemies = ennemies;
    }

}
