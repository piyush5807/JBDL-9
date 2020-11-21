package End;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {


    Employee(int age, String name, String lastName) {
        super(age, name, lastName);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    // Compile time polymorphism
    public void printSomething(Employee employee){

        System.out.println(employee);
    }

    public void printSomething(String name, int age){
        System.out.println("name = " + name + ", age = " + age);
    }

    // Run time polymorphism
    public void printSomething(){
        super.printSomething();
        System.out.println("In employee class: printing hello");

    }

    public static void main(String[] args) {
        Person employee = new Employee(40, "Karan", "Gupta");

        employee.getName();


//        employee.printSomething(employee);
//        employee.printSomething(employee.getName(), employee.getAge());

//        employee.printSomething();

        Employee person = new Employee(40, "Karan", "Kumar");
        person.printSomething();


    }
}
