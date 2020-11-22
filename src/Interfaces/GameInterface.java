package Interfaces;

import GraphicEngine.GraphicEngine;

public interface GameInterface {

    void init();

    void start();

    void update();

    double getSpeed();

    void set_graphic(GraphicEngine graphic);

}
