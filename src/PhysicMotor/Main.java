package PhysicMotor;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        var motor = new PhysicMotor();
        var pc = new PacmanMock(5, 4, PhysicReaction.SOLID, null);
        pc.setCollisionArea(new RectCollisionArea(50,50, pc));
        motor.registerMoveableEntity(pc);

        var el = new PacmanMock(10, 10, PhysicReaction.SOLID, null);
        el.setCollisionArea(new RectCollisionArea(50,50, el));
        motor.registerEntity(el);

        motor.update();
        pc.setPosition(30,30);
        motor.update();
        System.out.println("-------------------------------|");
        pc.setPosition(100,100);
        pc.addForce(new Force(60,60));
        motor.update();
        System.out.println(pc.getX() + ";" + pc.getY());
        ArrayList<? extends AZA> arr = new ArrayList<>();
        AZAF a = new AZAF();
        //arr.add(a);
    }

    public static void doS(int ...args){
        System.out.println("Nom classe :" + args.getClass().getTypeName());
    }
}
interface  Inte{

        }
class ABA<T extends  Inte>{
    public void doS(int ... arg){

    }
    public void getSomething(T a){

    }
}

class AZA{
    public <T extends AZA> T doS(T a){

        return a;
    }
}

class AZAF extends AZA{

}

interface Lol<U>{
    void doS(U a);
}