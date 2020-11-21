package start;

import End.Person;

public class Main {

    public static void main(String[] args) {

//        SingleTonClass obj1 = new SingleTonClass();
//        SingleTonClass obj2 = new SingleTonClass();

        SingleTonClass obj1 = SingleTonClass.getInstance();

        SingleTonClass obj2 = SingleTonClass.getInstance();

        System.out.println(obj1 + " " + obj2);

    }


}
