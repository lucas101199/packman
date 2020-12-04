package PhysicMotor;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

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