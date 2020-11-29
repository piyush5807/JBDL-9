import java.math.BigInteger;

public class Factorial {

    public static void main(String[] args) {

        int[]arr = {10000, 20000, 21000, 50000, 40000, 80000, 60000};

        long start = System.currentTimeMillis();

        for(int i=0;i<arr.length;i++){
            printFactorial(arr[i]);
        }

        System.out.println(System.currentTimeMillis() - start);
    }

    public static void printFactorial(int num){

        BigInteger result = BigInteger.valueOf(1);

        for(int i=1;i<num;i++){
            result = result.multiply(BigInteger.valueOf(i));
        }

        System.out.println(result);
    }
}
