import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(new Character(new Position(50,60), 20,20,0));
        var c = new CollisionChecker(entities);
        Character.setCollisionChecker(c);
	    var pc= new Character(new Position(50,10), 20,20,10);

        while(true){
            pc.move(Direction.NORTH);
            System.out.println(pc.getPosition().x + ";" + pc.getPosition().y);
            waitFewMoment();
        }
    }

    public static void waitFewMoment(){
        try{
            Thread.sleep(100);;
        }
        catch(InterruptedException e){e.printStackTrace();}
    }
}
