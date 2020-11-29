import java.math.BigInteger;

public class FactorialUsingMultiThreading {

    public static void main(String[] args) throws Exception{
        int[]arr = {10000, 20000, 21000, 50000, 40000, 80000, 60000};

        long start = System.currentTimeMillis();

        Test[]threads = new Test[arr.length];
        for(int i=0;i<arr.length;i++){

            threads[i] = new Test(arr[i]);

            // starting a thread
            threads[i].start();

            // waiting for thread to complete
//            threads[i].join();

            // printing the result
//            System.out.println("result of " + arr[i] + " is " + threads[i].result);


            //
            //
            //


        }

        for(int i=0;i<arr.length;i++){
            threads[i].join();
            System.out.println("result of " + arr[i] + " is " + threads[i].result);
        }

        System.out.println(System.currentTimeMillis() - start);
    }

    // t1 t2

    private static class Test extends Thread{

        private int num;
        private BigInteger result;

        Test(int num){
            this.num = num;
            this.result = BigInteger.valueOf(1);

        }

        @Override
        public void run() {

            for(int i=1;i<this.num;i++){
                result = result.multiply(BigInteger.valueOf(i));
            }
        }
    }
}
