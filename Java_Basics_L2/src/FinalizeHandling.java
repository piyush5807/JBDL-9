public class FinalizeHandling {

    int x;

    @Override
    protected void finalize() {

        System.out.println("Method starting...");

        try {
            System.out.println(3 / 0);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Method ending ...");
    }

    public static void main(String[] args) {

        FinalizeHandling obj = new FinalizeHandling();

        System.out.println(obj);
//
        obj = null;

        System.gc();
//
//        obj.finalize();

        obj.x = 10;

        System.out.println(obj);
    }

    public void gc(){

        try {
            finalize();
        }catch (Exception e){

        }

    }
}
