public class MultiThreading {

    public static void main(String[] args) throws Exception{

//        Thread.sleep(10000);
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        Thread.sleep(10000);
//        System.out.println(Runtime.getRuntime().totalMemory());
//        Thread.sleep(10000);
//        System.out.println(Runtime.getRuntime().maxMemory());
//        Thread.sleep(10000);
//        System.out.println(Runtime.getRuntime().freeMemory());


        MyThread[]threads = new MyThread[5];

        for(int i=0;i<threads.length;i++){
            threads[i] = new MyThread();
            threads[i].start();
        }

        Thread.sleep(20000);

    }

    private static class MyThread extends Thread{


        @Override
        public void run() {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Kafka - used as a messaging
    // Redis - cache
}
