package start;

import End.Person;

public class TC2 extends AbstractClass{

    public static void main(String[] args) {

//        MyInterface obj = new MyContractClass();
//
//        obj.printHello();
//        System.out.println(obj.getMyName());
//        obj.setName("John");

        TC2 obj = new TC2();
        obj.printHello();
        obj.printHello2();


    }

    @Override
    void printHello() {
        System.out.println("In Class TC 2: printing hello");
    }
}
