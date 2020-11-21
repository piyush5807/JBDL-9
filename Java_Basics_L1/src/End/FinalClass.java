package End;

public class FinalClass {

    // final -> class, variable, function

    public final int count = 30;


    public FinalClass(){
//        count = 1;
    }

    public final void printHello(String name){
        System.out.println("In Final Class:  Saying hello to " + name);
    }

    public static void main(String[] args) {
        FinalClass obj = new FinalClass();

    }
}
