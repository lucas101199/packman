package Outils;

import GraphicEngine.GraphicEngine;

public interface Game {

    void init();

    void start();

    void update();

    double getSpeed();

    void set_graphic(GraphicEngine graphic);

}
