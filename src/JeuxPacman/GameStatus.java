import java.util.ArrayList;

public class GameStatus {
    private Pacman _pc;
    private ArrayList<Ghost> _ennemies;

    public GameStatus(Pacman pc, ArrayList<Ghost> ennemies){
        _pc = pc;
        _ennemies = ennemies;
    }

    public Status getStatus(){
        if(_pc.getRemainingLife() == 0)
            return Status.LOST;
        else{
            for(var e : _ennemies)
            {
                if(e.isActive())
                    return Status.IN_PROGRESS;
            }
            return Status.WON;
        }
    }

}
