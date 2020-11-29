public class ThreadingUsingRunnable {

    public static void main(String[] args) {

        MyThread obj = new MyThread();
        Thread thread = new Thread(obj);

        thread.start();

    }

    private static class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println("I am in thread - " + Thread.currentThread().getName());
            System.out.println("the answer is " + 3*5);

            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("After sleeping for 30 secs");
        }
    }
}
