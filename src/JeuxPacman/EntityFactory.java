package JeuxPacman;

public interface EntityFactory {
    SuperPacGum createSuperPacGum(Position pos);
    Pacman createPacman(Position pos);
    Ghost createGhost(Position pos);
    ScoringBonus createScoringBonus(Position pos);
}
