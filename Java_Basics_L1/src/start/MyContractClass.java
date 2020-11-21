package start;

public class MyContractClass implements MyInterface{

    @Override
    public void printHello() {
        System.out.println("Printing hello");
    }

    @Override
    public String getMyName() {
        return "Your name is : Piyush";
    }

    @Override
    public void setName(String name) {
        System.out.println("setting the name :" + name);
    }
}
