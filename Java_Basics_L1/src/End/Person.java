package End;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Person {

    // 1. private -> scope of that particular class
    // 2. default -> scope of current class and every class in the same package
    // 3. protected -> scope of current class and any current in the same package and in the child classes in different package
    // 4. public -> scope is anywhere in the project

    public static int counter;

    private String name;
    private int age;
    private String lastName;

    int houseNo;

    public Person(int age, String name, String lastName){
        this.age = age;
        this.name = name;
        this.lastName = lastName;
    }

    protected String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void printSomething(){
        System.out.println("In person class: printing hello");
    }


    //    @Override
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", lastName='" + lastName + '\'' +
//                '}';
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName);
    }

    public static int getCounter(){
        return counter;
    }

    public static void setCounter(int count){
        counter = count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    // -1854580347

    // 1. Hashcodes are calculated
    // 2. if hc are same, then equals come into the picture

    public static void main(String[] args) {

//        System.out.println("Main function starting here");
//
//        Person obj1 = new Person(20, "Rahul", "Arora");
//
//        Person obj2 = new Person(20, "Rahul", "Singh");
//
////        System.out.println(obj1.equals(obj2));
//
//        HashMap<Person, Boolean> hashMap = new HashMap<>();
//
//        hashMap.put(obj1, true);
//        hashMap.put(obj2, true);
//
//
//        System.out.println(hashMap);
//        System.out.println(obj1.hashCode() + " " + obj2.hashCode() + " " + obj1.equals(obj2));

        System.out.println(getCounter());

        setCounter(getCounter() + 10);

        System.out.println(getCounter());
    }
}
