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
    }
}