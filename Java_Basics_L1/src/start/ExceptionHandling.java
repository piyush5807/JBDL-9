package start;

import com.sun.tools.internal.xjc.AbortException;

public class ExceptionHandling {

    public static void main(String[] args) throws Exception {
        try {
            System.out.println(3 / 0);

        }catch (ArithmeticException e){
            System.out.println("There is something wrong in the maths calculation in this block");
//            e.printStackTrace();
            throw new Exception("An arithemtic exception occured");
        }catch (AbortException e){
            System.out.println("There is something wrong in this due wo which it is aborted");
        }catch (Exception e){
            System.out.println("Some error occured");
        }
        finally {
            System.out.println("Hello");
        }

        System.out.println("Ending the function");
    }
}
