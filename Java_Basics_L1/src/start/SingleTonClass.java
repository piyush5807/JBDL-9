package start;

public class SingleTonClass {

    private static SingleTonClass singletonObject;

    private SingleTonClass() {

    }

    public static SingleTonClass getInstance(){

        if(singletonObject == null){
            singletonObject = new SingleTonClass();
        }

        return singletonObject;
    }
}
