package JeuxPacman;

import GraphicEngine.GraphicEngine;

import java.util.ArrayList;

public class Game {

    private ArrayList<Entity> _items;
    private ArrayList<Ghost> _ennemies;
    private  Pacman _pc;
    private DisplayPacman _pcDisplay;
    private  int _score;
    private final int _gameSpeed = 10;
    private final GraphicEngine _graphic = new GraphicEngine("PacMan");

    public void init() throws Exception {
        _graphic.addScene("maze");
        _graphic.setSizeScene("maze",544,600);
        _graphic.displayScene("maze");
        _graphic.addImage("maze","map","./src/Images/Map/map.png");
        _graphic.resizeImage("maze","map",600,544);
        _graphic.setPositionImage("maze","map",272,300);
        _graphic.displayImage("maze","map");
        _items = new ArrayList<>();
        _ennemies = new ArrayList<>();
        _pc = new Pacman(new Position(272,454), 31,27,10);
        _pcDisplay = new DisplayPacman(_graphic,"maze");
        _score = 0;
    }

    public void start() throws Exception {
        _pcDisplay.displayPacMan("Pacman_left",_pc.getPosition());
    }

    public void waitFewMoment(){
        try{
            Thread.sleep(_gameSpeed);
        }
        catch(InterruptedException e){e.printStackTrace();}
    }

    public void show() {
        _graphic.window.show();
    }

    public void update() throws Exception {
        _pc.move();
        _pcDisplay.displayPacMan("Pacman_left",_pc.getPosition());
    }

}
