import java.util.ArrayList;

public class Main {
    private static ArrayList<Entity> _entities;
    private static Pacman _pc;
    private static int _score;

    public static void main(String[] args) {
        var game = new Game(500);
        game.start();
    }
}
