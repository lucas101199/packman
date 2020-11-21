public class Main {

    public static void main(String[] args) {
        var motor = new PhysicMotor();
        var pc = new PacmanMock(5, 4);
        pc.setCollisionArea(new RectCollisionArea(50,50, pc));
        motor.registerMoveableEntity(pc);

        var el = new PacmanMock(10, 10);
        el.setCollisionArea(new RectCollisionArea(50,50, el));
        motor.registerEntity(el);
        motor.update();
    }
}