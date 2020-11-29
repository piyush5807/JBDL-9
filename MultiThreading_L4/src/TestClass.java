public class TestClass{

    // variable
    // function
    // class
    // enum

    // Ques: How you can create a thread ?

    // I am in thread - Thread-2
    //I am in thread - Thread-5
    //I am in thread - Thread-4
    //I am in thread - Thread-0
    //I am in thread - Thread-1
    //I am in thread - Thread-3
    //I am in thread - Thread-6
    //I am in thread - Thread-7
    //I am in thread - Thread-8
    //I am in thread - Thread-9


    public static void main(String[] args) throws InterruptedException {

        TestClass obj = new TestClass();

        for(int i=0;i<10;i++){
            MyThread thread = obj.new MyThread();

//            if(i == 4) {
//                thread.setPriority(Thread.MAX_PRIORITY);
//            }else if(i == 5){
//                thread.setPriority(Thread.MIN_PRIORITY);
//            }
            thread.start();
            thread.join();
        }
    }

    private class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("I am in thread - " + currentThread().getName());
        }
    }
}
