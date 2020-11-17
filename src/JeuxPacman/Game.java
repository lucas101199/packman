package JeuxPacman;

import java.util.ArrayList;

public class Game {
    private ArrayList<Entity> _entities;
    private ArrayList<Ghost> _ennemies;
    private  Pacman _pc;
    private  int _score;
    private final int _gameSpeed;

    public Game(int gameSpeed){
        _gameSpeed = gameSpeed;
    }

    public void start(){
        _entities = new ArrayList<>();
        _ennemies = new ArrayList<>();
        var c = new CollisionChecker(_entities);
        Character.setCollisionChecker(c);
        _entities.add(new Ghost(new Position(50,60), 20,20,0));
        _entities.add(new Wall(new Position(80,10), 20,20));
        _entities.add(new ScoringBonus(new Position(0,10), 20,20, 1,50));
        var ghost = new Ghost(new Position(-50,10), 20,20,10);
        _entities.add(ghost);
        _ennemies.add(ghost);
        //  _entities.add(new SuperPacGum(new Position(-40,10), 20,20, 2));

        _pc= new Pacman(new Position(50,10), 20,20,10, 3);
        _entities.add(new SuperPacGum(new Position(50,10), 20,20,20));
        _entities.add(_pc);
        _pc.move(Direction.WEST);
        var status = new GameStatus(_pc, _ennemies);
        _score = 0;
        while(true){
           if(status.getStatus() == Status.WON){
               System.out.println("Vous avez gagné !");
               break;
           }
           else if(status.getStatus() == Status.LOST)
           {
               System.out.println("Vous avez perdu !");
               break;
           }
            if(_pc.lastEatenItem() != null && !_pc.lastEatenItem().isFullyConsumed()) {
                _score += _pc.lastEatenItem().getScore();
                _pc.lastEatenItem().consume();
            }
            else if(_pc.lastEatenItem() != null && _pc.canEatGhost())
                _pc.cancelSpPacGumEffect();
            updateWorld();
            showPacmanStatus();
            System.out.println("Ghost pos : " + ghost.getPosition().x + ";" + ghost.getPosition().y);
            if(ghost.isActive())
                ghost.move(Direction.EAST);

            waitFewMoment();
        }
    }

    public void showPacmanStatus(){
        System.out.println(_pc.getPosition().x + ";" + _pc.getPosition().y);
        System.out.println("Score :" + _score + " | " + "Vie :" + _pc.getRemainingLife());
        System.out.println("Est super PC :" + _pc.canEatGhost());
        System.out.println("");
    }

    public void updateWorld(){
        _entities.removeIf(entity -> !entity.isActive());
    }

    public void waitFewMoment(){
        try{
            Thread.sleep(_gameSpeed);;
        }
        catch(InterruptedException e){e.printStackTrace();}
    }
}
