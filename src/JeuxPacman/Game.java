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
    private final int nbVie = 3;
    private int vie;
    private int level = 1;
    private int scoreLevel;
    private int scoreTotal;
    private final int[] positionScoreMaze = {200,230,260,290};
    private final int[] positionScoreLost = {258,286,314,342};
    private final int[] positionScoreVictory = {258,286,314,342};
    private final int[] positionFirstLife = {50,665};

    private final int nbrTags = 67;
    private final int [][] neighbors = {
            {-1,24,25}, {2,7},{1,3,8},{2,10},{11,5},{4,6,13},{5,14},{1,8,15},{2,7,9,16},{8,10,17},{3,9,11},{4,10,12},
            {11,13,20},{5,12,14,21},{6,13,22},{7,16},{8,28},{9,18},{17,24},{20,25},{12,19},{13,22,31},{14,21},
            {24,29},{18,23,25},{19,24,26},{25,30},{28},{16,29,36},{23,28,33},{26,31,34},{21,30,32,41},{31},{29,34,37},
            {30,33,40},{36,43},{28,35,37,45},{33,36,38},{37,47},{40,48},{34,39,41},{31,40,42,50},{41,52},{35,44},{43,54},
            {36,46,55},{45,47,56},{38,46,48},{39,47,49},{48,5,59},{41,49,60},{52,61},{42,51},{54,63},{44,53,55},{45,54},
            {46,57},{56,64},{59,65},{49,58},{50,61},{51,60,62},{61,66},{53,64},{57,63,65},{58,64,66},{65,62},
    };
    private int [][] positionNeighbors = {{273,275}, {33, 82}, {129, 82}, {244, 82}, {303, 82}, {418, 82}, {514, 82}, {33, 158},
            {129, 158}, {187, 158}, {244, 158}, {303, 158}, {360, 158}, {418, 158}, {514, 158}, {33, 216}, {129, 216},
            {187, 213}, {243, 213}, {304, 213}, {360, 213}, {418, 216}, {514, 216}, {187, 275}, {243, 275}, {304, 275},
            {360, 275}, {16, 332}, {129, 332}, {187, 332}, {360, 332}, {418, 332}, {529, 332}, {187, 390}, {360, 390},
            {32, 447}, {129, 447}, {187, 447}, {243, 447}, {304, 447}, {360, 447}, {418, 447}, {515, 447}, {32, 504},
            {70, 504}, {129, 505}, {188, 505}, {243, 505}, {304, 505}, {359, 505}, {418, 505}, {477, 505}, {515, 505},
            {32, 563}, {70, 563}, {129, 563}, {188, 563}, {243, 563}, {304, 563}, {359, 563}, {418, 463}, {477, 463},
            {515, 563}, {32, 620}, {243, 620}, {304, 620}, {515, 620}

    };

    private Direction [] [] directionReturn = {
            {Direction.SOUTH}, //1
            {Direction.SOUTH}, //2
            {Direction.SOUTH}, //3
            {Direction.SOUTH}, //4
            {Direction.SOUTH}, //5
            {Direction.SOUTH}, //6
            {Direction.SOUTH}, //7
            {Direction.EAST},  //8
            {Direction.EAST},  //9
            {Direction.SOUTH}, //10
            {Direction.WEST},  //11
            {Direction.EAST},  //12
            {Direction.SOUTH}, //13
            {Direction.WEST},  //14
            {Direction.WEST},  //15
            {Direction.EAST},  //16
            {Direction.NORTH}, //17
            {Direction.EAST},  //18
            {Direction.SOUTH}, //19
            {Direction.SOUTH}, //20
            {Direction.WEST},  //21
            {Direction.NORTH}, //22
            {Direction.EAST},  //23
            {Direction.EAST},  //24
            {Direction.EAST},  //25
            {Direction.WEST},  //26
            {Direction.WEST},  //27
            {Direction.EAST},  //28
            {Direction.EAST},  //29
            {Direction.NORTH}, //30
            {Direction.NORTH}, //31
            {Direction.WEST},  //32
            {Direction.WEST},  //33
            {Direction.NORTH}, //34
            {Direction.NORTH}, //35
            {Direction.EAST},  //36
            {Direction.EAST},  //37
            {Direction.NORTH}, //38
            {Direction.WEST},  //39
            {Direction.EAST},  //40
            {Direction.NORTH}, //41
            {Direction.WEST},  //42
            {Direction.WEST},  //43
            {Direction.NORTH}, //44
            {Direction.WEST},  //45
            {Direction.NORTH}, //46
            {Direction.WEST},  //47
            {Direction.NORTH}, //48
            {Direction.NORTH}, //49
            {Direction.WEST},  //50
            {Direction.NORTH}, //51
            {Direction.EAST},  //52
            {Direction.NORTH}, //53
            {Direction.EAST},  //54
            {Direction.NORTH}, //55
            {Direction.NORTH}, //56
            {Direction.NORTH}, //57
            {Direction.WEST},  //58
            {Direction.EAST},  //59
            {Direction.NORTH}, //60
            {Direction.NORTH}, //61
            {Direction.NORTH}, //62
            {Direction.WEST},  //63
            {Direction.NORTH}, //64
            {Direction.NORTH}, //65
            {Direction.NORTH}, //66
            {Direction.NORTH}  //67
    };


    private int [][] road = {
            {2,8,9,17,18,24,0,-1},
            {8,9,17,18,24,0,-1},
            {10,9,17,18,24,0,-1},
            {11,12,20,19,25,0,-1},
            {13,12,20,19,25,0,-1},
            {14,13,12,20,19,25,0,-1},
            {8,9,17,18,24,0,-1},
            {9,17,18,24,0,-1},
            {17,18,24,0,-1},
            {9,17,18,24,0,-1},
            {12,20,19,25,0,-1},
            {20,19,25,0,-1},
            {12,20,19,25,0,-1},
            {13,12,20,19,25,0,-1},
            {16,8,9,17,18,24,0,-1},
            {8,9,17,18,24,0,-1},
            {18,24,0,-1},
            {24,0,-1},
            {25,0,-1},
            {19,25,0,-1},
            {13,12,20,19,25,0,-1},
            {21,13,12,20,19,25,0,-1},
            {24,0,-1},
            {0,-1},
            {0,-1},
            {25,0,-1},
            {28,29,23,24,0,-1},
            {29,23,24,0,-1},
            {23,24,0,-1},
            {26,25,24,0,-1},
            {30,26,25,24,0,-1},
            {31,30,26,25,24,0,-1},
            {29,23,24,0,-1},
            {30,26,25,24,0,-1},
            {36,37,33,29,23,24,0,-1},
            {37,33,29,23,24,0,-1},
            {33,29,23,24,0,-1},
            {37,33,29,23,24,0,-1},
            {40,34,30,26,25,0,-1},
            {34,30,26,25,0,-1},
            {31,30,26,25,0,-1},
            {41,31,30,26,25,0,-1},
            {35,36,37,33,29,23,24,0,-1},
            {43,35,36,37,33,29,23,24,0,-1},
            {36,37,33,29,23,24,0,-1},
            {45,36,37,33,29,23,24,0,-1},
            {38,37,33,29,23,24,0,-1},
            {39,40,34,30,26,25,0,-1},
            {48,39,40,34,30,26,25,0,-1},
            {41,40,34,30,26,25,0,-1},
            {52,42,41,40,34,30,26,25,0,-1},
            {42,41,40,34,30,26,25,0,-1},
            {54,55,45,36,37,33,29,23,24,0,-1},
            {55,45,36,37,33,29,23,24,0,-1},
            {45,36,37,33,29,23,24,0,-1},
            {46,45,36,37,33,29,23,24,0,-1},
            {56,46,45,36,37,33,29,23,24,0,-1},
            {59,49,48,39,40,34,30,26,25,0,-1},
            {49,48,39,40,34,30,26,25,0,-1},
            {50,41,40,34,30,26,25,0,-1},
            {60,50,41,40,34,30,26,25,0,-1},
            {61,60,50,41,40,34,30,26,25,0,-1},
            {53,54,55,45,36,37,33,29,23,24,0,-1},
            {57,56,46,45,36,37,33,29,23,24,0,-1},
            {64,57,56,46,45,36,37,33,29,23,24,0,-1},
            {65,64,57,56,46,45,36,37,33,29,23,24,0,-1}
    };




    public void init() {
        try {
            System.out.println(positionNeighbors.length);
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

            for (int i = 0; i < nbrTags; i++) {
                
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
            _ennemies.add(new Ghost(new Position(242,332),29,29,2,new DisplayBlinky(_graphic,"maze")));
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


            _items.add(new Niche(new Position(273, 332), 76, 132));    // Foyer fantômes
            _items.add(new Wall(new Position(273,361),28,142)); //Mur du bas
            _items.add(new Wall(new Position(331,332),84,26));  //Mur de Droite
            _items.add(new Wall(new Position(215,332),84,26));  //Mur de Gauche
            _items.add(new Wall(new Position(229,303),28,54));  //Mur du Haut Gauche
            _items.add(new Wall(new Position(317,303),28,54));  //Mur du Haut Droite
            _items.add(new Door(new Position(273,303),28,30));  //Porte


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
            scoreLevel= 0;
            scoreTotal = 0;
            vie = nbVie;

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
            _graphic.setPositionImageButton("help","retour",197,640,false);
            _graphic.displayObject("help","retour");


            /* ------ Création Scène ABANDON ------ */
            _graphic.addScene("forfeit");
            _graphic.setSizeScene("forfeit",544,700);

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

            for (int j = 0; j < 10; j++) {
                String value = String.valueOf(j);
                for (int i = 0; i < 4; i++) {
                    _graphic.addImage("victory",value,"./src/Images/Autres/Chiffres/chiffre_"+j+".png");
                    _graphic.setPositionImage("victory",value,positionScoreVictory[positionScoreVictory.length-1-i]-43, 275,false);
                    value = value.concat("0");
                }
            }

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

            for (int j = 0; j < 10; j++) {
                String value = String.valueOf(j);
                for (int i = 0; i < 4; i++) {
                    _graphic.addImage("lost",value,"./src/Images/Autres/Chiffres/chiffre_"+j+".png");
                    _graphic.setPositionImage("lost",value,positionScoreLost[positionScoreLost.length-1-i]-43, 275,false);
                    value = value.concat("0");
                }
            }

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

            /* ---- Ajout Score dans le Jeu --- */

            for (int j = 0; j < 10; j++) {
                String value = String.valueOf(j);
                for (int i = 0; i < 4; i++) {
                    _graphic.addImage("maze",value,"./src/Images/Autres/Chiffres/chiffre_"+j+".png");
                    _graphic.setPositionImage("maze",value,positionScoreMaze[positionScoreMaze.length-1-i], 0,false);
                    value = value.concat("0");
                }
            }


            _graphic.addImage("maze","score","./src/Images/Autres/score.png");
            _graphic.setPositionImage("maze","score",20,0,false);
            _graphic.resizeImage("maze","score",50,170);
            _graphic.displayObject("maze","score");
            resetScore("maze");

            /* --- Ajout vies dans le Jeu ---*/

            for (int i = 0; i < nbVie; i++) {
                _graphic.addImage("maze","vie"+i,"./src/Images/Autres/vie.png");
                int x = positionFirstLife[0]+50*i;
                int y = positionFirstLife[1];
                _graphic.setPositionImage("maze","vie"+i,x,y,false);
                _graphic.displayObject("maze","vie"+i);
            }

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
            vie = nbVie;
            scoreLevel = 0;
            gameStart = false;
            printScore("maze");
            printVie();

            for (Character ghost : _ennemies) {
                ghost.restart();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printVie() {
        for (int i = 0; i < nbVie; i++) {
            try {
                _graphic.displayObject("maze","vie"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void resetScore(String sceneLabel) throws Exception {
        for (int j = 0; j < 10; j++) {
            String value = String.valueOf(j);
            for (int i = 0; i < 4; i++) {
                _graphic.hideObject(sceneLabel,value);
                value = value.concat("0");
            }
        }
        _graphic.displayObject(sceneLabel,"0000");
        _graphic.displayObject(sceneLabel,"000");
        _graphic.displayObject(sceneLabel,"00");
        _graphic.displayObject(sceneLabel,"0");
    }

    private void updateScore() throws Exception {
        if (_pc.score != scoreLevel) {
            updateScoreThousand();
            updateScoreHundred();
            updateScoreTen();
            updateScoreOne();
            scoreTotal -= scoreLevel;
            scoreLevel = _pc.score;
            scoreTotal += scoreLevel;
        }
    }

    private void updateScoreThousand() throws Exception {
        String oldValue = String.valueOf(scoreTotal%10000/1000);
        oldValue = oldValue.concat("000");
        _graphic.hideObject("maze",oldValue);
        String newValue = String.valueOf((scoreTotal-scoreLevel+ _pc.score)%10000/1000);
        newValue = newValue.concat("000");
        _graphic.displayObject("maze",newValue);
    }

    private void updateScoreHundred() throws Exception {
        String oldValue = String.valueOf(scoreTotal%1000/100);
        oldValue = oldValue.concat("00");
        _graphic.hideObject("maze",oldValue);
        String newValue = String.valueOf((scoreTotal-scoreLevel+ _pc.score)%1000/100);
        newValue = newValue.concat("00");
        _graphic.displayObject("maze",newValue);
    }

    private void updateScoreTen() throws Exception {
        String oldValue = String.valueOf(scoreTotal%100/10);
        oldValue = oldValue.concat("0");
        _graphic.hideObject("maze",oldValue);
        String newValue = String.valueOf((scoreTotal-scoreLevel+ _pc.score)%100/10);
        newValue = newValue.concat("0");
        _graphic.displayObject("maze",newValue);

    }

    private void updateScoreOne() throws Exception {
        String oldValue = String.valueOf(scoreTotal%10);
        _graphic.hideObject("maze",oldValue);
        String newValue = String.valueOf((scoreTotal-scoreLevel+ _pc.score)%10);
        _graphic.displayObject("maze",newValue);

    }

    private void printScore(String sceneLabel) throws Exception {
        resetScore(sceneLabel);
        _graphic.displayObject(sceneLabel,String.valueOf(scoreTotal%10000/1000).concat("000"));
        _graphic.displayObject(sceneLabel,String.valueOf(scoreTotal%1000/100).concat("00"));
        _graphic.displayObject(sceneLabel,String.valueOf(scoreTotal%100/10).concat("0"));
        _graphic.displayObject(sceneLabel,String.valueOf(scoreTotal%10));
    }

    public void update() {
        if (!_graphic.currentScene().equals("maze"))
            return;
        if (gamePaused)
            return;
        if (_pc._direction == Direction.NONE && !gameStart)
            return;
        try {
            updateScore();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (scoreLevel%240 == 0 && scoreLevel != 0) {
            try {

                restartMaze();
                level++;

                if (level == 2) {
                    _ennemies.add(new Ghost(new Position(190,390),29,29,2,new DisplayGreen(_graphic,"maze")));
                }

                if (level == 3) {
                    _ennemies.add(new Ghost(new Position(360,390),29,29,2,new DisplayPurple(_graphic,"maze")));
                }

                if (level == 4) {
                    _ennemies.add(new Ghost(new Position(273.5,157),29,29,2,new DisplayGrey(_graphic,"maze")));
                }

                if (level == 5) {
                    _ennemies.add(new Ghost(new Position(273.5,620.5),29,29,2,new DisplayBrown(_graphic,"maze")));
                }
                if (level == 6) {
                    printScore("victory");
                    _graphic.displayScene("victory");
                    scoreTotal = 0;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (_pc.isDead) {
            if (System.currentTimeMillis() - _pc.deathDate < 3900)
                return;
            vie--;
            try {
                _graphic.hideObject("maze","vie"+vie);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (vie == 0) {
                try {
                    printScore("lost");
                    _graphic.displayScene("lost");
                    scoreTotal = 0;
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
        if (_pc.canEatGhost()) {
            for (Ghost ghost : _ennemies) {
                if (ghost.isActive() && !ghost._edible)
                    ghost._edible = true;
            }
        } else if (!_pc.canEatGhost()) {
            for (Ghost ghost : _ennemies) {
                if (ghost.isActive() && ghost._edible)
                    ghost._edible = false;
            }
        }
        handleLastKey();
        _pc.move(_pc._direction);
        for (Ghost g : _ennemies)
            if (!_pc.isDead){
                if (g.isActive())
                    g.move(g._direction);
                else
                {
                    for (int i = 0; i < nbrTags; i++) {
                        if (g._position.x < positionNeighbors[i][0]+2 && g._position.x > positionNeighbors[i][0]-2&&
                            g._position.y < positionNeighbors[i][1]+2 && g._position.y > positionNeighbors[i][1]-2) {
                            g._direction = directionReturn[i][0];
                            break;
                        }
                    }
                    g.move(g._direction);
                }
            }
    }

    @Override
    public double getSpeed() {
        return 60;
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
                        if (gamePaused)
                            break;
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
                        scoreTotal = 0;
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


    private boolean inTag(int tag1, int tag2, Position pos) {
        if (positionNeighbors[tag1][0] < pos.x && pos.x < positionNeighbors[tag2][0] && (pos.y == positionNeighbors[tag1][1] || pos.y == positionNeighbors[tag1][1]+1 || pos.y == positionNeighbors[tag1][1]-1))
        {
                return true;
        }
        else if (positionNeighbors[tag2][0] < pos.x && pos.x < positionNeighbors[tag1][0] && (pos.y == positionNeighbors[tag1][1] || pos.y == positionNeighbors[tag1][1]-1 || pos.y == positionNeighbors[tag1][1]+1)) {
                return true;
        }
        else if (positionNeighbors[tag1][1] < pos.y && pos.y < positionNeighbors[tag2][1] && (pos.x == positionNeighbors[tag1][0] || pos.x == positionNeighbors[tag1][0]-1 || pos.x == positionNeighbors[tag1][0]+1)){
                return true;
        }
        else {
            return positionNeighbors[tag2][1] < pos.y && pos.y < positionNeighbors[tag1][1] &&
                    (pos.x == positionNeighbors[tag1][0] || pos.x == positionNeighbors[tag1][0]-1 || pos.x == positionNeighbors[tag1][0]+1);
        }
    }

    private int[] findTag(Position pos) {
        int [] duo = {0,0};
        for (int i = 1; i < nbrTags; i++) {
            for (int j = 0; j < neighbors[i].length; j++) {
                if (inTag(i, neighbors[i][j], pos)) {
                    duo[0] = i; duo[1] = neighbors[i][j];
                    return duo;
                }
            }
        }
        return duo;
    }

    private int neighborTag(int tag1, int tag2, Position pos) {
        if (tag1 == 0)
            return tag2;
        if (tag2 == 0)
            return tag1;

        double distance1 = 0;
        double distance2 = 0;
        if (positionNeighbors[tag1][0] == positionNeighbors[tag2][0]) {
            distance1 = Math.abs(positionNeighbors[tag1][1] - pos.y);
            distance2 = Math.abs(positionNeighbors[tag2][1] - pos.y);
        }
        else {
            distance1 = Math.abs(positionNeighbors[tag1][0] - pos.x);
            distance2 = Math.abs(positionNeighbors[tag2][0] - pos.x);
        }

        if (distance1 > distance2)
            return tag2;
        else
            return tag1;
    }

    private Direction direction(int[] tag_origin, int[] tag_target) {
        if ((tag_target[1]-tag_origin[1]) > 0) {
            return Direction.NORTH;
        }
        else if ((tag_target[1]-tag_origin[1]) < 0) {
            return Direction.SOUTH;
        }
        else if ((tag_target[0]-tag_origin[0]) > 0) {
            return Direction.EAST;
        }
        else if ((tag_target[0]-tag_origin[0]) < 0) {
            return Direction.WEST;
        }
        else
            return Direction.NONE;

    }

}
