package JeuxPacman;

import GraphicEngine.GraphicEngine;
import InputEngine.InputEngine;
import Interfaces.GameInterface;

import java.util.ArrayList;

public class Game implements GameInterface {

    private ArrayList<Entity> _items;
    private ArrayList<Ghost> _ennemies;
    private Pacman _pc;
    private GraphicEngine _graphic;
    private CollisionChecker checker;
    private String lastKey;
    private int keyTime;
    private boolean gameStart;
    private boolean gamePaused;
    private int vie;

    public void init() {
        try {

            //Création du labyrinthe

            _graphic.addScene("maze");
            _graphic.setSizeScene("maze",544,750);
            _graphic.addImage("maze","background","./src/Images/Autres/background.png");
            _graphic.resizeImage("maze","background",750,544);
            _graphic.setPositionImage("maze","background",0,0,false);
            _graphic.displayObject("maze","background");
            _graphic.addImage("maze","map","./src/Images/Map/map.png");
            _graphic.resizeImage("maze","map",600,544);
            _graphic.setPositionImage("maze","map",272,350, true);
            _graphic.displayObject("maze","map");
            _ennemies = new ArrayList<>();
            _items = new ArrayList<>();

            int pgId = 0;
            int[] Y = {
                    29,48,67,87,106,126,145,164,
                    184,203,222,242,261,280,300,319,338,358,378,
                    397,416,435,455,474,493,513,532,551,571};
            int[][] X = {
                    {30,51,69,88,107,127,147,165,185,204,224,244,302,321,339,360,379,399,417,438,457,475,495,516},
                    {30,127,244,302,417,516},
                    {127,244,302,417},
                    {30,127,244,302,417,516},
                    {30,51,69,88,107,127,147,165,185,204,224,263,282,244,302,321,339,360,379,399,417,438,457,475,495,516},
                    {30,127,185,360,417,516},
                    {30,127,185,360,417,516},
                    {30,51,69,88,107,127,185,204,224,244,302,321,339,360,417,438,457,475,495,516},
                    {127,417},
                    {127,417},
                    {127,417},
                    {127,417},
                    {127,417},
                    {127,417},
                    {127,417},
                    {127,417},
                    {127,417},
                    {127,417},
                    {127,417},
                    {30,51,69,88,107,127,147,165,185,204,224,244,302,321,339,360,379,399,417,438,457,475,495,516},
                    {30,127,244,302,417,516},
                    {30,127,244,302,417,516},
                    {51,69,127,147,165,185,204,224,244,302,321,339,360,379,399,417,475,495},
                    {69,127,185,360,417,475},
                    {69,127,185,360,417,475},
                    {30,51,69,88,107,127,185,204,224,244,302,321,339,360,417,438,457,475,495,516},
                    {30,244,302,516},
                    {30,244,302,516},
                    {30,51,69,88,107,127,147,165,185,204,224,263,282,244,302,321,339,360,379,399,417,438,457,475,495,516}
            };
            int yId = 0;
            for (int y : Y) {
                for (int x : X[yId]) {
                    _items.add(new PacGum(new Position(x, y+50), 8, 8, 1, 1, new DisplayPacGum(_graphic, "maze", pgId)));
                    pgId++;
                }
                yId++;
            }

            int[][] spgCoords = {{30,67},{516,67},{30,455},{516,455}};
            int spgId = 0;
            for (int[] coord : spgCoords) {
                _items.add(new SuperPacGum(new Position(coord[0], coord[1]+50), 8, 8, 300, new DisplaySuperPacGum(_graphic, "maze", spgId)));
                spgId++;
            }

            _pc = new Pacman(new Position(272,504), 29,29,2,new DisplayPacman(_graphic,"maze"));
            _ennemies.add(new Ghost(new Position(190,274),29,29,2,new DisplayClyde(_graphic,"maze")));
            _ennemies.add(new Ghost(new Position(360,274),29,29,2,new DisplayInky(_graphic,"maze")));
            _ennemies.add(new Ghost(new Position(272,274),29,29,2,new DisplayBlinky(_graphic,"maze")));
            _ennemies.add(new Ghost(new Position(272,390),29,29,2,new DisplayPinky(_graphic,"maze")));

            InputEngine _input = new InputEngine(this);
            _input.addKey("Z");
            _input.addKey("Q");
            _input.addKey("S");
            _input.addKey("D");
            _input.addKey("P");
            _input.addKey("Ctrl");
            _input.addKey("E");
            _input.setScene(_graphic.getScene("maze").getScene());
            _items.add(new Wall(new Position(80.5, 119.5), 45, 65));   // Rectangle haut gauche 1

            _items.add(new Wall(new Position(186, 119.5), 45, 84));   // Rectangle haut gauche 2

            _items.add(new Wall(new Position(360, 119.5), 45, 84));    // Rectangle haut droite 1

            _items.add(new Wall(new Position(465.5, 119.5), 45, 65));  // Rectangle haut droite 2

            _items.add(new Wall(new Position(80.5, 186.5), 27, 65));    // Rectangle long horizontal haut gauche

            _items.add(new Wall(new Position(465.5, 186.5), 27, 65)); // Rectangle long horizontal haut droite

            _items.add(new Wall(new Position(185.5, 475.5), 27, 83)); // Rectangle long horizontal bas gauche

            _items.add(new Wall(new Position(360.5, 475.5), 27, 83)); // Rectangle long horizontal bas droite


            _items.add(new Wall(new Position(157.5, 389), 84, 27));   // Rectangle long vertical gauche

            _items.add(new Wall(new Position(388.5, 389), 84, 27));   // Rectangle long vertical droite


            _items.add(new Wall(new Position(273, 185), 24, 142));    // T central 1
            _items.add(new Wall(new Position(273, 228.5), 61, 30));   // T central 1

            _items.add(new Wall(new Position(273, 418), 26, 142));    // T central 2
            _items.add(new Wall(new Position(273, 460.5), 57, 30));   // T central 2

            _items.add(new Wall(new Position(273, 533.5), 27, 140));  // T central 3
            _items.add(new Wall(new Position(273, 576), 56, 30));    // T central 3


            _items.add(new Wall(new Position(157.5, 244.5), 143, 27));// T haut gauche
            _items.add(new Wall(new Position(199.5, 243.5), 31, 55)); // T haut gauche

            _items.add(new Wall(new Position(388.5, 244.5), 143, 27));// T haut droite
            _items.add(new Wall(new Position(346.5, 243.5), 31, 55)); // T haut droite

            _items.add(new Wall(new Position(137, 591), 26, 180));    // T bas gauche
            _items.add(new Wall(new Position(158, 548.5), 57, 28));   // T bas gauche

            _items.add(new Wall(new Position(409, 591), 26, 180));    // T bas droite
            _items.add(new Wall(new Position(388, 548.5), 57, 28));   // T bas droite


            _items.add(new Wall(new Position(273, 332), 84, 142));    // Foyer fantômes


            _items.add(new Wall(new Position(54, 273.5), 85, 115));    // Grand carre haut gauche

            _items.add(new Wall(new Position(490.5, 273.5), 85, 114));// Grand carre haut droit

            _items.add(new Wall(new Position(55, 389), 84, 114));    // Grand carre bas gauche

            _items.add(new Wall(new Position(491, 389), 84, 114));  // Grand carre bas droite


            _items.add(new Wall(new Position(273, 58.5), 15, 510));    // Bord haut

            _items.add(new Wall(new Position(273, 642.5), 15, 512));  // Bord bas


            _items.add(new Wall(new Position(9, 140.5), 179, 16));    // Bord haut gauche

            _items.add(new Wall(new Position(536.5, 140.5), 179, 15)); // Bord haut droite

            _items.add(new Wall(new Position(8.5, 541), 218, 15));    // Bord bas gauche

            _items.add(new Wall(new Position(537, 541), 218, 14));    // Bord bas droite


            _items.add(new Wall(new Position(273, 104.5), 75, 28));    // Tige haut


            _items.add(new Wall(new Position(35.5, 533), 28, 37));    // Tige bas gauche

            _items.add(new Wall(new Position(510.5, 533.5), 27, 37)); // Tige bas droite


            _items.add(new Wall(new Position(80, 475), 26, 66));    // L inversé gauche
            _items.add(new Wall(new Position(99, 518), 58, 28));    // L inversé gauche

            _items.add(new Wall(new Position(466, 475.5), 27, 66));   // L inversé droite
            _items.add(new Wall(new Position(447, 518.5), 57, 28));   // L inversé droite

            ArrayList<Entity> entities = new ArrayList<>(_items);
            entities.addAll(_ennemies);
            entities.add(_pc);
            checker = new CollisionChecker(entities);
            Character.setCollisionChecker(checker);
            gameStart = false;
            gamePaused = false;
            vie = 3;

            /* ------ Création Scène MENU ------ */
            _graphic.addScene("menu");
            _graphic.setSizeScene("menu",544,700);

            // Background
            _graphic.addImage("menu","background","./src/Images/Menu/menu.png");
            _graphic.setPositionImage("menu","background",0,0,false);
            _graphic.displayObject("menu","background");

            // Bouton JOUER
            _graphic.addImageButton("menu","jouer","./src/Images/Menu/bouton_jouer.png");
            _graphic.setPositionImageButton("menu","jouer",197,280,false);
            _graphic.displayObject("menu","jouer");

            // Bouton AIDE
            _graphic.addImageButton("menu","aide","./src/Images/Menu/bouton_aide.png");
            _graphic.setPositionImageButton("menu","aide",197,380,false);
            _graphic.displayObject("menu","aide");

            // Bouton QUITTER
            _graphic.addImageButton("menu","quitter","./src/Images/Menu/bouton_quitter.png");
            _graphic.setPositionImageButton("menu","quitter",197,480,false);
            _graphic.displayObject("menu","quitter");


            /* ------ Création Scène AIDE ------ */
            _graphic.addScene("help");
            _graphic.setSizeScene("help",544,700);

            // Background
            _graphic.addImage("help","background","./src/Images/Aide/aide.png");
            _graphic.setPositionImage("help","background",0,0,false);
            _graphic.displayObject("help","background");

            // Bouton RETOUR
            _graphic.addImageButton("help","retour","./src/Images/Aide/retour.png");
            _graphic.setPositionImageButton("help","retour",197,630,false);
            _graphic.displayObject("help","retour");


            /* ------ Création Scène ABANDON ------ */
            _graphic.addScene("forfeit");
            _graphic.setSizeScene("forfeit",544,750);

            // Background
            _graphic.addImage("forfeit","background","./src/Images/Autres/background.png");
            _graphic.setPositionImage("forfeit","background",0,0,false);
            _graphic.displayObject("forfeit","background");

            // Texte QUESTION
            _graphic.addImage("forfeit","question","./src/Images/Autres/question.png");
            _graphic.setPositionImage("forfeit","question",52,221,false);
            _graphic.displayObject("forfeit","question");

            // Bouton OUI
            _graphic.addImageButton("forfeit","oui","./src/Images/Autres/reponse_oui.png");
            _graphic.setPositionImageButton("forfeit","oui",252,295,false);
            _graphic.displayObject("forfeit","oui");

            // Bouton NON
            _graphic.addImageButton("forfeit","non","./src/Images/Autres/reponse_non.png");
            _graphic.setPositionImageButton("forfeit","non",252,325,false);
            _graphic.displayObject("forfeit","non");


            /* ------ Création Scène VICTOIRE ------ */
            _graphic.addScene("victory");
            _graphic.setSizeScene("victory",544,700);

            // Background
            _graphic.addImage("victory","background","./src/Images/Autres/background.png");
            _graphic.setPositionImage("victory","background",0,0,false);
            _graphic.displayObject("victory","background");

            // TITRE
            _graphic.addImage("victory","titre","./src/Images/Autres/titre.gif");
            _graphic.setPositionImage("victory","titre",30,65,false);
            _graphic.displayObject("victory","titre");

            // SCORE
            _graphic.addImage("victory","score","./src/Images/Autres/score.png");
            _graphic.setPositionImage("victory","score",184,215,false);
            _graphic.displayObject("victory","score");

            // NIVEAU
            _graphic.addImage("victory","niveau","./src/Images/Autres/niveau.png");
            _graphic.setPositionImage("victory","niveau",184,330,false);
            _graphic.displayObject("victory","niveau");

            // Bouton RETOUR MENU
            _graphic.addImageButton("victory","retour_menu","./src/Images/Autres/retour_menu.png");
            _graphic.setPositionImageButton("victory","retour_menu",134,505,false);
            _graphic.displayObject("victory","retour_menu");

            // Bouton REJOUER
            _graphic.addImageButton("victory","rejouer","./src/Images/Autres/rejouer.png");
            _graphic.setPositionImageButton("victory","rejouer",193,565,false);
            _graphic.displayObject("victory","rejouer");

            // Bouton QUITTER
            _graphic.addImageButton("victory","quitter","./src/Images/Autres/quitter.png");
            _graphic.setPositionImageButton("victory","quitter",194,625,false);
            _graphic.displayObject("victory","quitter");


            /* ------ Création Scène PERDU ------ */
            _graphic.addScene("lost");
            _graphic.setSizeScene("lost",544,700);

            // Background
            _graphic.addImage("lost","background","./src/Images/Autres/background.png");
            _graphic.setPositionImage("lost","background",0,0,false);
            _graphic.displayObject("lost","background");

            // TITRE
            _graphic.addImage("lost","titre_abandon","./src/Images/Autres/titre_abandon.gif");
            _graphic.setPositionImage("lost","titre_abandon",55,70,false);
            _graphic.displayObject("lost","titre_abandon");

            // SCORE
            _graphic.addImage("lost","score","./src/Images/Autres/score.png");
            _graphic.setPositionImage("lost","score",184,215,false);
            _graphic.displayObject("lost","score");

            // NIVEAU
            _graphic.addImage("lost","niveau","./src/Images/Autres/niveau.png");
            _graphic.setPositionImage("lost","niveau",184,330,false);
            _graphic.displayObject("lost","niveau");

            // Bouton RETOUR MENU
            _graphic.addImageButton("lost","retour_menu","./src/Images/Autres/retour_menu.png");
            _graphic.setPositionImageButton("lost","retour_menu",134,505,false);
            _graphic.displayObject("lost","retour_menu");

            // Bouton REJOUER
            _graphic.addImageButton("lost","rejouer","./src/Images/Autres/rejouer.png");
            _graphic.setPositionImageButton("lost","rejouer",193,565,false);
            _graphic.displayObject("lost","rejouer");

            // Bouton QUITTER
            _graphic.addImageButton("lost","quitter","./src/Images/Autres/quitter.png");
            _graphic.setPositionImageButton("lost","quitter",194,625,false);
            _graphic.displayObject("lost","quitter");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            _graphic.displayScene("menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void restartMaze() {
        try {

            for (Entity item : _items) {
                item.restart();
            }

            _pc.restart();
            vie = 3;

            for (Character ghost : _ennemies) {
                ghost.restart();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!_graphic.currentScene().equals("maze"))
            return;
        if (gamePaused)
            return;
        if (_pc._direction == Direction.NONE && !gameStart)
            return;
        if (_pc.score == 240) {
            try {
                _graphic.displayScene("victory");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (_pc.isDead) {
            if (System.currentTimeMillis() - _pc.deathDate < 3900)
                return;
            vie--;
            if (vie == 0) {
                try {
                    _graphic.displayScene("lost");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                _pc.respawn();
                for (Ghost g : _ennemies)
                    g.respawn();
                gameStart = false;
                return;
            }
        }
        if (_pc.canEatGhost() && !_ennemies.get(0)._edible) {
            for (Ghost ghost : _ennemies) {
                ghost._edible = true;
            }
        } else if (!_pc.canEatGhost() && _ennemies.get(0)._edible) {
            for (Ghost ghost : _ennemies) {
                ghost._edible = false;
            }
        }
        handleLastKey();
        _pc.move(_pc._direction);
        for (Ghost g : _ennemies)
            if (!_pc.isDead)
                g.move(g._direction);

    }

    @Override
    public double getSpeed() {
        return 42;
    }

    public void set_graphic(GraphicEngine _graphic) {
        this._graphic = _graphic;
    }

    @Override
    public void handleKey(String key) {
        switch (_graphic.currentScene()) {
            case "menu":
                switch (key) {
                    case "jouer":
                        restartMaze();
                        try {
                            _graphic.displayScene("maze");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "aide":
                        try {
                            _graphic.displayScene("help");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "quitter":
                        _graphic.stop();
                        break;
                }
                break;
            case "help":
                if (key.equals("retour")) {
                    try {
                        _graphic.displayScene("menu");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "maze":
                switch (key) {
                    case "P":
                        gamePaused = !gamePaused;
                        break;
                    case "Ctrl+E":
                        try {
                            _graphic.displayScene("forfeit");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        lastKey = key;
                        keyTime = 14;
                        if (!gameStart && (key.equals("D") || key.equals("Q")))
                            gameStart = true;
                }
                break;
            case "forfeit":
                switch (key) {
                    case "oui":
                        try {
                            _graphic.displayScene("menu");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "non":
                        try {
                            _graphic.displayScene("maze");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
                break;
            case "victory":
            case "lost":
                switch (key) {
                    case "retour_menu":
                        try {
                            _graphic.displayScene("menu");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "rejouer":
                        restartMaze();
                        try {
                            _graphic.displayScene("maze");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "quitter":
                        _graphic.stop();
                        break;
                }
                break;
        }
    }

    private void handleLastKey() {
        if (keyTime > 0) {
            keyTime--;
            Direction olddir = _pc._direction;
            ArrayList<Entity> collideWith;
            switch (lastKey) {
                case "Z":
                    _pc._direction = Direction.NORTH;
                    break;
                case "D":
                    _pc._direction = Direction.EAST;
                    break;
                case "S":
                    _pc._direction = Direction.SOUTH;
                    break;
                case "Q":
                    _pc._direction = Direction.WEST;
                    break;
                default:
                    break;
            }
            collideWith = checker.hasCollisionsWith(_pc,_pc.nextPos());
            for (Entity e : collideWith) {
                if (e instanceof Wall) {
                    _pc._direction = olddir;
                    break;
                }
            }
            if (_pc._direction != olddir)
                keyTime = 0;
        }
    }

}
