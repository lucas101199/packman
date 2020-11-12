import java.util.ArrayList;

public class Main {
    private static ArrayList<Entity> _entities;

    public static void main(String[] args) {
        _entities = new ArrayList<>();
        _entities.add(new Ghost(new Position(50,60), 20,20,0));
        _entities.add(new Wall(new Position(80,10), 20,20));
        _entities.add(new Bonus(new Position(0,10), 20,20, false, 50));
        var c = new CollisionChecker(_entities);
        Character.setCollisionChecker(c);
	    var pc= new Pacman(new Position(50,10), 20,20,10, 3);
        int score = 0;
        while(true){
            pc.move(Direction.WEST);
            System.out.println(pc.getPosition().x + ";" + pc.getPosition().y);
            System.out.println("Score :" + score + "Vie :" + pc.getRemainingLife());
            if(pc.lastEatenItem() != null && pc.lastEatenItem().isActive()) {
                score += pc.lastEatenItem().getScore();
                pc.lastEatenItem().eat();
            }
            updateWorld();
            waitFewMoment();
        }

    }

    public static void updateWorld(){
        _entities.removeIf(entity -> !entity.isActive());
    }

    public static void waitFewMoment(){
        try{
            Thread.sleep(100);;
        }
        catch(InterruptedException e){e.printStackTrace();}
    }
}
